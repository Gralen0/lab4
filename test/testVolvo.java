import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class testVolvo {

    private Volvo240 volvo;
    @Before
    public void init(){
        volvo = new Volvo240("TestVolvo");
    }
    @Test
    public void testGetTrimFactor(){
        assert volvo.getTrimFactor() == 1.25;
    }
    @Test
    public void testSetTrimFactor(){
        volvo.setTrimFactor(0.5);
        assert volvo.getTrimFactor() == 0.5;
    }
    @Test
    public void testSpeedFactor() {
        assert volvo.speedFactor() == 1.25;
    }



}
