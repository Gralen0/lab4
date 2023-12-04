import java.awt.*;
import java.util.ArrayList;

public class Cartransport extends Truck{

    private final int carAmount;
    public Load<Car> loadHelper;
    private int loadDistanceX;
    private int loadDistanceY;

    public Cartransport(String name, int carAmount){
        this.carAmount = carAmount;
        setNrDoors(2);
        setColor(Color.black);
        setEnginePower(600);
        setModelName("Transporter");
        stopEngine();
        setRegistrationNr(name);
        loadDistanceX = 1;
        loadDistanceY = 1;
        loadHelper = new Load<>(carAmount);
    }

    public boolean closeBy(Car car) {
        //Kontrollera att bilen som ska lastas är rimligt nära biltransporten
        return ((car.getPosition().getX() < this.getPosition().getX() + loadDistanceX && car.getPosition().getX() > this.getPosition().getX() - loadDistanceX) &
            (car.getPosition().getY() < this.getPosition().getY() + loadDistanceY && car.getPosition().getY() > this.getPosition().getY() - loadDistanceY));
    }

    public void load(Car car){
        if (!getRampRaised()){
                    if (closeBy(car)){
                        loadHelper.load(car);
                    }else{
                        System.out.println("ERROR: The car is too far away.");
                    }

        }else {
            System.out.println("ERROR: The ramp is not in the correct position.");
            }
    }


    public void unload(){
            if (!getRampRaised()){
                loadHelper.unload();
            }else {
                System.out.println("ERROR: The ramp is not in the correct position.");
            }
        }

    @Override
    public void gas(double amount){
        if (getRampRaised()){
            super.gas(amount);
        }
        else{
            System.out.println("ERROR: Ramp is still down.");
        }
    }

    @Override
    public void move() {
        super.move();
        for (Vehicle i : loadHelper.getCarList()) {
            i.getPosition().move((int)this.getPosition().getX(),(int)this.getPosition().getY());
        }
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        for (Car i : loadHelper.getCarList())   {
            i.moveLeft();
            }
        }

    @Override
    public void moveRight() {
        super.moveRight();
        for (Vehicle car : loadHelper.getCarList()) {
            car.moveRight();
        }
    }


}
