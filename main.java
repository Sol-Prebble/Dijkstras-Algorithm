import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * sets up the program
 *
 * @author  Sol Prebble
 * @version 6-8-26
 */
public class main
{
    /**
     * Layered Panes: https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html
     */
    public static void main(String[] args){
        ArrayList<Node> nodes = new ArrayList<>(); // arraylist for all of the nodes in the graph
        
        /* Color palette */
        Theme theme = new Theme();
        // colorPalette = theme.getPalette();

        /* Graph */
        GraphBuilder graphBuilder = new GraphBuilder(theme.getPalette());
        Graph graph = graphBuilder.getGraph();
        nodes = graph.getAllNodes();
        
        PanelCanvas canvas = new PanelCanvas(nodes, theme.getPalette());
        PathFinder newPath = new PathFinder(graph, canvas);
        
        ControlPanel controlPanel = new ControlPanel(theme, canvas, nodes, newPath, graphBuilder);
        JFrame window = new JFrame("Dijkstra's algorithm");
        
        
        
        
        /* canvas */
        canvas.setBounds(0,0,800,500);


        /* Layered Pane */
        
        window.add(controlPanel.getLayeredPane());

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,500);
        window.toFront();
        window.setVisible(true);
    }
}