import java.util.ArrayList;
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
    private int x;
    private int y;
    private int radius;
    /* Constructors */
    public Node(){}
    public Node (int name){
        this.name = name;
        this.distanceFromStart = Integer.MAX_VALUE;
        this.visited = false;
        this.previous = null;
        this.x = (int)(Math.random() * 600);
        this.y = (int)(Math.random() * 300);
        this.radius = 30;
    }
    public Node(int name, ArrayList<Edge> edges){
        this.name = name;
        this.edges = edges;
        this.distanceFromStart = Integer.MAX_VALUE;
        this.visited = false;
        this.previous = null;
        this.x = (int)(Math.random() * 600);
        this.y = (int)(Math.random() * 300);
        this.radius = 50;
    }
    
    public void addDestination(Node destination, int distanceToDestination){
        Edge newEdge = new Edge(destination, distanceToDestination);
        this.edges.add(newEdge);
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
    public int getX(){
        return(this.x);
    }
    public int getY(){
        return(this.y);
    }
    public int getRadius(){
        return(this.radius);
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
    public void setX(int newValue){
        this.x = newValue;
    }
    public void setY(int newValue){
        this.x = newValue;
    }
    public void setRadius(int newValue){
        this.x = newValue;
    }
}