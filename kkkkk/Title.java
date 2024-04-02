
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title extends JPanel{
    
    private final int FRAME_WIDTH = 1366;
    private final int FRAME_HEIGHT = 768;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    // About Font
    private Font WatchOutFont ,SqaureFont, KruskalFont, PrimFont, DijkstraFont,Finite_automataFont;
    
    // Color
    private Color tomato = new Color(255, 87, 87);
    private Color offWhite = new Color(250, 249, 246);
    private Color black = new Color(25, 28, 32);
    
    // Label
    private JLabel title_p1, title_p2;
    
    // Button
    protected JLabel Kruskal, Dijkstra, Prim,Finite_automata;
    
    // Arrow Btn
    protected Polygon triangleLeft, triangleRight;
    private int pointX_triangleLeft = 490;
    private int pointX_triangleRight = 800;
    private int pointY_triangle = 435; 
    private int sizeTriangleBox = 35;
    // Arrow Btn Right
    private int[] xLeft = {sizeTriangleBox + pointX_triangleLeft, sizeTriangleBox + pointX_triangleLeft, pointX_triangleLeft};
    private int[] yLeft = {pointY_triangle, sizeTriangleBox + pointY_triangle, pointY_triangle + sizeTriangleBox / 2};
    // Arrow Btn Right
    private int[] xRight = {pointX_triangleRight, pointX_triangleRight, pointX_triangleRight + sizeTriangleBox};
    private int[] yRight = {pointY_triangle, sizeTriangleBox + pointY_triangle, pointY_triangle + sizeTriangleBox / 2};
    
    // Footer backgound
    // private Rectangle floor = new Rectangle(0, 600, FRAME_WIDTH + 50, 200);
    // private Rectangle Box = new Rectangle(1000, 555, 70, 70);
    
    // Constructor to setup Component and Event handler
    public Title(){
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(offWhite);

        // Watch Out Screen Label
        title_p1 = new JLabel();
        title_p1.setText("Select");
        title_p1.setBounds(450, 258, 454, 104);
        title_p1.setForeground(black);
        WatchOutFont = usingFontsBold(WatchOutFont, 65f, "font/ShantellSans-Regular.ttf");
        title_p1.setFont(WatchOutFont);
        add(title_p1);

        // SQUARE Screen Label
        title_p2 = new JLabel();
        title_p2.setText("Tree");
        title_p2.setBounds(708, 237, 405, 128);
        SqaureFont = usingFontsBold(SqaureFont, 90f, "font/Oswald-Medium.ttf");
        title_p2.setFont(SqaureFont);
        title_p2.setForeground(tomato);
        add(title_p2);

        // Button Arrow Left and Right
        triangleLeft = new Polygon(xLeft,yLeft, 3);
        triangleRight = new Polygon(xRight,yRight, 3);
        

        // Button Kruskal ( use Label)
        Kruskal = new JLabel();
        Kruskal.setText("Kruskal");
        Kruskal.setOpaque(false); // show bg color 
        Kruskal.setBackground(Color.RED);
        Kruskal.setBounds(620 , 430, 155, 45);
        KruskalFont = usingFontsBold(KruskalFont, 25f, "font/ShantellSans-Regular.ttf");
        Kruskal.setForeground(black);
        Kruskal.setFont(KruskalFont);
        add(Kruskal);
        System.out.println("Kruskal's showing");
        
        // Button Dijkstra (use Label)
        Dijkstra = new JLabel();
        Dijkstra.setText("Dijkstra");
        Dijkstra.setOpaque(false); // show bg color 
        Dijkstra.setBackground(Color.RED);
        Dijkstra.setBounds(620 , 430, 150, 45);
        DijkstraFont = usingFontsBold(DijkstraFont, 25f, "font/ShantellSans-Regular.ttf");
        Dijkstra.setForeground(black);
        Dijkstra.setFont(DijkstraFont);

        // Button Prim (use Label)
        Prim = new JLabel();
        Prim.setText("Prim");
        Prim.setOpaque(false); // show bg color 
        Prim.setBackground(Color.RED);
        Prim.setBounds(630 , 430, 100, 45);
        PrimFont = usingFontsBold(PrimFont, 25f, "font/ShantellSans-Regular.ttf");
        Prim.setForeground(black);
        Prim.setFont(PrimFont);

         // Button Finite_automata (use Label)
        Finite_automata = new JLabel();
        Finite_automata.setText("Finite_automata");
        Finite_automata.setOpaque(false); // show bg color 
        Finite_automata.setBackground(Color.RED);
        Finite_automata.setBounds(560 , 430, 220, 45);
        Finite_automataFont = usingFontsBold(Finite_automataFont, 25f, "font/ShantellSans-Regular.ttf");
        Finite_automata.setForeground(black);
        Finite_automata.setFont(Finite_automataFont);

        // Event-Handler Section
        addMouseListener(new ArrowAction()); // click arrow to change label
        addMouseMotionListener(new MousePositionCheck());
        addMouseMotionListener(new HoverButton());
    }
    // Main PaintComponents Method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); // for smoothly rect
        paintArrowBtn(g);
        // paintFloor(g2d);
        // paintBox(g2d);
    }
    
    // Using Fonts Method Section
    // public Font usingFonts(Font fontName,  float sizeFont, String fontFilePath_ttf){
    //     try {
    //         fontName = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)).deriveFont(sizeFont);
    //         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    //         ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)));
    //         return fontName;
    //     } catch (IOException|FontFormatException e){
    //         // Handle Exception
    //         System.out.println("Font Problems Please Check");
    //         return null;
    //     }
    // }
    public Font usingFontsBold(Font fontName,  float sizeFont, String fontFilePath_ttf){
        try {
            fontName = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)).deriveFont(Font.BOLD,sizeFont);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)));
            return fontName;
        } catch (IOException|FontFormatException e){
            // Handle Exception
            System.out.println("Font Problems Please Check");
            return null;
        }
    }
    
    // Paint Method Section
    // public void paintFloor(Graphics2D g2d){
    //     g2d.setColor(black);
    //     AffineTransform tfFloor = new AffineTransform();
    //     tfFloor.translate(-10, 42);
    //     tfFloor.rotate(Math.toRadians(177), floor.getCenterX(), floor.getCenterY());
    //     Shape tfShapeFloor = tfFloor.createTransformedShape(floor);
    //     g2d.fill(tfShapeFloor);
    // }
    public void paintArrowBtn(Graphics g){
        g.setColor(black);
        g.fillPolygon(triangleLeft);
        g.fillPolygon(triangleRight);
    }
    // public void paintBox(Graphics2D g2d){
    //     g2d.setColor(black);
    //     AffineTransform tfBox = new AffineTransform();
    //     tfBox.rotate(Math.toRadians(177), Box.getCenterX(), Box.getCenterY());
    //     Shape tfShapeBox = tfBox.createTransformedShape(Box);
    //     g2d.fill(tfShapeBox);
    // }

    // Inner Class for Event Handler Section
    private class MousePositionCheck extends MouseAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
        // Check mouse's position
        // System.out.println("mouseX:" + e.getX() + ", mouseY:" + e.getY());
        }
    }
    private class ArrowAction extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (triangleLeft.contains(e.getPoint()) || triangleRight.contains(e.getPoint())){
                System.out.println(" ==> Triangle's clicked");
                if (Kruskal.isShowing()){
                    remove(Kruskal);
                    add(Prim);
                    revalidate();
                    repaint();
                    System.out.println("Prim's showing");
                }else if (Prim.isShowing()){
                    remove(Prim);
                    add(Dijkstra);
                    validate();
                    repaint();
                    System.out.println("Dijkstra's showing");
                }else if (Dijkstra.isShowing()){
                    remove(Dijkstra);
                    add(Finite_automata);
                    validate();
                    repaint();
                    System.out.println("Finite_automata's showing");
                }else if (Finite_automata.isShowing()){
                    remove(Finite_automata);
                    add(Kruskal);
                    validate();
                    repaint();
                    System.out.println("Kruskal's showing");
                }
            }
        }
    }
    private class HoverButton extends MouseAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            if (Kruskal.getBounds().contains(e.getPoint())){
                // System.out.println("point at Kruskal button");
                Kruskal.setForeground(black.brighter().brighter().brighter().brighter());
                Prim.setForeground(black.brighter().brighter().brighter().brighter());
                Dijkstra.setForeground(black.brighter().brighter().brighter().brighter());
                Finite_automata.setForeground(black.brighter().brighter().brighter().brighter());
            }else{
                Kruskal.setForeground(black);
                Prim.setForeground(black);
                Dijkstra.setForeground(black);
                Finite_automata.setForeground(black);

            }
        }
        
        
    }

    public static void main(String[] args) {
    JFrame frame = new JFrame("Discreat Math");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new Title());
    frame.pack();
    frame.setVisible(true);
}

}