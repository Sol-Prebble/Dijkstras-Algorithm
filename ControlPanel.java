import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class ControlPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ControlPanel
{
    private JPopupMenu dropDownMenu;
    private Map<String, Color> colorPalette;
    private ArrayList<Node> nodes;
    private Map<String, JMenuItem> items;
    private JLayeredPane layeredPane;
    /* buttons */
    private JButton pickStartButton;
    private JButton pickTargetButton;
    private JButton runButton;
    private JButton resetButton;
    /* nodes */
    private Node start;
    private Node target;
    /**
     * Constructor for objects of class ControlPanel
     */
    public ControlPanel(Theme theme, PanelCanvas canvas, ArrayList<Node> nodes, PathFinder newPath, GraphBuilder graphBuilder)
    {
        this.dropDownMenu = new JPopupMenu();
        this.colorPalette = theme.getPalette();
        this.layeredPane = new JLayeredPane();
        this.items = new HashMap();
        this.nodes = nodes;
        /* buttons */
        this.pickStartButton = new JButton("Pick Start");
        this.pickTargetButton = new JButton("Pick Target");
        this.runButton = new JButton("Run");
        this.resetButton = new JButton("RESET");
        buttons(canvas, newPath, graphBuilder);
    }
    /* getters */
    public JLayeredPane getLayeredPane(){
        return(this.layeredPane);
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
    private void buttons(PanelCanvas canvas, PathFinder newPath, GraphBuilder graphBuilder){
        createButtons();
        
        /* update layered pane */
        layeredPaneAddition(layeredPane, canvas); // add the aspects to the layered pane
        
        /* button functions */
        pickStartButton();
        pickTargetButton();
        runButton(newPath);
        resetButton(graphBuilder, canvas, nodes);
    }
    /**
     * This methods role is to call the methods that determin the visuals of the buttons on the canvas. 
     * Made it's own method for easier orginisation
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private void createButtons(){
        setButtonBackgroundColor(colorPalette.get("buttonBackground"));
        setButtonForegroundColor(colorPalette.get("buttonText")); 
        setButtonBounds();
    }
    /**
     * This methods role is to update / set the background (main) color of the buttons
     * It calls core methods to do so for each button
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private void setButtonBackgroundColor(Color backgroundColor){
        pickStartButton.setBackground(backgroundColor);
        pickTargetButton.setBackground(backgroundColor);
        runButton.setBackground(backgroundColor);
        resetButton.setBackground(backgroundColor);
    }
    /**
     * This methods role is to update / set the foreground (text) color of the buttons
     * It calls core methods to do so for each button
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private void setButtonForegroundColor(Color foregroundColor){
        pickStartButton.setForeground(foregroundColor); 
        pickTargetButton.setForeground(foregroundColor);
        runButton.setForeground(foregroundColor); 
        resetButton.setForeground(foregroundColor); 
    }
    /**
     * This methods role is to update / set the bounds (location and size) of the buttons
     * It calls core methods to do so for each button
     * @param
     *      pickStartButton, pickTargetButton, runButton - all JButton objects.
     * 
     * @return void (nothing)
     */
    private void setButtonBounds(){
        /* x, y values */
        final int buttonX = 50;
        final int startButtonY = 50;
        final int targetButtonY = startButtonY + 60;
        final int runButtonY = targetButtonY + 60;
        final int resetButtonY = runButtonY + 60;
        
        /* button size */
        final int buttonSizeX = 120;
        final int buttonSizeY = 40;
        
        /* update methods */
        pickStartButton.setBounds(buttonX, startButtonY, buttonSizeX, buttonSizeY); 
        pickTargetButton.setBounds(buttonX, targetButtonY, buttonSizeX, buttonSizeY);
        runButton.setBounds(buttonX, runButtonY, buttonSizeX, buttonSizeY);
        resetButton.setBounds(buttonX, resetButtonY, buttonSizeX, buttonSizeY);
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
    private void layeredPaneAddition(JLayeredPane layeredPane, PanelCanvas canvas){
        layeredPane.add(canvas, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(pickStartButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(pickTargetButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(runButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(resetButton, JLayeredPane.PALETTE_LAYER);
    }
    private void pickStartButton(){

        pickStartButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    updateDropDownVisibility();
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

    private void pickTargetButton(){
        pickTargetButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    updateDropDownVisibility();
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

    private void updateDropDownVisibility(){
        boolean menuVisible = dropDownMenu.isVisible();
        if(menuVisible){
            dropDownMenu.setVisible(false);
        } else if(!menuVisible){
            dropDownMenu.removeAll();
        }
    }
    private void runButton(PathFinder newPath){

        runButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    newPath.runAlgorithm(start, target, colorPalette);
                }
            });
    }
    private void resetButton(GraphBuilder graphBuilder, PanelCanvas canvas, ArrayList<Node> nodes){

        resetButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    graphBuilder.resetGraph(colorPalette);
                    PathFinder newPath = new PathFinder(graphBuilder.getGraph(), canvas);
                    canvas.repaint();
                }
            });
    }
}