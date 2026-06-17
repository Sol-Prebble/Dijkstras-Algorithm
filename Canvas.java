import java.awt.*;
import javax.swing.*;
/**
 * Canvas for the graphics
 *
 * @author Sol prebble
 * @version 11/6/26
 */
public class Canvas extends JFrame
{
    /**
     * Constructor for objects of class Canvas
     */
    public Canvas(Graph graph)
    {
        
        setTitle("Animation of Dijkstra's Algorithm");
        
        this.getContentPane().setPreferredSize(new Dimension(500,500));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /* main code */
        //CustomDrawingPanel panel = new CustomDrawingPanel();
        
        
        /* make window appear */
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    public void paint(Graphics g){
        int nodeSize = 75;
        g.fillOval(nodeSize,nodeSize,nodeSize,nodeSize);
    }
    
}