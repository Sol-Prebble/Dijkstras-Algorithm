
/**
 * Distance bewteen nodes
 *
 * @Sol prebble
 * @v1
 */
public class Edge
{
    private int distance; // the cost to travel this edg e
    private Node targetNode; // the node this edge leads to
    private Edge nextEdge;
    public Edge(Node targetNode, int distance){
        this.targetNode = targetNode;
        this.distance = distance;
        this.nextEdge = null;
    }
    public Edge(Node targetNode){
        this.targetNode = targetNode;
        this.distance = (int)(Math.random() * 101); // 0 to 100
        this.nextEdge = null;
    }
    
    /* getters */
    public Node getTargetNode(){
        return(this.targetNode);
    }
    public Edge getNextEdge(){
        return(this.nextEdge);
    }
    public int getDistance(){
        return(this.distance);
    }
    
     /* setters */
    public void setTargetNode(Node targetNode){
        this.targetNode = targetNode;
    }
    public void setNextEdge(Edge nextEdge){
        this.nextEdge = nextEdge;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
}
