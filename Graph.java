import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
/**
 * Write a description of class Graph here.
 * https://www.w3schools.com/Java/java_hashmap.asp
 * https://stackoverflow.com/questions/1348199/what-is-the-difference-between-the-hashmap-and-map-objects-in-java
 * @author (your name)
 * @version (a version number or a date)
 */
public class Graph
{
    /**
     * Wrapper class
     */
    private class GraphNode{
        Node node;
        public GraphNode(Node node){
            this.node = node;
        }

        /* getters */
        public Node getNode(){
            return(this.node);
        }
    }
    private Map<String, GraphNode> map = new HashMap<>();
    public Graph(ArrayList<Node> nodes){
        for(int x=0;x<nodes.size();x++){
            addNode(nodes.get(x));
        }
    }
    public Graph(){}
    public void addNode(Node node){
        String newNodeName = String.valueOf(node.getName()); // convert char to string
        GraphNode graphNode = new GraphNode(node);
        /* name is the key to find the node later */
        this.map.put(newNodeName, graphNode);
        //System.out.println("this.map.put: "+newNode.getName());
    }
    /* getters */
    public Node getNode(int nodeName){
        String newNodeName = String.valueOf(nodeName); // convert char to string
        GraphNode graphNode = this.map.get(newNodeName);
        return(graphNode.getNode());
    }
    public ArrayList<Node> getAllNodes(){
        ArrayList<Node> nodes = new ArrayList<>();
        for(GraphNode wrapper : this.map.values()){
            nodes.add(wrapper.getNode());
        }
        return(nodes);
    }
    public Map getMap(){
        return(this.map);
    }
    
    public boolean isEmpty(){
        return(map.isEmpty());
    }
}