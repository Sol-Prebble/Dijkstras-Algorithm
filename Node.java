
/**
 * This is the Node class
 * It creates objects for each name structure inside the project
 */
public class Node{
    private int distance;
    private Edge firstEdge;

    /* Constructors */
    /* For a node with no name input */
    public Node(){
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
    public int getDistance(){
        return(this.distance);
    }
    public Edge getFirstEdge(){
        return(this.firstEdge);
    }
}