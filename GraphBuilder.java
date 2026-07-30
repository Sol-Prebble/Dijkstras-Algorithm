import java.util.*;
/**
 * Write a description of class GraphBuilder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GraphBuilder
{
    // instance variables - replace the example below with your own
    private Graph graph = new Graph();
    private ArrayList<Node> nodes = new ArrayList<>();
    /**
     * Constructor for objects of class GraphBuilder
     */
    public GraphBuilder()
    {
        this.graph = defaultGraph();
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
    private Graph defaultGraph(){
        ArrayList<Node> nodes = new ArrayList<>();
        
        Node zero = new Node(0, 670, 190);
        Node one = new Node(1, 500, 400);
        Node two = new Node(2, 600, 100);
        Node three = new Node(3, 50, 300);
        Node four = new Node(4, 300, 320);
        Node five = new Node(5, 180, 150);
        
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
            graph.addNode(nodes.get(x));
        }   
        return graph;
    }
    private static int edgeDistanceCalc(Node start, Node target){
        int x = start.getX() - target.getX();
        int y = start.getY() - target.getY();
        return (int) Math.hypot(x, y);
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