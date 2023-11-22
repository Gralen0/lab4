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

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    // A list of cars, modify if needed
    public ArrayList<Vehicle> cars = new ArrayList<>();

    public ArrayList<Vehicle> getCars() {
        return cars;
    }
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240("Yo"));
        cc.cars.add(new Saab95("Tja"));
        cc.cars.getLast().getPosition().move(0,200);
        cc.cars.getLast().moveRight();
        cc.cars.add(new Scania("Hej"));
        cc.cars.getLast().getPosition().move(0,400);
        cc.cars.getLast().moveRight();

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {

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
                car.move();
                frame.drawPanel.moveit(car,x, y);
                // repaint() calls the paintComponent method of the panel

                 frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars
                ) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars
        ) {
            car.brake(brake);
        }
    }
    void turboOn(){
        //Sätt på turbon på Saaben
        for (Vehicle saab : cars) {
            if (saab.getClass() == Saab95.class){
                ((Saab95) saab).setTurboOn();
            }
        }
    }
    void turboOff(){
        //Stäng av turbon på Saaben
        for (Vehicle saab : cars) {
            if (saab.getClass() == Saab95.class){
                ((Saab95) saab).setTurboOff();
            }
        }
    }
    void liftBed(){
        //Res flaket
        for (Vehicle car : cars) {
            if (car.getClass()==Scania.class){
                ((Scania) car).raiseRamp(70);
                System.out.println(((Scania) car).getTruckBedIncline());
            }
        }
    }
    void lowerBed(){
        //Fäll flaket
        for (Vehicle car : cars) {
            if (car.getClass()==Scania.class){
                ((Scania) car).lowerRamp(70);

                System.out.println(((Scania) car).getTruckBedIncline());
            }
        }
    }
    void start(){
        //Starta alla engines
        for (Vehicle car : cars
        ) {
            car.startEngine();
        }
    }
    void stop(){
        //Stoppa alla engines
        for (Vehicle car : cars
        ) {
            car.stopEngine();
        }
    }
}
