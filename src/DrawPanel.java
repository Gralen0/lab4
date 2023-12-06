import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    //BufferedImage volvoImage;

    // To keep track of a singel cars position
    private ArrayList<carDraw> carsToDraw;
    private int frameBoundaryX;


    // TODO: Make this genereal for all cars
    void moveit(String carName, int x, int y){
        for (carDraw carToCheckName: carsToDraw){
            if (carToCheckName.name.equals(carName)){
                carToCheckName.point.x=x;
                carToCheckName.point.y=y;
            }
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);


        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            //TODO Factory pattern?
            carsToDraw= new ArrayList<>();
            carsToDraw.add(new carDraw( "VVO240",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")),new Point()));
            carsToDraw.add(new carDraw("SAB095",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")),new Point()));
            carsToDraw.add(new carDraw("SCA180",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")),new Point()));

            this.frameBoundaryX = x - carsToDraw.getFirst().image.getWidth();

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public int getFrameBoundaryX() {
        return frameBoundaryX;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (carDraw car : carsToDraw){
            g.drawImage(car.image, car.point.x, car.point.y, null); // see javadoc for more info on the parameters
        }
    }
}