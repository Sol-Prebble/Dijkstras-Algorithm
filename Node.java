
/**
 * This is the Node class
 * It creates objects for each name structure inside the project
 */
public class Node{
    private String name;
    
    private int distance;
    private Queue shortestPath = new Queue();
    
    private Edge firstEdge;

    /* Constructors */
    /* For a node with no name input */
    public Node(){
        this.name = "";
        this.distance = Integer.MAX_VALUE / 2; // placeholder for infinity - half the max value to protect from looping around
        this.firstEdge = null;
        shortestPath.enqueue(this);
        System.out.println(shortestPath);
    }
    /* For a node with a string input */
    public Node(String name){
        this.name = name;
        this.distance = Integer.MAX_VALUE / 2; // placeholder for infinity - half the max value to protect from looping around
        this.firstEdge = null;
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