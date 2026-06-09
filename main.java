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
        ArrayList<Edge> aEdges = new ArrayList<>();
        Node zero = new Node(0, aEdges);
        
        nodes.add(zero);
        ArrayList<Edge> bEdges = new ArrayList<>();
        Node one = new Node(1, bEdges);
        
        nodes.add(one);
        ArrayList<Edge> cEdges = new ArrayList<>();
        Node two = new Node(2, cEdges);
        
        nodes.add(two);
        
        /* edges */
        Edge edgeA = new Edge(two,3);
        aEdges.add(edgeA);
        Edge edgeAA = new Edge(one,4);
        aEdges.add(edgeAA);
        
        Edge edgeB = new Edge(two,5);
        bEdges.add(edgeB);
        
        
        
        
        Graph graph = new Graph();
        for(int x = 0; x < nodes.size(); x++){
            System.out.println("node to add: "+nodes.get(x).getName());
            graph.addNode(nodes.get(x));
            //System.out.println(graph.getNode(x));
        }
        
        
        PathFinder newPath = new PathFinder(graph);
        newPath.runAlgorithm(zero, two);
        //newPath.finalPath(C);
    }
}