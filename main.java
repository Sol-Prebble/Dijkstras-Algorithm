import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
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
        ArrayList<Node> nodes = new ArrayList<>();
        Graph graph = new Graph();

        defaultGraph(nodes, graph);

        PanelCanvas canvas = new PanelCanvas(graph);
        PathFinder newPath = new PathFinder(graph, canvas);

        JFrame window = new JFrame("Dijkstra's algorithm");
        JButton button = new JButton("Run");
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE); 
        button.setBounds(50, 50, 120, 40); 

        JPopupMenu dropDownMenu = new JPopupMenu();
        Map<String, JMenuItem> items = new HashMap();

        button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    //System.out.println("was clicked");
                    dropDownMenu.show(
                        button, 
                        0, 
                        button.getHeight()
                    );
                    for(int x = 0; x < nodes.size(); x++){
                        String name = ("item"+x);
                        items.put(name, new JMenuItem(String.valueOf(nodes.get(x).getName())));
                        dropDownMenu.add(items.get(name));
                    }
                    items.get(name).addActionListener(new ActionListener(){

                        });
                    newPath.runAlgorithm(start, target);
                }
            });

        window.add(button);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(canvas);
        window.setSize(800,500);
        window.toFront();
        window.setVisible(true);
    }

    private static void defaultGraph(ArrayList<Node> nodes, Graph graph){

        Node zero = new Node(0, 670, 190);
        Node one = new Node(1, 500, 400);
        Node two = new Node(2, 600, 100);
        Node three = new Node(3, 50, 300);
        Node four = new Node(4, 300, 320);
        Node five = new Node(5, 180, 150);

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