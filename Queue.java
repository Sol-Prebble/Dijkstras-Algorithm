
/**
 * This is the class for a 'queue' data structure
 * It takes user input of data and adds it to the set
 * It is a first in - first out data structure
 * It utilises the node class to create the queue
 */
public class Queue{
    private QueueNode head = null;
    private QueueNode tail = null;
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
    }
    public Queue(){
        // Left empty
    }
    public boolean isEmpty(){
        return(this.head==null);
    }
    public void enqueue(QueueNode newNode){
        if(isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            //this.tail = newNode.next;
            this.tail = newNode;
        }
    }
    public QueueNode dequeue(){
        if(isEmpty()){
            return(null);
        } else {
            QueueNode queueNode = this.head;
            this.head = queueNode.getNext();
            System.out.println("dequeue method node: "+queueNode.getNode().getName());
            return queueNode;
        }
    }
}