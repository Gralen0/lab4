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

    public TimerListener timerListener = new TimerListener();

    private int frameBoundaryX;


    protected CarModel(int frameBoundaryX) {
        this.frameBoundaryX = frameBoundaryX;
        timer = new Timer(delay, timerListener);
        //createCarList();
    }

    private void createCarList() {
        //addCar();
        //addCar();
        //addCar();
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

    void turboOn() {
        //Sätt på turbon på Saaben
        for (Vehicle saab : cars) {
            if (saab.getClass() == Saab95.class) {
                ((Saab95) saab).setTurboOn();
            }
        }
    }

    void turboOff() {
        //Stäng av turbon på Saaben
        for (Vehicle saab : cars) {
            if (saab.getClass() == Saab95.class) {
                ((Saab95) saab).setTurboOff();
            }
        }
    }

    void liftBed() {
        //Res flaket
        for (Vehicle car : cars) {
            if (car.getClass() == Scania.class) {
                ((Scania) car).raiseRamp(70);
            }
        }
    }

    void lowerBed() {
        //Fäll flaket
        for (Vehicle car : cars) {
            if (car.getClass() == Scania.class) {
                ((Scania) car).lowerRamp(70);
            }
        }
    }

    void startAll() {
        //Starta alla engines
        for (Vehicle car : cars
        ) {
            car.startEngine();
        }
    }

    void stopAll() {
        //Stoppa alla engines
        for (Vehicle car : cars
        ) {
            car.stopEngine();
        }
    }

    void addCar() {
        if (cars.size() < 10) {
            if (cars.isEmpty()) {
                cars.add(VehicleFactory.createVolvo240("VOLVO" + Math.random()));
            }
            else if (!cars.isEmpty()){
                int height = (int) cars.getLast().getPosition().getY();
                if (cars.getLast().getClass() == Volvo240.class) {
                    cars.add(VehicleFactory.createSaab95("SAAB" + Math.random()));
                } else if (cars.getLast().getClass() == Saab95.class) {
                    cars.add(VehicleFactory.createScania("SCANIA" + Math.random()));
                } else if (cars.getLast().getClass() == Scania.class) {
                    cars.add(VehicleFactory.createVolvo240("VOLVO" + Math.random()));
                }
                cars.getLast().getPosition().move(0, height + 60);
            }
            cars.getLast().moveRight();

            timerListener.notifyCarAdd();
        }
    }



    void removeCar(){
        if (!cars.isEmpty()) {
            cars.removeLast();
            timerListener.notifyCarRemove();
        }
    }

    public class TimerListener implements ActionListener {


        public ArrayList<Observer> observers = new ArrayList<>();

        public void addObserver(Observer observer) {
            observers.add(observer);
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
                observer.notifyCarAdd(cars.getLast().getModelName(), cars.getLast().getRegistrationNr());
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


            }
            notifyObservers();
        }
    }

}
