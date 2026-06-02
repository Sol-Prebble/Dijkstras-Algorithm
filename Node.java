import java.util.ArrayList;
/**
 * This is the Node class
 * It creates objects for each name structure inside the project
 */
public class Node{
    private char name;
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private int distanceFromStart;
    private boolean visited;
    /* Constructors */
    /* For a node with no name input */
    public Node(char name, ArrayList edges){
        this.name = name;
        this.edges = edges;
        this.distanceFromStart = 0;
        this.visited = false;
    }
    
    public void addDestination(Node destination, int distanceToDestination){
        Edge newEdge = new Edge(destination, distanceToDestination);
        
    }
    
    /* Getters */
    public ArrayList<Edge> getEdges(){
        return(this.edges);
    }
    public int getDistanceFromStart(){
        return(this.distanceFromStart);
    }
    public char getName(){
        return(this.name);
    }
    public boolean getVisited(){
        return(this.visited);
    }
    
    /* Setters */
    public void setDistanceFromStart(int distance){
        this.distanceFromStart = distance;
    }
    public void setVisited(boolean newState){
        this.visited = newState;
    }
}