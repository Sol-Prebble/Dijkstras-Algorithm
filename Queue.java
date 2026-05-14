
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
    public void enqueue(QueueNode newNode){ //# working on priority
        if(isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            int newNodeDistance = newNode.getNode().getDistance();
            int headDistance = this.head.getNode().getDistance();
            QueueNode temp = this.head;
            int tempDistance = temp.getNode().getDistance();
            
            while(newNodeDistance > tempDistance){
                temp = temp.getNext();
                tempDistance = temp.getNode().getDistance();
            }
            //temp.get
            this.tail.setNext(newNode);
            this.tail = newNode;
            
        }
    }
    public QueueNode dequeue(){
        if(isEmpty()){
            return(null);
        } else {
            QueueNode queueNode = this.head;
            this.head = queueNode.getNext();
            if(this.head == null){
                this.tail = null;
            }
            return queueNode;
        }
    }
}