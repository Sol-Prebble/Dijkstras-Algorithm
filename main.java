import java.util.ArrayList;
import javax.swing.*;
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
    public static void main(String[] args){
        
        
        
        
        
        Graph graph = new Graph();
        
        defaultGraph(graph);
        PathFinder newPath = new PathFinder(graph);
        newPath.runAlgorithm(start, target);
        
        JFrame window = new JFrame("Dijkstra's algorithm");
        
        
        PanelCanvas canvas = new PanelCanvas(graph);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(canvas);
        window.setSize(800,500);
        window.toFront();
        window.setVisible(true);
    }
    private static void defaultGraph(Graph graph){
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        
        /* temp code */
        ArrayList<Edge> aEdges = new ArrayList<>();
        Node zero = new Node(0, aEdges);
        
        nodes.add(zero);
        ArrayList<Edge> bEdges = new ArrayList<>();
        Node one = new Node(1, bEdges);
        
        nodes.add(one);
        ArrayList<Edge> cEdges = new ArrayList<>();
        Node two = new Node(2, cEdges);
        
        nodes.add(two);
        
        /* edges */
        Edge edgeA = new Edge(two,3);
        aEdges.add(edgeA);
        Edge edgeAA = new Edge(one,4);
        aEdges.add(edgeAA);
        
        Edge edgeB = new Edge(two,5);
        bEdges.add(edgeB);
        
        for(int x = 0; x < nodes.size(); x++){
            graph.addNode(nodes.get(x));
        }
        
        start = zero;
        target = two;
    }
}