import org.w3c.dom.ranges.Range;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class DrawPanel extends JPanel {
    private HashMap<String, BufferedImage> carImages = new HashMap();
    private HashMap<String, Point> carPositions;
    private int frameSizeX;
    private final int imageSizeX = 100;

    public void setPositionHashMap(HashMap hmPos){

        this.carPositions = new HashMap<>(hmPos);
        //this.carPositions = hmPos;

    }

    public void setFrameSizeX(int x){
        this.frameSizeX = x;
    }

    public int getFrameEdgeX(){
        int edgeX = frameSizeX-imageSizeX;
        return edgeX;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (String key : carImages.keySet()){

            g.drawImage(carImages.get(key), carPositions.get(key).x, carPositions.get(key).y, null);

        }

    }


    public DrawPanel(int x, int y) {
        System.out.println("Konstruktor");
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ
            //ArrayList<BufferedImage> carsDraw = new ArrayList<>();
            carImages.put("Volvo240", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImages.put("Saab95", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImages.put("Scania", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));

            /*carsToDraw= new ArrayList<>();
            carsToDraw.add(new carDraw( "Yo", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")),new Point()));
            carsToDraw.add(new carDraw("Tja",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")),new Point()));
            carsToDraw.add(new carDraw("Hej",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")),new Point()));*/


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}