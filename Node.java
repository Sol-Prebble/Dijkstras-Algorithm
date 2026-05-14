
/**
 * This is the Node class
 * It creates objects for each name structure inside the project
 */
public class Node{
    private String name;
    
    private int distance = Integer.MAX_VALUE / 2; // placeholder for infinity - half the max value to protect from looping around
    private Queue shortestPath = new Queue();
    
    private Edge firstEdge = null;

    /* Constructors */
    /* For a node with no name input */
    public Node(){
        this.name = "";
    }
    /* For a node with a string input */
    public Node(String name){
        this.name = name;
    }
    
    public void addDestination(Node destination, int distanceToDestination){
        Edge newEdge = new Edge(destination, distanceToDestination);
        
        if(this.firstEdge == null){
            this.firstEdge = newEdge;
        } else{
            newEdge.setNextEdge(this.firstEdge);
            this.firstEdge = newEdge;
        }
    }
    
    /* Getters */
    public String getName(){
        return(this.name);
    }
    public int getDistance(){
        return(this.distance);
    }

    /* Setters */
    public void setName(String name){
        this.name = name;
    }
}