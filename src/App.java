public class App {
    public static void main(String[] args) {
        CarController cc = new CarController();

        cc.model.timerListener.addObserver(cc.frame.drawPanel);


    }
}
