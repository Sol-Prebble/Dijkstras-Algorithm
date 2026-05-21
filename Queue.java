
/**
 * This is the class for a 'queue' data structure
 * It takes user input of data and adds it to the set
 * It is a first in - first out data structure
 * It utilises the node class to create the queue
 */
// Note: could add a this.tail for effectiency to check whether the new node distance is the largest (more efficient)
public class Queue{
    private QueueNode head = null;
    /**
     * Wrapper class
     */
    private class QueueNode{
        Node node;
        QueueNode next;
        public QueueNode(Node node){
            this.node = node;
            this.next = null;
        }

        /* getters */
        public Node getNode(){
            return(this.node);
        }

        public QueueNode getNext(){
            return(this.next);
        }

        public QueueNode getNextRecursive(int n){
            if(n == 0){
                return(this);
            }
            if(this.next == null){
                return(null);
            }
            return(this.next.getNextRecursive(n-1));
        }

        /* setters */
        public void setNext(QueueNode newNode){
            this.next = newNode;
        }
    }
    public Queue(){
        // Left empty
    }

    public boolean isEmpty(){
        return(this.head==null);
    }
    
    /**
     * This method adds a node to the queue
     * It's position is based off it's distance variable
     * It takes the node to add and returns nothing (void)
     */
    public void enqueue(Node node){
        QueueNode newNode = new QueueNode(node);
        int newNodeDistance = newNode.getNode().getDistance();
        QueueNode temp = this.head;
        //int queueIndex = 0;

        QueueNode dummy = new QueueNode(null);
        dummy.setNext(this.head);
        QueueNode previous = dummy;
        while(temp != null && newNodeDistance > temp.getNode().getDistance()){
            previous = temp;
            temp = temp.getNext();
        }
        newNode.setNext(previous.getNext());
        previous.setNext(newNode);
        this.head = dummy.getNext();
    }
    /**
     * This method removes the first node in the queue
     * It takes nothing and returns the QueueNode that was removed
     */
    public QueueNode dequeue(){
        if(isEmpty()){
            return(null);
        } else {
            QueueNode queueNode = this.head;
            this.head = queueNode.getNext();
            return queueNode;
        }
    }
    public void print(){
        Queue temp = new Queue();
        String fullQueueString = "";
        temp = this;
        while(!temp.isEmpty()){
            fullQueueString += "\n"+temp.dequeue().getNode().getFirstEdge();
        }
        System.out.println(fullQueueString);
    }
}