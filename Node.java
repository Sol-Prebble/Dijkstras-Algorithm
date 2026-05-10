import java.util.LinkedList;
/**
 * This is the Node class
 * It creates objects for each name structure inside the project
 */
public class Node{
    private String name;
    
    private int distance = Integer.MAX_VALUE / 2; // half the max value to protect looping around from happening
    private Queue shortestPath = new Queue();

    /* Constructors */
    /* For a node with no name input */
    public Node(){
        this.name = "";
    }
    /* For a node with a string input */
    public Node(String name){
        this.name = name;
    }
    
    /* Getters */
    public String getName(){
        return(this.name);
    }

    /* Setters */
    public void setName(String name){
        this.name = name;
    }
}