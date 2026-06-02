import java.util.ArrayList;
/**
 * This class finds the shortest path 
 *
 * @Sol Prebble
 * @version (a version number or a date)
 */
public class PathFinder
{
    Queue path = new Queue();
    public PathFinder(Graph graph){
        generateQueue(graph);
        runAlgorithm(graph);
    }
    public void runAlgorithm(Graph graph){
        while(!path.isEmpty()){
            Node currentNode = path.dequeue();
            if(!currentNode.getVisited()){
                findShortestLocalPath(currentNode);
            }
        }    
    }
    public Queue generateQueue(Graph graph){
        int index = graph.getMap().size() - 1;
        while(index >= 0){
            String indexStr = String.valueOf(index);
            Node currentNode = graph.getNode(indexStr);
            path.priorityEnqueue(currentNode);
            index--;
        }
        return(path);
    }
    public void findShortestLocalPath(Node currentNode){
        for(int x=0;x< currentNode.getEdges().size(); x++){
            Edge currentEdge = currentNode.getEdges().get(x);
            Node targetNode = currentEdge.getTargetNode();
            int proposedDistance = currentNode.getDistanceFromStart() + currentEdge.getDistance();
            
            if(targetNode.getDistanceFromStart() > proposedDistance){
                targetNode.setDistanceFromStart(proposedDistance);
            }
        }
        currentNode.setVisited(true);
    }
}
