import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
/**
 * This class finds the shortest path 
 * It contains the aglorithm behind the animation
 *
 * @Sol Prebble
 * @7/8/26
 */
public class PathFinder
{
    private Graph graph; // graph of nodes
    private Queue nodeOrder = new Queue(); // contains each
    private PanelCanvas canvas; // GUI pane object
    private Map<String, Color> colorPalette;
    public PathFinder(Graph graph, PanelCanvas canvas){
        this.graph = graph;
        this.canvas = canvas;
    }

    /**
     * https://www.w3schools.com/java/java_threads.asp
     * https://pressbooks.pub/javaprogramming/chapter/multithreading-java-programming/
     * https://www.geeksforgeeks.org/java/thread-sleep-method-in-java-with-examples/
     * 
     */
    /**
     * Contain the first part of the algorithm
     * It takes out a node from a priority queue and calls the findShortestLocalPath if unvisited
     * @param startNode (Node), endNode (node), colorPalette (map), controlPanel (object)
     * @return null/void
     */
    public void runAlgorithm(Node startNode, Node endNode, Map<String, Color> colorPalette, ControlPanel controlPanel){
        this.colorPalette = colorPalette;
        new Thread(() -> {
                    nodeOrder.enqueue(startNode);
                    startNode.setDistanceFromStart(0);
                    while(!nodeOrder.isEmpty()){
                        Node currentNode = nodeOrder.dequeue();
                        if(!currentNode.getVisited()){
                            threadSleep();
                            findShortestLocalPath(currentNode);
                            threadSleep();
                        }
                    }
                    finalPath(startNode, endNode);
                    controlPanel.enableResetButton(canvas);
                    canvas.repaint();
            }).start();
    }
    /**
     * Finds the shortest edge on the current node
     * Also updates the colors
     * @param Node currentNode
     * @return null/void
     */
    public void findShortestLocalPath(Node currentNode){
        recolorNode(currentNode, colorPalette.get("selected"));
        Node returnNode = null;
        for(int x=0;x< currentNode.getEdges().size(); x++){
            Edge currentEdge = currentNode.getEdges().get(x);

            recolorEdge(currentEdge, colorPalette.get("selected"));
            threadSleep();
            Node targetNode = currentEdge.getTargetNode();
            int proposedDistance = currentNode.getDistanceFromStart() + currentEdge.getDistance();
            if(proposedDistance < targetNode.getDistanceFromStart()){
                targetNode.setDistanceFromStart(proposedDistance);
                nodeOrder.priorityEnqueue(targetNode);
                targetNode.setPrevious(currentNode);
                targetNode.setPreviousEdge(currentEdge);
            }
            threadSleep();
            recolorEdge(currentEdge, colorPalette.get("visited"));
            returnNode = targetNode;

        }
        recolorNode(currentNode, colorPalette.get("visited"));
        currentNode.setVisited(true);
    }
    /**
     * Calculates the final path
     * Each node contains the previous node in the shortest path as a variable
     * This method puts all the nodes into a stack
     * This means we can later utilise the functionality of a stack to take them out in reverse. Getting the final path (done in the next method)
     * @param Node startNode, Node currentNode
     * @return Stack
     */
    public Stack finalPath(Node startNode, Node currentNode){
        Stack path = new Stack();
        while(currentNode!=null){
            Edge currentEdge = currentNode.getPreviousEdge();
            //System.out.println(currentNode.getName());
            recolorNode(currentNode, colorPalette.get("path"));
            if(currentEdge != null){
                recolorEdge(currentEdge, colorPalette.get("path"));
            }
            path.push(currentNode);
            currentNode = currentNode.getPrevious();

        }
        printFinalPath(path);
        return path;
    }
    /**
     * Prints the final path in the terminal
     * Pops a node from the top of the stack one at a time, giving us the final path
     * @param Stack path
     * @return void
     */
    public void printFinalPath(Stack path){
        String pathData = "";
        while(!path.isEmpty()){
            pathData += (path.pop().getName()+", ");
        }
        System.out.println(pathData);
    }

    public void threadSleep(){
        try{
            Thread.sleep(100); // pause the algorithm for 250ms
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * EDT thread alignment - NOTE: this didn't actauly fix anything, but it works the same so I left it.
     * To sync the recolor methods with the algorithm
     *      https://codingtechroom.com/question/invoke-and-wait-swingutilities
     * To make the canvas update imediatly
     * https://codingtechroom.com/question/jcomponent-paintimmediately-java-swing
     */
    /**
     * Updates the current nodes color variable
     * @param Node currentNode, Color newColor
     * @return void
     */
    public void recolorNode(Node currentNode, Color newColor){
        try{
            SwingUtilities.invokeAndWait(()-> {
                        currentNode.setColor(newColor);
                        canvas.paintImmediately(canvas.getVisibleRect());
                });           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Updates the current edges color variable
     * @param Edge currentEdge, Color newColor
     * @return void
     */
    public void recolorEdge(Edge currentEdge, Color newColor){
        Edge twin = currentEdge.getTwin();
        try{
            SwingUtilities.invokeAndWait(()-> {
                        currentEdge.setColor(newColor);
                        twin.setColor(newColor);
                        canvas.paintImmediately(canvas.getVisibleRect());
                });           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
