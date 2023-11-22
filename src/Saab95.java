import java.awt.*;

public class Saab95 extends Car{

    private boolean turboOn;


    public Saab95(String name){
        setNrDoors(2);
        setColor(Color.red);
        setEnginePower(125);
        setModelName("Saab95");
        setTurboOff();
        stopEngine();
        setRegistrationNr(name);

    }

    public boolean getTurboOn() { return turboOn; }
    public void setTurboOn(){ this.turboOn = true; }
    public void setTurboOff(){ this.turboOn = false; }


    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }


}
