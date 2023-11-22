import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class testWorkshop{

    private Workshop <Car> carWorkshop;
    private Volvo240 myVolvo;
    private Saab95 bestSaab;
    @Before
    public void init() {

        carWorkshop = new Workshop("carWorkshop",6);
        bestSaab = new Saab95("TestSaab");
        myVolvo = new Volvo240("TestVolvo");
    }
    @Test
    public void testLoad() {
        carWorkshop.loadHelper.load(myVolvo);
        assert !carWorkshop.loadHelper.getCarList().isEmpty();

    }
    @Test
    public void testUnload() {
        carWorkshop.loadHelper.load(myVolvo);
        carWorkshop.loadHelper.unload();
        assert carWorkshop.loadHelper.getCarList().isEmpty();
    }
    @Test
    public void testDuplicateLoad() {
        carWorkshop.loadHelper.load(myVolvo);
        carWorkshop.loadHelper.load(myVolvo);
        assert carWorkshop.loadHelper.getCarList().size()==1;

    }
}