import java.util.*;
import java.awt.*;
/**
 * Creates a graph
 *
 * @author Sol Prebble
 * @version 6-8-26
 */
public class GraphBuilder
{
    private Graph graph = new Graph();
    private ArrayList<Node> nodes = new ArrayList<>();
    /**
     * Constructor for objects of class GraphBuilder
     * Creates a new graph
     */
    public GraphBuilder(Map<String, Color> colorPalette)
    {
        this.graph = defaultGraph(colorPalette);
    }

    /**
     * This method is used as a default graph for the algorithm
     * @param
     *      nodes - the arraylist collection of every node
     *      graph - an object of the custom class Graph. It is essentailly a map of all the nodes
     *       = 
     * @return
     *      void (nothing)
     */
    private Graph defaultGraph(Map<String, Color> colorPalette){
        ArrayList<Node> nodes = new ArrayList<>();
        Color nodeColor = colorPalette.get("nodeDefault");
        
        Node zero = new Node(0, 670, 190, nodeColor);
        Node one = new Node(1, 500, 400, nodeColor);
        Node two = new Node(2, 600, 100, nodeColor);
        Node three = new Node(3, 50, 300, nodeColor);
        Node four = new Node(4, 300, 320, nodeColor);
        Node five = new Node(5, 200, 150, nodeColor);
        
        zero.addBidirectionalDestination(one, edgeDistanceCalc(zero,one));
        zero.addBidirectionalDestination(two, edgeDistanceCalc(zero,two));
        zero.addBidirectionalDestination(three , edgeDistanceCalc(zero,three));
        one.addBidirectionalDestination(two, edgeDistanceCalc(one,two));
        one.addBidirectionalDestination(three, edgeDistanceCalc(one,three));
        two.addBidirectionalDestination(three, edgeDistanceCalc(two,three));
        two.addBidirectionalDestination(four, edgeDistanceCalc(two,four));
        three.addBidirectionalDestination(four, edgeDistanceCalc(three,four));
        four.addBidirectionalDestination(five, edgeDistanceCalc(four,five));
        
        nodes.add(zero);
        nodes.add(one);
        nodes.add(two);
        nodes.add(three);
        nodes.add(four);
        nodes.add(five);
        
        
        for(int x = 0; x < nodes.size(); x++){
            Node currentNode = nodes.get(x);
            graph.addNode(currentNode);
        }   
        /* edges */
        for(Node n : graph.getAllNodes()){
            ArrayList<Edge> currentEdges = n.getEdges();
            for(int e = 0; e < currentEdges.size(); e++){
                Edge currentEdge = currentEdges.get(e);
                currentEdge.setColor(colorPalette.get("edgeDefault"));
            }
        }
        
        
        return graph;
    }
    /**
     * This method is used as a default graph for the algorithm
     * @param
     *      nodes - the arraylist collection of every node
     *      graph - an object of the custom class Graph. It is essentailly a map of all the nodes
     *       = 
     * @return
     *      void (nothing)
     */
    private Graph testingGraph(Map<String, Color> colorPalette){
        ArrayList<Node> nodes = new ArrayList<>();
        Color nodeColor = colorPalette.get("nodeDefault");
        
        for(int x = 0; x < nodes.size(); x++){
            Node currentNode = nodes.get(x);
            graph.addNode(currentNode);
        }   
        /* edges */
        for(Node n : graph.getAllNodes()){
            ArrayList<Edge> currentEdges = n.getEdges();
            for(int e = 0; e < currentEdges.size(); e++){
                Edge currentEdge = currentEdges.get(e);
                currentEdge.setColor(colorPalette.get("edgeDefault"));
            }
        }
        
        
        return graph;
    }
    /**
     * Calculates the distance between two nodes
     * @param start (node), target (node)
     * @return distance (int)
     */
    private static int edgeDistanceCalc(Node start, Node target){
        int x = start.getX() - target.getX();
        int y = start.getY() - target.getY();
        return (int) Math.hypot(x, y);
    }
    /** 
     * sets the color of all the nodes and edges to default
     */
    public void resetGraph(Map<String, Color> colorPalette){
        for(Node n : graph.getAllNodes()){
            n.resetNode(colorPalette.get("nodeDefault"));
            for(Edge e : n.getEdges()){
                e.setColor(colorPalette.get("edgeDefault"));
            }
        }
    }
    /* Getters */
    public Graph getGraph(){
        return(this.graph);
    }
    /* setters */
    public void setGraph(Graph graph){
        this.graph = graph;
    }
}