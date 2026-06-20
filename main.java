import java.util.ArrayList;
import javax.swing.*;
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    private static Node start;
    private static Node target;
    public static void main(String[] args){
        
        Graph graph = new Graph();
        
        defaultGraph(graph);
        
        
        JFrame window = new JFrame("Dijkstra's algorithm");
        
        PanelCanvas canvas = new PanelCanvas(graph);
        PathFinder newPath = new PathFinder(graph, canvas);
        newPath.runAlgorithm(start, target);
        
        
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(canvas);
        window.setSize(800,500);
        window.toFront();
        window.setVisible(true);
    }
    private static void defaultGraph(Graph graph){
        ArrayList<Node> nodes = new ArrayList<>();
        
        
        Node zero = new Node(0, 300, 320);
        Node one = new Node(1, 500, 400);
        Node two = new Node(2, 600, 100);
        Node three = new Node(3, 80, 100);
        Node four = new Node(4, 150, 350);
        Node five = new Node(5, 300, 200);
        
        nodes.add(zero);
        nodes.add(one);
        nodes.add(two);
        nodes.add(three);
        nodes.add(four);
        nodes.add(five);
        
        zero.addDestination(one, edgeDistanceCalc(zero,one));
        zero.addDestination(two, edgeDistanceCalc(zero,two));
        zero.addDestination(two, edgeDistanceCalc(zero,three));
        one.addDestination(two, edgeDistanceCalc(one,two));
        one.addDestination(three, edgeDistanceCalc(one,three));
        two.addDestination(three, edgeDistanceCalc(two,three));
        two.addDestination(four, edgeDistanceCalc(two,four));
        three.addDestination(four, edgeDistanceCalc(three,four));
        four.addDestination(five, edgeDistanceCalc(four,five));
        
        for(int x = 0; x < nodes.size(); x++){
            graph.addNode(nodes.get(x));
        }
        
        start = zero;
        target = five;
    }
    private static int edgeDistanceCalc(Node start, Node target){
        int x = start.getX() - target.getX();
        int y = start.getY() - target.getY();
        return (int) Math.hypot(x, y);
    }
}