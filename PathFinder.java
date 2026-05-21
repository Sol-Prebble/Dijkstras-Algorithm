
/**
 * This class finds the shortest path 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PathFinder
{
    Queue queue;
    public PathFinder(){
        this.queue = new Queue();
        findEdgesToCheck(2);
    }
    public void findShortestPath(Node start, Node target){
        
    }
    public void findEdgesToCheck(int nodeCount){
        Node one = new Node();
        Node two = new Node();
        Node three = new Node();
        one.addDestination(two, 5);
        two.addDestination(three, 6);
        queue.enqueue(one);
        queue.enqueue(two);
        queue.enqueue(three);
        queue.print();
    }
}