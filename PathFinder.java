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
                            currentNode.setColor(Color.YELLOW);
                            canvas.repaint();
                            try{
                                Thread.sleep(1000);
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            findShortestLocalPath(currentNode);
                        }
                    }
                    finalPath(startNode, endNode);
                    canvas.repaint();
            }).start();
    }

    public void findShortestLocalPath(Node currentNode){ //# working on chaning color back to normal after it has been checked
        new Thread(() -> {
                    Node returnNode = null;
                    for(int x=0;x< currentNode.getEdges().size(); x++){
                        Edge currentEdge = currentNode.getEdges().get(x);
                        currentEdge.setColor(Color.YELLOW);
                        canvas.repaint();
                        try{
                            Thread.sleep(1000);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        Node targetNode = currentEdge.getTargetNode();
                        int proposedDistance = currentNode.getDistanceFromStart() + currentEdge.getDistance();
                        if(proposedDistance < targetNode.getDistanceFromStart()){
                            targetNode.setDistanceFromStart(proposedDistance);
                            nodeOrder.priorityEnqueue(targetNode);
                            targetNode.setPrevious(currentNode);
                            currentEdge.setColor(Color.GREEN);
                            canvas.repaint();
                        } else {
                            currentEdge.setColor(Color.DARK_GRAY);
                            canvas.repaint();
                        }
                        returnNode = targetNode;
                        
                    }
                    currentNode.setVisited(true);
            }).start();
    }

    public Stack finalPath(Node startNode, Node currentNode){
        Stack path = new Stack();
        while(currentNode!=null){
            path.push(currentNode);
            currentNode = currentNode.getPrevious();
        }
        //path.push(currentNode);
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
}
