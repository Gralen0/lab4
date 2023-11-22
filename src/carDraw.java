import java.awt.*;
import java.awt.image.BufferedImage;

public class carDraw{

    public String name;
    public BufferedImage image;
    public Point point;
    public carDraw(String name, BufferedImage image, Point p){
        this.name = name;
        this.image = image;
        this.point = p;
    }
}