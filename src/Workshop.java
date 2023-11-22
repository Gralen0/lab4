import java.awt.*;
import java.util.ArrayList;

public class Workshop<T extends Vehicle>{



    private final String workshopName;
    private final int amountCars;
    public Load<T> loadHelper;

    public Workshop(String name,int amountCars){
        this.amountCars=amountCars;
        this.workshopName=name;
        this.loadHelper = new Load<>(amountCars);

    }



}
