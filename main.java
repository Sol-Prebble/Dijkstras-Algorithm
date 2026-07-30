import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    private static Node start;
    private static Node target;
    private static Map<String, Color> colorPalette;
    private static Graph graph;

    /**
     * Layered Panes: https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html
     */
    public static void main(String[] args){
        ArrayList<Node> nodes = new ArrayList<>(); // arraylist for all of the nodes in the graph
        
        /* Color palette */
        Theme theme = new Theme();
        // colorPalette = theme.getPalette();

        /* Graph */
        GraphBuilder graphBuilder = new GraphBuilder();
        graph = graphBuilder.getGraph();
        nodes = graph.getAllNodes();
        
        PanelCanvas canvas = new PanelCanvas(nodes, theme);
        PathFinder newPath = new PathFinder(graph, canvas);
        
        ControlPanel controlPanel = new ControlPanel(theme, canvas, nodes, newPath, graph);
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