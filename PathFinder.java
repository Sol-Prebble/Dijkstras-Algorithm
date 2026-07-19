import java.awt.Color;
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
    public void runAlgorithm(Node startNode, Node endNode){
        new Thread(() -> {
                    nodeOrder.enqueue(startNode);
                    startNode.setDistanceFromStart(0);
                    while(!nodeOrder.isEmpty()){
                        Node currentNode = nodeOrder.dequeue();
                        if(!currentNode.getVisited()){
                            recolorNode(currentNode, Color.YELLOW);
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

        Node returnNode = null;
        for(int x=0;x< currentNode.getEdges().size(); x++){
            Edge currentEdge = currentNode.getEdges().get(x);
            recolorEdge(currentEdge, Color.YELLOW);
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
            recolorEdge(currentEdge, Color.LIGHT_GRAY);
            returnNode = targetNode;
        }
        recolorNode(currentNode, Color.LIGHT_GRAY);
        currentNode.setVisited(true);
    }

    public Stack finalPath(Node startNode, Node currentNode){ //# last edge doesn't get changed
        Stack path = new Stack();
        while(currentNode!=null){
            Edge currentEdge = currentNode.getPreviousEdge();
            System.out.println(currentNode.getName());
            recolorNode(currentNode, Color.GREEN);
            if(currentEdge != null){
                recolorEdge(currentEdge, Color.GREEN);
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
            Thread.sleep(100); // pause the algorithm for 750ms
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void recolorNode(Node currentNode, Color newColor){
        currentNode.setColor(newColor);
        canvas.repaint();
    }

    public void recolorEdge(Edge currentEdge, Color newColor){
        currentEdge.setColor(newColor);
        canvas.repaint();
    }
}
