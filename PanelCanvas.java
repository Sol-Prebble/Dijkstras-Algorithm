import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        this.setBackground(Color.GRAY);
        for(Node n : graph.getAllNodes()){
            /* edges */
            ArrayList<Edge> currentEdges = n.getEdges();
            for(int e = 0; e < currentEdges.size(); e++){
                Edge currentEdge = currentEdges.get(e);
                g.setColor(currentEdge.getColor());
                int startNodeCentreX = n.getX() + (n.getRadius() / 2);
                int startNodeCentreY = n.getY() + (n.getRadius() / 2);
                int targetNodeCentreX = currentEdge.getTargetNode().getX() + (n.getRadius() / 2);
                int targetNodeCentreY = currentEdge.getTargetNode().getY() + (n.getRadius() / 2);
                g.drawLine(startNodeCentreX, startNodeCentreY, targetNodeCentreX, targetNodeCentreY);
              }
            
            /* nodes */
            g.setColor(n.getColor());

            g.fillOval(n.getX(),n.getY(),n.getRadius(),n.getRadius());
            
            Font customFont = new Font("SansSerif", Font.BOLD, 18);
            g.setFont(customFont);
            
            /* nodes */
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