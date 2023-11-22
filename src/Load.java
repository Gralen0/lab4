import java.util.ArrayList;

public class Load <T extends Vehicle>{
    private ArrayList<T> carList = new ArrayList<>();
    private int amountCars;
    public Load(int tempAmountCars){
        this.amountCars = tempAmountCars;
    }

    public void load(T car){
        if (carList.size()<amountCars) {
            if (!carList.contains(car)){
                carList.add(car);
            }
            else {
                System.out.println("ERROR: Vehicle already in the cargo");
            }
        }
        else{
            System.out.println("ERROR: Cargo is full.");
        }

    }

    public void unload() {
        if (!carList.isEmpty()) {
        T unloadedcar = carList.get(carList.size()-1);
        System.out.println("Unloaded vehicle: "+unloadedcar.getClass());
        carList.removeLast();
    }else {
        System.out.println("ERROR: There are no cars to unload");
    }
    }


    public String showLoad() {
        String showLoadString ="";
        for (T i : carList) {
            showLoadString+=(i.getRegistrationNr() +" | ");}
        return showLoadString;
    }

    public ArrayList<T> getCarList() {
        return carList;
    }


}
