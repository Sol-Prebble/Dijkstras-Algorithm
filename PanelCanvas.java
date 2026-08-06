import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Creates and maintains the GUI window
 *
 * @author Sol Prebble
 * @version 6-8-26
 */
public class PanelCanvas extends JPanel 
{
    private Graph graph;
    private Map<String, Color> colorPalette;
    /**
     * Constructor for objects of class PanelCanvas
     */
    public PanelCanvas(ArrayList<Node> nodes, Map<String, Color> colorPalette)
    {
        this.colorPalette = colorPalette;
        this.graph = new Graph(nodes);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(colorPalette.get("background"));
        /* edges */
        for(Node n : graph.getAllNodes()){
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
        }
        /* Nodes */
        for(Node n : graph.getAllNodes()){
            /* node circle */
            g.setColor(n.getColor());
            g.fillOval(n.getX(),n.getY(),n.getRadius(),n.getRadius());

            Font customFont = new Font("SansSerif", Font.BOLD, 18);
            g.setFont(customFont);

            /* text */
            String text = String.valueOf(n.getName());
            FontMetrics fm = g.getFontMetrics();

            // Calculate centering X
            int textWidth = fm.stringWidth(text);
            int centreX = n.getX() + (n.getRadius() - textWidth) / 2;

            // Calculate centering Y
            int textHeight = fm.getHeight();
            int textAscent = fm.getAscent();
            int centreY = n.getY() + (n.getRadius() - textHeight) / 2 + textAscent;

            if(n.getVisited()){
                g.setColor(colorPalette.get("visitedText"));
            } else {
                g.setColor(colorPalette.get("text"));
            }

            g.drawString(text, centreX, centreY);

        }
    }

    /**
     * Creates a new graphBuilder and extracts the graph
     * @param nodes(list)
     * @return Graph (obejct)
     */
    private Graph setUpGraph(ArrayList<Node> nodes){
        GraphBuilder graphBuilder = new GraphBuilder(colorPalette);
        this.graph = graphBuilder.getGraph();
        return graph;
    }

}