
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
    public PathFinder(Graph graph){
        this.graph = graph;
    }
    public void runAlgorithm(Node startNode, Node endNode){
        nodeOrder.enqueue(startNode);
        startNode.setDistanceFromStart(0);
        while(!nodeOrder.isEmpty()){
            Node currentNode = nodeOrder.dequeue();
            if(!currentNode.getVisited()){
                findShortestLocalPath(currentNode);
            }
        }
        finalPath(startNode, endNode);
    }
    public Node findShortestLocalPath(Node currentNode){
        System.out.println("test1");
        System.out.println(currentNode.getEdges());
        Node returnNode = null;
        for(int x=0;x< currentNode.getEdges().size(); x++){
            Edge currentEdge = currentNode.getEdges().get(x);
            Node targetNode = currentEdge.getTargetNode();
            int proposedDistance = currentNode.getDistanceFromStart() + currentEdge.getDistance();
            System.out.println("test2");
            if(proposedDistance < targetNode.getDistanceFromStart()){
                System.out.println("test3");
                targetNode.setDistanceFromStart(proposedDistance);
                nodeOrder.priorityEnqueue(targetNode);
                targetNode.setPrevious(currentNode);
                System.out.println(targetNode.getPrevious().getName());
            }
            returnNode = targetNode;
        }
        currentNode.setVisited(true);
        return(returnNode);
    }
    public Stack finalPath(Node startNode, Node currentNode){
        Stack path = new Stack();
        while(currentNode!=null){
            path.push(currentNode);
            System.out.println("post push: "+currentNode.getName());
            currentNode = currentNode.getPrevious();
            System.out.println("post .getPrevious(): "+currentNode);
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
