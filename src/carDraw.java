import java.awt.*;
import java.awt.image.BufferedImage;

public class carDraw{

    public String typ;
    public BufferedImage image;
    public Point point;
    public carDraw(String typ, BufferedImage image, Point p){
        this.typ = typ;
        this.image = image;
        this.point = p;
    }
}