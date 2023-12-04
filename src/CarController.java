import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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



    public CarController(){
        this.frame = new CarView("CarSim 1.0");

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary

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

        /*cc.cars.add(new Volvo240("Yo"));
        cc.cars.getLast().moveRight();
        cc.cars.add(new Saab95("Tja"));
        cc.cars.getLast().getPosition().move(0,200);
        cc.cars.getLast().moveRight();
        cc.cars.add(new Scania("Hej"));
        cc.cars.getLast().getPosition().move(0,400);
        cc.cars.getLast().moveRight();*/



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
                if (x+(int)Math.round(car.getCurrentSpeed()) > 700 && car.getDirection().equals("East")){
                    car.getPosition().move(700,(int)car.getPosition().getY());
                    car.stopEngine();
                    car.moveRight();
                    car.moveRight();
                    car.startEngine();
                }

                else if (x-(int)Math.round(car.getCurrentSpeed()) < 0 && car.getDirection().equals("West")){
                    car.getPosition().move(0,(int)car.getPosition().getY());
                    car.stopEngine();
                    car.moveRight();
                    car.moveRight();
                    car.startEngine();
                }
                else {

                car.move();
                }
                frame.moveit(car,x, y);
                // repaint() calls the paintComponent method of the panel

                 frame.repaint(car,x ,y);
            }
        }
    }



}
