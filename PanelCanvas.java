import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class PanelCanvas here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PanelCanvas extends JPanel
{
    private Graph graph;
    /**
     * Constructor for objects of class PanelCanvas
     */
    public PanelCanvas(Graph graph)
    {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Node n : graph.getAllNodes()){
            g.setColor(Color.DARK_GRAY);

            g.fillOval(n.getX(),n.getY(),n.getRadius(),n.getRadius());
            
            Font customFont = new Font("SansSerif", Font.BOLD, 18);
            g.setFont(customFont);
            
            String text = String.valueOf(n.getName());
            FontMetrics fm = g.getFontMetrics();
            
            // Calculate centering X
            int textWidth = fm.stringWidth(text);
            int centreX = n.getX() + (n.getRadius() - textWidth) / 2;

            // Calculate centering Y
            int textHeight = fm.getHeight();
            int textAscent = fm.getAscent();
            int centreY = n.getY() + (n.getRadius() - textHeight) / 2 + textAscent;

            g.setColor(Color.WHITE);
            
            g.drawString(text, centreX, centreY);
        }
    }
}