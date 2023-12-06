import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    CarModel model;

    int frameMinBoundaryX = 0;

    //methods:

    public CarController(){
        this.frame = new CarView("CarSim 1.0");

        model = new CarModel();

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
                model.startAll();
            }
        });
        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.stopAll();
            }
        });
    }
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        // Start a new view and send a reference of self


        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */

    //TODO Flytta till model??
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO Ta bort Vehicle?
            for (Vehicle car : model.getCars()) {
                int x = (int) Math.round(model.getPosition(car).getX());
                int y = (int) Math.round(model.getPosition(car).getY());
                if (x+(int)Math.round(model.getCurrentSpeed(car)) > frame.drawPanel.getFrameBoundaryX() && model.getDirection(car).equals("East")){
                    model.getPosition(car).move(frame.drawPanel.getFrameBoundaryX(),(int)model.getPosition(car).getY());
                    model.stopEngine(car);
                    model.moveRight(car);
                    model.moveRight(car);
                    model.startEngine(car);
                }

                else if (x-(int)Math.round(model.getCurrentSpeed(car)) < frameMinBoundaryX && model.getDirection(car).equals("West")){
                    model.getPosition(car).move(frameMinBoundaryX,(int)model.getPosition(car).getY());
                    model.stopEngine(car);
                    model.moveRight(car);
                    model.moveRight(car);
                    model.startEngine(car);
                }
                else {

                model.move(car);
                }
                //TODO Är Vehicle car som argument problem?
                frame.drawPanel.moveit(model.getRegistrationNr(car),x, y);
                // repaint() calls the paintComponent method of the panel

                 frame.drawPanel.repaint();
            }
        }
    }

    //TODO FIX STOP AND START CAR (BREAK)

}
