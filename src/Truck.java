public abstract class Truck extends Vehicle {
    private boolean rampRaised = false;
    // Ramp nere (false) betyder att bilen inte går att köras och går att loadas

    public double speedFactor(){
        return getEnginePower() * 0.01;
    }

    public boolean getRampRaised(){
        return rampRaised;
    }
    private void setRampRaised(boolean bol){
        this.rampRaised = bol;
    }

    protected void raiseRamp() {
        setRampRaised(true);
    }

    protected void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            setRampRaised(false);
        }
    }




}
