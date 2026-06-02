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
    private Map<String, Node> map = new HashMap<>();
    public Graph(ArrayList<Node> nodes){
        for(int x=0;x<nodes.size();x++){
            addNode(nodes.get(x));
        }
    }
    public void addNode(Node newNode){
        String newNodeName = String.valueOf(newNode.getName()); // convert char to string
        /* name is the key to find the node later */
        this.map.put(newNodeName, newNode);
    }
    public Node getNode(String nodeName){
        return(this.map.get(nodeName));
    }
    public Map getMap(){
        return(this.map);
    }
    public boolean isEmpty(){
        return(map.isEmpty());
    }
}