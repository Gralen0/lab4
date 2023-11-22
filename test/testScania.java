import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testScania {

    private Scania truck;

    @Before
    public void init(){
        truck = new Scania("TestScania");
    }
    @Test
    public void testRampRaise(){
        truck.raiseRamp(5);
        assert truck.getTruckBedIncline() == 5;
    }
    @Test
    public void testRampLower(){
        truck.raiseRamp(40);
        truck.lowerRamp(5);
        assert truck.getTruckBedIncline() == 35;
    }
    @Test
    public void testMaxTruckBedIncline(){
        truck.raiseRamp(60);
        truck.raiseRamp(20);
        assert truck.getTruckBedIncline() == 70;
    }
    @Test
    public void testMinTruckBedIncline(){
        truck.raiseRamp(10);
        truck.lowerRamp(20);
        assert truck.getTruckBedIncline() == 0;
    }
    @Test
    public void testTruckGas(){
        truck.raiseRamp(5);
        truck.gas(0.5);
        assert truck.getCurrentSpeed() == 0;
    }

}
