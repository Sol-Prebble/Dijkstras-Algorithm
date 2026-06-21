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
        JPopupMenu dropDownMenuStart = new JPopupMenu();
        JPopupMenu dropDownMenuTarget = new JPopupMenu();
        Map<String, JMenuItem> startItems = new HashMap();
        Map<String, JMenuItem> targetItems = new HashMap();
        
        
        for(int x = 0; x < nodes.size(); x++){
            String name = ("item"+x);
            startItems.put(name, new JMenuItem(String.valueOf(nodes.get(x).getName())));
            dropDownMenuStart.add(startItems.get(name));

        }
        
        /* buttons */
        /* pick start */
        JButton pickStart = new JButton("Pick Start Node");
        pickStart.setBackground(Color.DARK_GRAY);
        pickStart.setForeground(Color.WHITE); 
        pickStart.setBounds(50, 50, 120, 40);
        pickStartButton(nodes, pickStart, dropDownMenuStart, startItems);
        /* pick target */
        JButton pickTarget = new JButton("Pick Target Node");
        pickTarget.setBackground(Color.DARK_GRAY);
        pickTarget.setForeground(Color.WHITE); 
        pickTarget.setBounds(50, 110, 120, 40);
        pickTargetButton(nodes, pickTarget, dropDownMenuTarget, targetItems);
        /* run */
        JButton run = new JButton("Run");
        run.setBackground(Color.DARK_GRAY);
        run.setForeground(Color.WHITE); 
        run.setBounds(50, 170, 120, 40);
        run(run, newPath);
        

        
        
        window.add(pickStart);
        window.add(pickTarget);
        window.add(run);

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

        // start = zero;
        // target = five;
    }

    private static int edgeDistanceCalc(Node start, Node target){
        int x = start.getX() - target.getX();
        int y = start.getY() - target.getY();
        return (int) Math.hypot(x, y);
    }
    
    private static void pickStartButton(ArrayList<Node> nodes, JButton pickStart, JPopupMenu dropDownMenuStart, Map<String, JMenuItem> startItems){
        
        
        pickStart.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    //System.out.println("was clicked");
                    dropDownMenuStart.show(
                        pickStart, 
                        0, 
                        pickStart.getHeight()
                    );
                    for(int x = 0; x < nodes.size(); x++){
                        String name = ("item"+x);
                        Node currentNode = nodes.get(x);
                        startItems.get(name).addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    start = currentNode;
                                    System.out.println("Start: "+start.getName());
                                    
                                }

                            });
                    }

                    
                }
            });
    }
    private static void pickTargetButton(ArrayList<Node> nodes, JButton pickTarget, JPopupMenu dropDownMenuTarget, Map<String, JMenuItem> targetItems){
        
        
        pickTarget.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    //System.out.println("was clicked");
                    dropDownMenuTarget.show(
                        pickTarget, 
                        0, 
                        pickTarget.getHeight()
                    );
                    for(int x = 0; x < nodes.size(); x++){
                        String name = ("item"+x);
                        Node currentNode = nodes.get(x);
                        targetItems.get(name).addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    target = currentNode;
                                    System.out.println("Target: "+target.getName());
                                    
                                }

                            });
                    }

                    
                }
            });
    }
    private static void run(JButton run, PathFinder newPath){
        
        
        run.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    newPath.runAlgorithm(start, target);
                }
            });
    }
}