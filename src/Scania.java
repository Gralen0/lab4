import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Scania extends Truck {

    private int truckBedIncline = 0;

    public Scania(String name) {
        setNrDoors(2);
        setColor(Color.black);
        setEnginePower(600);
        setModelName("Scania");
        stopEngine();
        setRegistrationNr(name);
    }

    public int getTruckBedIncline() {
        return truckBedIncline;
    }

    private void setTruckBedIncline(int truckBedIncline) {
        this.truckBedIncline = truckBedIncline;
    }


    public void raiseRamp(int raiseAmount) {
        if (getCurrentSpeed() == 0) {
            if ((getTruckBedIncline() + raiseAmount) <= 70) {
                setTruckBedIncline(getTruckBedIncline() + raiseAmount);
            } else {
                setTruckBedIncline(70);
            }
        }
    }

    public void lowerRamp(int raiseAmount) {
        if (getCurrentSpeed() == 0) {
            if ((getTruckBedIncline() - raiseAmount) >= 0) {
                setTruckBedIncline(getTruckBedIncline() - raiseAmount);
            } else {
                setTruckBedIncline(0);
            }
        }
    }

    @Override
    public void gas(double amount) {
        if (getTruckBedIncline() == 0) {
            super.gas(amount);
        } else {
            System.out.println("ERROR: Ramp is still down.");
        }
    }

}
