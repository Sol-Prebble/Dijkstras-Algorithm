import java.util.ArrayList;
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    public static void main(String[] args){
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        
        /* temp code */
        Node A = new Node('A', edges);
        nodes.add(A);
        Node B = new Node('B', edges);
        nodes.add(B);
        
        Graph graph = new Graph(nodes);
        PathFinder newPath = new PathFinder(graph);
    }
}