import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 **/

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;


    DrawPanel drawPanel = new DrawPanel(X, Y-240);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");


    // Create a HashMap
    private Map<Vehicle, BufferedImage> CarImages = new HashMap<>();

    CarImages.put(Volvo240, ImageIO.read(.class.getResourceAsStream("pics/Volvo240.jpg");

    private ArrayList<BufferedImage> carsDraw = new ArrayList<>();


    // Constructor
    public CarView(String framename){
        initComponents(framename);
    }

    //TODO:Get buttons




    //TODO: Fixa så att imageSize finns tillgänglig i CarView
    public int getFrameEdgeX(){
        int edgeX = X-imageSize;
        return edgeX;
    }
    public int getFrameEdgeY(){
        int edgeY = Y-imageSize;
        return edgeY;
    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);


        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);


        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void repaint(String carName, int x ,int y){

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (carDraw car : carsToDraw){
            g.drawImage(car.image, car.point.x, car.point.y, null); // see javadoc for more info on the parameters
        }
    }
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
            // if you are starting in IntelliJ
            //ArrayList<BufferedImage> carsDraw = new ArrayList<>();
            carsDraw.add(ImageIO.read(CarView.class.getResourceAsStream("pics/Volvo240.jpg")));
            carsDraw.add(ImageIO.read(CarView.class.getResourceAsStream("pics/Saab95.jpg")));
            carsDraw.add(ImageIO.read(CarView.class.getResourceAsStream("pics/Scania.jpg")));

            /*carsToDraw= new ArrayList<>();
            carsToDraw.add(new carDraw( "Yo", ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")),new Point()));
            carsToDraw.add(new carDraw("Tja",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")),new Point()));
            carsToDraw.add(new carDraw("Hej",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")),new Point()));*/





        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

}