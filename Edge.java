
/**
 * Distance bewteen nodes
 *
 * @Sol prebble
 * @v1
 */
public class Edge
{
    private int distance;
    private Node targetNode;
    private Edge nextEdge;
    public Edge(Node targetNode){
        this.targetNode = targetNode;
        this.distance = (int)(Math.random() * 101); // 0 to 100
        this.nextEdge = null;
    }
    
    /
}
