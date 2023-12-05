import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Position;
import java.awt.*;
import java.util.HashMap;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    CarModel model = new CarModel();

    // The frame that represents this instance View of the MVC
    // Start a new view and send a reference of self
    CarView frame;
    //DrawPanel drawPanelComposition;
    int frameMinEdgeX = 0;
    HashMap<String, Point> carPositions;


    public CarController(){
        this.frame = new CarView("CarSim 1.0");

        carPositions = new HashMap<>();
        //TODO: Fråga om Vehicle som typ
        for (Vehicle car : model.getCars()){

            carPositions.put(car.getModelName(), car.getPosition());

        }

        //Sätter Frame storleken i DrawPanel
        //drawPanelComposition.setFrameSizeX(frame.getFrameSizeX());
        frame.drawPanel.setFrameSizeX(frame.getFrameSizeX());

        frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                frame.gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gas(frame.gasAmount);
            }
        });
        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.brake(frame.gasAmount);
            }
        });
        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOn();
            }
        });
        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOff();
            }
        });
        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.liftBed();
            }
        });
        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.lowerBed();
            }
        });
        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.start();
            }
        });
        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.stop();
            }
        });

    }
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();


        // Start the timer
        cc.timer.start();
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : model.getCars()) {

                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                if (x+(int)Math.round(car.getCurrentSpeed()) > frame.drawPanel.getFrameEdgeX() && car.getDirection().equals("East")){
                    car.getPosition().move(frame.drawPanel.getFrameEdgeX(),(int)car.getPosition().getY());
                    car.stopEngine();
                    car.moveRight();
                    car.moveRight();
                    car.startEngine();
                }

                else if (x-(int)Math.round(car.getCurrentSpeed()) < frameMinEdgeX && car.getDirection().equals("West")){
                    car.getPosition().move(frameMinEdgeX,(int)car.getPosition().getY());
                    car.stopEngine();
                    car.moveRight();
                    car.moveRight();
                    car.startEngine();
                }
                else {
                    model.moveit(car,x, y);
                }

                // repaint() calls the paintComponent method of the panel

                //Uppdatera Hashmap sen skicka
                carPositions.put(car.getModelName(),car.getPosition());
                frame.drawPanel.setPositionHashMap(carPositions);


                frame.drawPanel.repaint();
            }
        }
    }



}
