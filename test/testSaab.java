import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class testSaab {
    private Saab95 saab;

    @Before
    public void init() {
        saab = new Saab95("TestSaab");
    }
    @Test
    public void testSpeedFactor() {
        assert saab.speedFactor() == 1.25;
        saab.setTurboOn();
        assert saab.speedFactor() == 1.625;
    }
    @Test
    public void testGetTurboOn() {
        saab.setTurboOn();
        assert saab.getTurboOn();
    }
    @Test
    public void testSetTurboOn(){
        saab.setTurboOn();
        assert saab.getTurboOn();
    }
    @Test
    public void testSetTurboOff(){
        saab.setTurboOn();
        saab.setTurboOff();
        assert !saab.getTurboOn();
    }

}