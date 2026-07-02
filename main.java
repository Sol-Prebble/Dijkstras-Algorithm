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
    /**
     * Layered Panes: https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html
     */
    public static void main(String[] args){
        ArrayList<Node> nodes = new ArrayList<>();
        Graph graph = new Graph();

        defaultGraph(nodes, graph);

        PanelCanvas canvas = new PanelCanvas(graph);
        PathFinder newPath = new PathFinder(graph, canvas);

        JPopupMenu dropDownMenuStart = new JPopupMenu();
        JPopupMenu dropDownMenuTarget = new JPopupMenu();
        Map<String, JMenuItem> items = new HashMap();

        JFrame window = new JFrame("Dijkstra's algorithm");
        JLayeredPane layeredPane = new JLayeredPane();

        /* Buttons */
        JButton pickStartButton = new JButton("Pick Start");
        JButton pickTargetButton = new JButton("Pick Target");
        JButton runButton = new JButton("Run");
        
        pickStartButton.setBackground(Color.DARK_GRAY);
        pickTargetButton.setBackground(Color.DARK_GRAY);
        runButton.setBackground(Color.DARK_GRAY);
        
        pickStartButton.setForeground(Color.WHITE); 
        pickTargetButton.setForeground(Color.WHITE);
        runButton.setForeground(Color.WHITE); 
        
        pickStartButton.setBounds(50, 50, 120, 40); 
        pickTargetButton.setBounds(50, 110, 120, 40);
        runButton.setBounds(50, 170, 120, 40);
        canvas.setBounds(0,0,800,500);
        
        /* button functions */
        boolean dropDownOpen = false;
        pickTarget(pickTargetButton, dropDownMenuTarget, nodes, items);
        pickStart(pickStartButton, dropDownMenuStart, nodes, items);
        run(runButton, newPath);
        
        /* Layered Pane */
        layeredPane.add(canvas, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(pickStartButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(pickTargetButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(runButton, JLayeredPane.PALETTE_LAYER);
        

        
        
        //window.getContentPane().add(layeredPane);
        //window.add(canvas);
        window.add(layeredPane);
        
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    private static int edgeDistanceCalc(Node start, Node target){
        int x = start.getX() - target.getX();
        int y = start.getY() - target.getY();
        return (int) Math.hypot(x, y);
    }

    private static void pickStart(JButton pickStartButton, JPopupMenu dropDownMenu, ArrayList<Node> nodes, Map<String, JMenuItem> items){

        pickStartButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    boolean menuVisible = dropDownMenu.isVisible();
                    if(menuVisible){
                        dropDownMenu.setVisible(false);
                        //System.out.println("test");
                    } else if(!menuVisible){
                        
                        dropDownMenu.removeAll();
                    }
                    for(int x = 0; x < nodes.size(); x++){
                        Node currentNode = nodes.get(x);
                        String name = ("item"+x);
                        items.put(name, new JMenuItem(String.valueOf(currentNode.getName())));
                        dropDownMenu.add(items.get(name));
                        items.get(name).addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    start = currentNode;
                                }
                            });
                    }
                    dropDownMenu.show(
                            pickStartButton, 
                            0, 
                            pickStartButton.getHeight()
                        );
                }
            });
    }

    private static void pickTarget(JButton pickTargetButton, JPopupMenu dropDownMenu, ArrayList<Node> nodes, Map<String, JMenuItem> items){
        pickTargetButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    
                    boolean menuVisible = dropDownMenu.isVisible();
                    if(menuVisible){
                        dropDownMenu.setVisible(false);
                        //System.out.println("test");
                    } else if(!menuVisible){
                        
                        dropDownMenu.removeAll();
                    }
                    for(int x = 0; x < nodes.size(); x++){
                        Node currentNode = nodes.get(x);
                        String name = ("item"+x);
                        items.put(name, new JMenuItem(String.valueOf(currentNode.getName())));
                        dropDownMenu.add(items.get(name));
                        items.get(name).addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    target = currentNode;
                                }
                            });
                    }
                    dropDownMenu.show(
                            pickTargetButton, 
                            0, 
                            pickTargetButton.getHeight()
                        );
                }
            });
    }

    private static void run(JButton runButton, PathFinder newPath){ //# can't run twice

        runButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    newPath.runAlgorithm(start, target);
                }
            });
    }
}