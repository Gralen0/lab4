public class VehicleFactory {
    public static Vehicle createVolvo240(String name){
        return new Volvo240(name);
    }
    public static Vehicle createSaab95(String name){
        return new Saab95(name);
    }
    public static Vehicle createScania(String name){
        return new Scania(name);
    }
}
