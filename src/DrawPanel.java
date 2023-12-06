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

public class DrawPanel extends JPanel implements Observer{

    private ArrayList<carDraw> carsToDraw = new ArrayList<>();
    private int frameBoundaryX;
    private int imageWidth = 100;

    @Override
    public void moveIt(String carName, int x, int y){
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
        this.frameBoundaryX = x - imageWidth;

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

    @Override
    public void update(){
        this.repaint();
    }

    @Override
    public void notifyCarAdd(String modelName, String registrationNumber){
        try {
            carsToDraw.add(new carDraw( registrationNumber,ImageIO.read(DrawPanel.class.getResourceAsStream("pics/"+modelName+".jpg")),new Point()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void notifyCarRemove(){
        carsToDraw.removeLast();
    }
}