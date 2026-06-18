import java.awt.Color;
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
    private Color color;
    public Edge(Node targetNode, int distance){
        this.targetNode = targetNode;
        this.distance = distance;
        this.color = Color.DARK_GRAY;
    }
    public Edge(Node targetNode){
        this.targetNode = targetNode;
        this.distance = (int)(Math.random() * 11); // 0 to 10
        this.color = Color.DARK_GRAY;
    }
    
    /* getters */
    public Node getTargetNode(){
        return(this.targetNode);
    }
    public int getDistance(){
        return(this.distance);
    }
    public Color getColor(){
        return(this.color);
    }
    
     /* setters */
    public void setTargetNode(Node targetNode){
        this.targetNode = targetNode;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public void setColor(Color newColor){
        this.color = newColor;
    }
}
