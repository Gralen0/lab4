public class App {
    public static void main(String[] args) {
        CarController cc = new CarController();
        addObserverMethod(cc);
    }

    private static void addObserverMethod(CarController cc){
        cc.model.timerListener.addObserver(cc.frame.drawPanel);
    }
}
