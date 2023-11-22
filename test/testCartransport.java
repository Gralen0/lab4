import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class testCartransport {
    private Cartransport carT;
    private Saab95 saab;
    private Volvo240 volvo;
    private Load<Car> loadHelper;

    @Before
    public void init(){
        carT = new Cartransport("TestCarT", 1);
        saab = new Saab95("TestSaab");
        volvo = new Volvo240("TestVolvo");
        loadHelper = new Load<>(5);
    }

    @Test
    public void testGetRampRaised(){
        assert !carT.getRampRaised();
    }

    @Test
    public void testRaiseRamp(){
        carT.raiseRamp();
        assert carT.getRampRaised();
    }
    @Test
    public void testLowerRamp(){
        carT.raiseRamp();
        carT.lowerRamp();
        assert !carT.getRampRaised();
    }
    @Test
    public void testCloseBy(){
        assert carT.closeBy(saab);
        saab.gas(1);
        saab.move();
        assert !carT.closeBy(saab);
    }
    @Test
    public void testLoad(){
        carT.load(saab);
        assert carT.loadHelper.getCarList().getFirst() == saab;
        carT.load(volvo);
        assert carT.loadHelper.getCarList().size() == 1;
    }
    @Test
    public void testUnLoad(){
        carT.load(saab);
        carT.unload();
        assert carT.loadHelper.getCarList().isEmpty();
    }
    @Test
    public void testShowLoad(){
        carT.load(saab);
        System.out.println(carT.loadHelper.showLoad());
        assert carT.loadHelper.showLoad().equals("TestSaab | ");
    }
    @Test
    public void testGas(){
        carT.gas(1);
        carT.move();
        //Ramp fortfarande nere så rör sig inte
        carT.raiseRamp();
        carT.gas(1);
        carT.move();
        //Ramp uppe så flyttar sig till y=6
        assert (carT.getPosition().getX() == 0) && (carT.getPosition().getY() == 6);
    }
    @Test
    public void testMove(){
        carT.load(saab);
        carT.raiseRamp();
        carT.gas(1);
        carT.move();
        assert (carT.getPosition().getX() == 0) && (carT.getPosition().getY() == 6);
        assert (saab.getPosition().getX() == 0) && (saab.getPosition().getY() == 6);
    }
    @Test
    public void testMoveLeft(){
        carT.load(saab);
        carT.raiseRamp();
        carT.moveLeft();
        assert saab.getDirection().equals("West");
        assert carT.getDirection().equals("West");
    }
    @Test
    public void testMoveRight(){
        carT.load(saab);
        carT.raiseRamp();
        carT.moveRight();
        assert saab.getDirection().equals("East");
        assert carT.getDirection().equals("East");
    }
}
