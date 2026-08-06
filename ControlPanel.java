import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Creates and maintains the buttons on the GUI
 *
 * @author Sol Prebble
 * @version 6-8-26
 */
public class ControlPanel
{
    private JPopupMenu dropDownMenu;
    private Map<String, JMenuItem> items; // for the drop down menu
    private Map<String, Color> colorPalette;
    private ArrayList<Node> nodes;
    private JLayeredPane layeredPane;
    /* buttons */
    private JButton pickStartButton;
    private JButton pickTargetButton;
    private JButton runButton;
    private JButton resetButton;
    /* nodes */
    private Node start;
    private Node target;

    private String state;
    /**
     * Constructor for objects of class ControlPanel
     * @param theme (the colors), canvas (the GUI pane), nodes (list), newPath (the algorithm object), graphBuilder (contains the graph)
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
        this.state = "pickStart";
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

        boolean run = false;
        /* button functions */

        resetButton(graphBuilder, canvas, nodes);
        resetClass(); // set buttons and node variables to default state
        pickStartButton();
        pickTargetButton();
        runButton(newPath);

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
        setButtonBackgroundColor(colorPalette.get("buttonEnabled"), colorPalette.get("buttonDisabled"));
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
    private void setButtonBackgroundColor(Color buttonEnabledColor, Color buttonDisabledColor){
        pickStartButton.setBackground(buttonEnabledColor);
        pickTargetButton.setBackground(buttonDisabledColor);
        runButton.setBackground(buttonDisabledColor);
        resetButton.setBackground(buttonEnabledColor);
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

    /**
     * Contains the action listener for if the pick start node button is clicked
     * Sets up the next buttons to be clicked and asigns the start node
     * @param null
     * @return null
     */
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
                                    pickStartButton.setBackground(colorPalette.get("buttonDisabled"));
                                    pickStartButton.setEnabled(false);
                                    pickTargetButton.setBackground(colorPalette.get("buttonEnabled"));    
                                    pickTargetButton.setEnabled(true);
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

    /**
     * Contains the action listener for if the pick target node button is clicked
     * Sets up the next buttons to be clicked and asigns the target node
     * @param null
     * @return null
     */
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
                                    pickTargetButton.setBackground(colorPalette.get("buttonDisabled"));    
                                    pickTargetButton.setEnabled(false);
                                    runButton.setBackground(colorPalette.get("buttonEnabled"));
                                    runButton.setEnabled(true);
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

    /**
     * Changes the visibility of the drop down menus
     * @param null
     * @return null
     */
    private void updateDropDownVisibility(){
        boolean menuVisible = dropDownMenu.isVisible();
        if(menuVisible){
            dropDownMenu.setVisible(false);
        } else if(!menuVisible){
            dropDownMenu.removeAll();
        }
    }

    /**
     * Contains the action listener for if the run button is clicked
     * Calls the runAlgorthm method in newPath.
     * @param newPath (an algorithm object)
     * @return null
     */
    private void runButton(PathFinder newPath){
        runButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    /* new algorithm */
                    newPath.runAlgorithm(start, target, colorPalette, ControlPanel.this); //# start algorithm

                    /* reset variables */
                    start = null;
                    target = null;

                    /* set colors */
                    pickStartButton.setBackground(colorPalette.get("buttonDisabled"));
                    pickTargetButton.setBackground(colorPalette.get("buttonDisabled"));
                    resetButton.setBackground(colorPalette.get("buttonDisabled"));
                    runButton.setBackground(colorPalette.get("buttonDisabled"));
                    /* turn off buttons */
                    pickStartButton.setEnabled(false);
                    pickTargetButton.setEnabled(false);
                    runButton.setEnabled(false);
                    resetButton.setEnabled(false);
                }
            });
    }

    /**
     * Contains the action listener for if the reset button is clicked
     * Resets the colors and nodes. Creates a new algorithm object
     * @param graphBuilder (contains the graph), canvas (graphic panel object), nodes (list)
     * @return null
     */
    private void resetButton(GraphBuilder graphBuilder, PanelCanvas canvas, ArrayList<Node> nodes){

        resetButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    resetClass();
                    graphBuilder.resetGraph(colorPalette);
                    PathFinder newPath = new PathFinder(graphBuilder.getGraph(), canvas);
                    canvas.repaint();
                }
            });
    }

    /**
     * Turns the resetButton on
     * @param canvas (graphic panel object)
     * @return null/ void
     */
    public void enableResetButton(PanelCanvas canvas){
        resetButton.setEnabled(true);
        resetButton.setBackground(colorPalette.get("buttonEnabled"));
        canvas.repaint();
    }

    /**
     * This class resets the variables and buttons to their default state
     * Nodes = undefined
     * buttons = only pickStart and reset buttons enabled
     */
    private void resetClass(){
        start = null;
        target = null;
        pickTargetButton.setEnabled(false);
        runButton.setEnabled(false);
        pickStartButton.setEnabled(true);
        resetButton.setEnabled(true);
        setButtonBackgroundColor(colorPalette.get("buttonEnabled"), colorPalette.get("buttonDisabled"));
    }
}