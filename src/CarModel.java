import java.util.ArrayList;

public class CarModel {
    private ArrayList<Vehicle> cars = new ArrayList<>();
    public ArrayList<Vehicle> getCars() {
        return cars;
    }
    protected CarModel(){
        createCarList();
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

    public void moveit(Vehicle car, int x, int y){
        //String name=car.getRegistrationNr();

            car.getPosition().move(x,y);

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
