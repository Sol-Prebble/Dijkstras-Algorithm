
/**
 * The connections between nodes
 *
 * @Sol prebble
 * @v1
 */
public class Edge
{
    private int distance; // the cost to travel this edg e
    private Node targetNode; // the node this edge leads to
    public Edge(Node targetNode, int distance){
        this.targetNode = targetNode;
        this.distance = distance;
    }
    public Edge(Node targetNode){
        this.targetNode = targetNode;
        this.distance = (int)(Math.random() * 11); // 0 to 10
    }
    
    /* getters */
    public Node getTargetNode(){
        return(this.targetNode);
    }
    public int getDistance(){
        return(this.distance);
    }
    
     /* setters */
    public void setTargetNode(Node targetNode){
        this.targetNode = targetNode;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
}
