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
    private static Map<String, Color> colorPalette;
    public main(ArrayList<Node> nodes){
        resetNodeColors(nodes);
    }

    /**
     * Layered Panes: https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html
     */
    public static void main(String[] args){
        ArrayList<Node> nodes = new ArrayList<>(); // arraylist for all of the nodes in the graph
        colorPalette = darkPalette();

        //new main(nodes);

        Graph graph = new Graph();

        defaultGraph(nodes, graph);

        PanelCanvas canvas = new PanelCanvas(graph, colorPalette);
        PathFinder newPath = new PathFinder(graph, canvas);

        /* drop down menu's */
        JPopupMenu dropDownMenu = new JPopupMenu();

        Map<String, JMenuItem> items = new HashMap();

        JFrame window = new JFrame("Dijkstra's algorithm");
        JLayeredPane layeredPane = new JLayeredPane();

        /* Buttons */
        buttons(layeredPane, canvas, dropDownMenu, nodes, items, newPath);
        
        /* canvas */
        canvas.setBounds(0,0,800,500);


        /* Layered Pane */
        
        window.add(layeredPane);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,500);
        window.toFront();
        window.setVisible(true);
    }
    private static Map<String, Color> darkPalette(){
        Map<String, Color> darkPalette = new HashMap<>();
        
        Color background = Color.BLACK;
        Color nodeDefault = Color.DARK_GRAY;
        Color nodeText = Color.WHITE;
        Color selected = Color.MAGENTA;
        Color checked = Color.WHITE;
        Color path = Color.CYAN;
        Color buttonBackground = Color.DARK_GRAY;
        Color buttonText = Color.WHITE;
        
        darkPalette.put("background", background);
        darkPalette.put("nodeDefault", nodeDefault);
        darkPalette.put("nodeText", nodeText);
        darkPalette.put("selected", selected);
        darkPalette.put("checked", checked);
        darkPalette.put("path", path);
        darkPalette.put("buttonBackground", buttonBackground);
        darkPalette.put("buttonText", buttonText);
        
        return(darkPalette);
    }
    private static Map<String, Color> defaultPalette(){
        
        Map<String, Color> defaultPalette = new HashMap<>();
        
        Color background = Color.GRAY;
        Color nodeDefault = Color.LIGHT_GRAY;
        Color nodeText = Color.WHITE;
        Color selected = Color.YELLOW;
        Color checked = Color.WHITE.darker();
        Color path = Color.GREEN;
        Color buttonBackground = Color.DARK_GRAY;
        Color buttonText = Color.WHITE;
        
        defaultPalette.put("background", background);
        defaultPalette.put("nodeDefault", nodeDefault);
        defaultPalette.put("nodeText", nodeText);
        defaultPalette.put("selected", selected);
        defaultPalette.put("checked", checked);
        defaultPalette.put("path", path);
        defaultPalette.put("buttonBackground", buttonBackground);
        defaultPalette.put("buttonText", buttonText);
        return(defaultPalette);
    }
    /**
     * Button methods
     */
    /**
     * This method role is to create the buttons that appear on the screen and call all of the functions that are involved in setting them up
     * @param 
     *      layeredPane - an object of the core java class to set the depth of content in the GUI
     *      canvas - the set that all of the content is displayed
     *      dropDownMenu - the menu that pops up with all of the nodes as options
     *      nodes - an arraylist that acts as storage for all of the nodes in the graph
     *      items - a map that is an index version of the 'nodes' arraylist. Used for dropdown menu names
     *      newPath - an object of the custom PathFinder class that contains the actual algorithm. Used to start the algorithm.
     *      
     * @return void (nothing)
     *      
     */
    private static void buttons(JLayeredPane layeredPane, PanelCanvas canvas, JPopupMenu dropDownMenu, ArrayList<Node> nodes, Map<String, JMenuItem> items, PathFinder newPath){
        /* create buttons */
        JButton pickStartButton = new JButton("Pick Start");
        JButton pickTargetButton = new JButton("Pick Target");
        JButton runButton = new JButton("Run");
        createButtons(pickStartButton, pickTargetButton, runButton);
        
        /* update layered pane */
        layeredPaneAddition(layeredPane, canvas, pickStartButton, pickTargetButton, runButton); // add the aspects to the layered pane
        
        /* button functions */
        pickTarget(pickTargetButton, dropDownMenu, nodes, items);
        pickStart(pickStartButton, dropDownMenu, nodes, items);
        run(runButton, newPath, colorPalette);
    }
    /**
     * This methods role is to call the methods that determin the visuals of the buttons on the canvas. 
     * Made it's own method for easier orginisation
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private static void createButtons(JButton pickStartButton, JButton pickTargetButton, JButton runButton){
        setButtonBackgroundColor(colorPalette.get("buttonBackground"), pickStartButton, pickTargetButton, runButton);
        setButtonForegroundColor(colorPalette.get("buttonText"),pickStartButton, pickTargetButton, runButton); 
        setButtonBounds(pickStartButton, pickTargetButton, runButton);
    }
    /**
     * This methods role is to update / set the background (main) color of the buttons
     * It calls core methods to do so for each button
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private static void setButtonBackgroundColor(Color backgroundColor, JButton pickStartButton, JButton pickTargetButton, JButton runButton){
        pickStartButton.setBackground(backgroundColor);
        pickTargetButton.setBackground(backgroundColor);
        runButton.setBackground(backgroundColor);
    }
    /**
     * This methods role is to update / set the foreground (text) color of the buttons
     * It calls core methods to do so for each button
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private static void setButtonForegroundColor(Color foregroundColor, JButton pickStartButton, JButton pickTargetButton, JButton runButton){
        pickStartButton.setForeground(foregroundColor); 
        pickTargetButton.setForeground(foregroundColor);
        runButton.setForeground(foregroundColor); 
    }
    /**
     * This methods role is to update / set the bounds (location and size) of the buttons
     * It calls core methods to do so for each button
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private static void setButtonBounds(JButton pickStartButton, JButton pickTargetButton, JButton runButton){
        /* constant values */
        final int buttonX = 50;
        final int startButtonY = 50;
        final int buttonSizeX = 120;
        final int buttonSizeY = 40;
        /* update methods */
        pickStartButton.setBounds(buttonX, startButtonY, buttonSizeX, buttonSizeY); 
        pickTargetButton.setBounds(buttonX, startButtonY + 60, buttonSizeX, buttonSizeY);
        runButton.setBounds(buttonX, startButtonY + 120 , buttonSizeX, buttonSizeY);
    }
    /**
     * This methods role is to update / set the bounds (location and size) of the buttons
     * It calls core methods to do so for each button
     * @param
     *      layeredPane - the object that allows us to change the depth of content on the canvas
     *      canvas - Where everythig is drawn
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private static void layeredPaneAddition(JLayeredPane layeredPane, PanelCanvas canvas, JButton pickStartButton, JButton pickTargetButton, JButton runButton){
        layeredPane.add(canvas, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(pickStartButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(pickTargetButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(runButton, JLayeredPane.PALETTE_LAYER);
    }
    
    /**
     * This method is used as a default graph for the algorithm
     * @param
     *      nodes - the arraylist collection of every node
     *      graph - an object of the custom class Graph. It is essentailly a map of all the nodes
     *      
     * @return
     *      void (nothing)
     */
    private static void defaultGraph(ArrayList<Node> nodes, Graph graph){

        Node zero = new Node(0, 670, 190, colorPalette.get("nodeDefault"));
        Node one = new Node(1, 500, 400, colorPalette.get("nodeDefault"));
        Node two = new Node(2, 600, 100, colorPalette.get("nodeDefault"));
        Node three = new Node(3, 50, 300, colorPalette.get("nodeDefault"));
        Node four = new Node(4, 300, 320, colorPalette.get("nodeDefault"));
        Node five = new Node(5, 180, 150, colorPalette.get("nodeDefault"));

        nodes.add(zero);
        nodes.add(one);
        nodes.add(two);
        nodes.add(three);
        nodes.add(four);
        nodes.add(five);

        zero.addDestination(one, edgeDistanceCalc(zero,one));
        zero.addDestination(two, edgeDistanceCalc(zero,two));
        zero.addDestination(three , edgeDistanceCalc(zero,three));
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
                    updateDropDownVisibility(dropDownMenu);
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
                    updateDropDownVisibility(dropDownMenu);
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

    private static void updateDropDownVisibility(JPopupMenu dropDownMenu){
        boolean menuVisible = dropDownMenu.isVisible();
        if(menuVisible){
            dropDownMenu.setVisible(false);
            //System.out.println("test");
        } else if(!menuVisible){
            dropDownMenu.removeAll();
        }
    }
    private static void run(JButton runButton, PathFinder newPath, Map<String, Color> colorPalette){ //# can't run twice

        runButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    newPath.runAlgorithm(start, target, colorPalette);
                }
            });
    }

    /**
     * Check if node has been clicked
     */
    private static void resetNodeColors(ArrayList<Node> nodes){ //# doesn't work
        for(int x=0;x<nodes.size();x++){
            Node currentNode = nodes.get(x);
            currentNode.setColor(Color.DARK_GRAY);
        }
    }
}