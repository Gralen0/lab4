import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class testVehicle {
    private Saab95 vehicle;

    @Before
    public void init() {
        vehicle = new Saab95("TestSaab");
    }
    @Test
    public void testGetNrDoors() {
        assert vehicle.getNrDoors() == 2;
    }
    @Test
    public void testGetEnginePower() {
        assert vehicle.getEnginePower() == 125;
    }
    @Test
    public void testGetColor() {
        assert vehicle.getColor() == Color.red;
    }
    @Test
    public void testSetColor() {
        vehicle.setColor(Color.black);
        assert vehicle.getColor() == Color.black;
    }
    @Test
    public void testGetModelName() {
        assert vehicle.getModelName().equals("Saab95");
    }
    @Test
    public void testSetModelName() {
        vehicle.setModelName("Saab21");
        assert vehicle.getModelName().equals("Saab21");
    }

    @Test
    public void testGetPosition() {
        vehicle.getPosition().move(2, 10);
        assert vehicle.getPosition().getY() == 10;
        assert vehicle.getPosition().getX() == 2;
    }

    @Test
    public void testGetDirection() {
        assert vehicle.getDirection().equals("North");
    }
    @Test
    public void testStartEngine() {
        vehicle.startEngine();
        assert vehicle.getCurrentSpeed() == 0.1;
    }
    @Test
    public void testStopEngine() {
        vehicle.startEngine();
        vehicle.stopEngine();
        assert vehicle.getCurrentSpeed() == 0;
    }
    @Test
    public void testGetCurrentSpeed() {
        vehicle.gas(0.5);
        assert vehicle.getCurrentSpeed() == 0.625;
    }
    @Test
    public void testGas() {
        vehicle.startEngine();
        vehicle.gas(0.5);
        assert vehicle.getCurrentSpeed() == 0.725;
        double speed = vehicle.getCurrentSpeed();
        vehicle.gas(5);
        assert speed == vehicle.getCurrentSpeed(); //Detta visar att gas avfärdar värdet.
    }
    @Test
    public void testBrake() {
        vehicle.gas(1);
        vehicle.brake(0.5);
        assert vehicle.getCurrentSpeed() == 0.625;
        double speed = vehicle.getCurrentSpeed();
        vehicle.brake(5);
        assert speed == vehicle.getCurrentSpeed(); //Detta visar att brake avfärdar värdet.
    }
    @Test
    public void testMove() {
        assert vehicle.getPosition().getX() == 0;
        assert vehicle.getPosition().getY() == 0;
        vehicle.gas(0.5);
        vehicle.move();
        vehicle.moveLeft();
        vehicle.move();
        assert vehicle.getPosition().getX() == -1;
        assert vehicle.getPosition().getY() == 1;
    }
    @Test
    public void testMoveLeft() {
        assert vehicle.getDirection().equals("North");
        vehicle.moveLeft();
        assert vehicle.getDirection().equals("West");
    }
    @Test
    public void testMoveRight() {
        assert vehicle.getDirection().equals("North");
        vehicle.moveRight();
        assert vehicle.getDirection().equals("East");
    }
    @Test
    public void testGetRegistrationNr(){
        assert vehicle.getRegistrationNr().equals("TestSaab");
    }
}


