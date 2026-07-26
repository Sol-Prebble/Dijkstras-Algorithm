import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
/**
 * This class finds the shortest path 
 *
 * @Sol Prebble
 * @5/6/26
 */
public class PathFinder
{
    private Graph graph; // graph of nodes
    private Queue nodeOrder = new Queue(); // contains each
    private PanelCanvas canvas;
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
    public void runAlgorithm(Node startNode, Node endNode, Map<String, Color> colorPalette){
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
                    canvas.repaint();
            }).start();
    }

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
            recolorEdge(currentEdge, colorPalette.get("checked"));
            returnNode = targetNode;
        }
        recolorNode(currentNode, colorPalette.get("checked"));
        currentNode.setVisited(true);
    }

    public Stack finalPath(Node startNode, Node currentNode){
        Stack path = new Stack();
        while(currentNode!=null){
            Edge currentEdge = currentNode.getPreviousEdge();
            System.out.println(currentNode.getName());
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

    public void printFinalPath(Stack path){
        String pathData = "";
        while(!path.isEmpty()){
            pathData += (path.pop().getName()+", ");
        }
        System.out.println(pathData);
    }

    public void threadSleep(){
        try{
            Thread.sleep(400); // pause the algorithm for 750ms
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void recolorNode(Node currentNode, Color newColor){
        System.out.println(newColor);
        currentNode.setColor(newColor);
        canvas.repaint();
    }

    public void recolorEdge(Edge currentEdge, Color newColor){
        currentEdge.setColor(newColor);
        canvas.repaint();
    }
}
