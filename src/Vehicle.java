import java.awt.*;

public abstract class Vehicle implements Movable{

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private Point position = new Point( 0, 0);
    private final String[] directions = {"North", "East", "South", "West"};
    private int currentDirection = 0;
    private String registrationNr;


    public String getRegistrationNr(){
        return this.registrationNr;
    }
    public void setRegistrationNr(String registrationNumber){
        registrationNr=registrationNumber;
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public void setNrDoors(int nrDoors) {
        this.nrDoors = nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }
    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    public double getCurrentSpeed(){ return currentSpeed; }
    private void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Point getPosition(){
        return position;
    }

    private int getCurrentDirection() {
        return currentDirection;
    }
    private void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }

    public String getDirection(){
        return directions[currentDirection];
    }

    public abstract double speedFactor();

    public void startEngine(){ setCurrentSpeed(0.1); }
    public void stopEngine(){ setCurrentSpeed(0); }

    private void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower()));}

    private void decrementSpeed(double amount){ setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));}

    public void gas(double amount){
        if (0.0<=amount & amount<=1.0 ){incrementSpeed(amount);}
    }

    public void brake(double amount){
        if (0 <=amount & amount <= 1) {decrementSpeed(amount);}
    }

    @Override
    public void move() {

        switch(getDirection()) {
            case "North":
                //+Y
                getPosition().move((int)getPosition().getX(),   (int)getPosition().getY()+(int)Math.round(getCurrentSpeed()));
                break;

            case "East":
                //+X
                getPosition().move((int)getPosition().getX()+(int)Math.round(getCurrentSpeed()), (int)getPosition().getY());
                break;

            case "South":
                //-Y
                getPosition().move((int)getPosition().getX(),   (int)getPosition().getY()-(int)Math.round(getCurrentSpeed()));
                break;

            case "West":
                //-X
                getPosition().move((int)getPosition().getX()-(int)Math.round(getCurrentSpeed()),(int)getPosition().getY());
                break;
        }
    }

    @Override
    public void moveLeft() {
        setCurrentDirection(getCurrentDirection()-1);
        if (getCurrentDirection() < 0){
            setCurrentDirection(3);
        }
    }

    @Override
    public void moveRight() {
        setCurrentDirection(getCurrentDirection()+1);
        if (getCurrentDirection() > 3){
            setCurrentDirection(0);
        }
    }
}
