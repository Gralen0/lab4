import java.awt.*;

public class Volvo240 extends Car{

    private double trimFactor;

    public Volvo240(String name){
        setNrDoors(4);
        setColor(Color.black);
        setEnginePower(100);
        setModelName("Volvo240");
        setTrimFactor(1.25);
        stopEngine();
        setRegistrationNr(name);
    }

    public double getTrimFactor() {
        return this.trimFactor;
    }
    public void setTrimFactor(double trimFactor) {
        this.trimFactor = trimFactor;
    }


    public double speedFactor(){
        return getEnginePower() * 0.01 * getTrimFactor();
    }


}
