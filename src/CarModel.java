import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarModel {
    private ArrayList<Vehicle> cars = new ArrayList<>();
    public ArrayList<Vehicle> getCars() {
        return cars;
    }

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    public Timer timer;

    public TimerListener timerListener;

    private int frameBoundaryX;


    //TODO Fråga: frameBoundaryX som en parameter i konstruktorn?
    protected CarModel(int frameBoundaryX){
        this.frameBoundaryX=frameBoundaryX;
        createCarList();
        this.timerListener = new TimerListener();
        timer = new Timer(delay, timerListener);
    }
    private void createCarList(){
        cars.add(VehicleFactory.createVolvo240("VVO240"));
        cars.getLast().moveRight();
        cars.add(VehicleFactory.createSaab95("SAB095"));
        cars.getLast().moveRight();
        cars.getLast().getPosition().move(0,200);
        cars.add(VehicleFactory.createScania("SCA180"));
        cars.getLast().moveRight();
        cars.getLast().getPosition().move(0,400);
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
    void startAll(){
        //Starta alla engines
        for (Vehicle car : cars
        ) {
            car.startEngine();
        }
    }
    void stopAll(){
        //Stoppa alla engines
        for (Vehicle car : cars
        ) {
            car.stopEngine();
        }
    }


    public class TimerListener implements ActionListener {


        public ArrayList<Observer> observers = new ArrayList<>();

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        //TODO: remove??
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        private void notifyObservers() {
            for (Observer observer : observers) {
                observer.update();

            }
        }
        private void moveItNotify(String carName, int x, int y){
            for (Observer observer : observers) {
                observer.moveIt(carName, x, y);
            }

        }

        public void notifyCarAdd(){
            for (Observer observer : observers) {
                observer.notifyCarAdd();
            }
        }

        public void notifyCarRemove(){
            for (Observer observer : observers) {
                observer.notifyCarRemove();
            }
        }

        int frameMinBoundaryX = 0;
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                if (x+(int)Math.round(car.getCurrentSpeed()) > frameBoundaryX && car.getDirection().equals("East")){
                    car.getPosition().move(frameBoundaryX,(int)car.getPosition().getY());
                    car.stopEngine();
                    car.moveRight();
                    car.moveRight();
                    car.startEngine();
                }

                else if (x-(int)Math.round(car.getCurrentSpeed()) < frameMinBoundaryX && car.getDirection().equals("West")){
                    car.getPosition().move(frameMinBoundaryX,(int)car.getPosition().getY());
                    car.stopEngine();
                    car.moveRight();
                    car.moveRight();
                    car.startEngine();
                }
                else {
                    car.move();
                }
                moveItNotify(car.getRegistrationNr(),x, y);

                notifyObservers();
            }
        }
    }

}
