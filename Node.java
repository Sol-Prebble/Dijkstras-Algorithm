import java.util.ArrayList;
import java.awt.Color;
/**
 * This is the Node class
 * It creates objects for each name structure inside the project
 */
public class Node{
    private int name;
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private int distanceFromStart;
    private boolean visited;
    private Node previous;
    private Edge previousEdge;
    private int x;
    private int y;
    private int radius;
    private Color color; // node color
    /* Constructor */
    public Node(int name, int x, int y, Color color){
        this.name = name;
        this.edges = edges;
        this.distanceFromStart = Integer.MAX_VALUE;
        this.visited = false;
        this.previous = null;
        this.previousEdge = null;
        this.x = x;
        this.y = y;
        this.radius = 50;
        this.color = color;
    }
    /**
     * Add an edge that goes from the currentNode to the target.
     * But also an edge that goes from the target to the current node. To make it multi directional
     */
    public void addBidirectionalDestination(Node destination, int distanceToDestination){
        Edge forwards = new Edge(destination, distanceToDestination);
        Edge reverse = new Edge(this, distanceToDestination);
        forwards.setTwin(reverse);
        reverse.setTwin(forwards);
        this.edges.add(forwards);
        destination.edges.add(reverse);
    }
    public void resetNode(Color color){
        this.name = name;
        this.edges = edges;
        this.distanceFromStart = Integer.MAX_VALUE;
        this.visited = false;
        this.previous = null;
        this.previousEdge = null;
        this.x = x;
        this.y = y;
        this.radius = 50;
        this.color = color;
    }
    
    /* Getters */
    public ArrayList<Edge> getEdges(){
        return(this.edges);
    }
    public int getDistanceFromStart(){
        return(this.distanceFromStart);
    }
    public int getName(){
        return(this.name);
    }
    public boolean getVisited(){
        return(this.visited);
    }
    public Node getPrevious(){
        return(this.previous);
    }
    public Edge getPreviousEdge(){
        return(this.previousEdge);
    }
    public int getX(){
        return(this.x);
    }
    public int getY(){
        return(this.y);
    }
    public int getRadius(){
        return(this.radius);
    }
    public Color getColor(){
        return(this.color);
    }
    
    /* Setters */
    public void setDistanceFromStart(int distance){
        this.distanceFromStart = distance;
    }
    public void setVisited(boolean newState){
        this.visited = newState;
    }
    public void setEdges(ArrayList<Edge> edges){
        this.edges = edges;
    }
    public void setPrevious(Node previous){
        this.previous = previous;
    }
    public void setPreviousEdge(Edge previous){
        this.previousEdge = previous;
    }
    public void setX(int newValue){
        this.x = newValue;
    }
    public void setY(int newValue){
        this.y = newValue;
    }
    public void setRadius(int newValue){
        this.radius = newValue;
    }
    public void setColor(Color newColor){
        this.color = newColor;
    }
}