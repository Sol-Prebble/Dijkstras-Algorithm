
/**
 * Custom data structure class for a 'Stack'
 * Last in, last out
 *
 * @author Sol Prebble
 * @8/6/26
 */
public class Stack
{
    /**
     * Wrapper class
     */
    private class StackNode{
        Node node;
        StackNode next;
        public StackNode(Node node){
            this.node = node;
            this.next = null;
        }

        /* getters */
        public Node getNode(){
            return(this.node);
        }

        public StackNode getNext(){
            return(this.next);
        }

        /* setters */
        public void setNext(StackNode newNode){
            this.next = newNode;
        }
    }
    private StackNode top;
    public Stack(){}
    
    public boolean isEmpty(){
        return(this.top==null);
    }
    
    public void push(Node node){
        StackNode newNode = new StackNode(node);
        newNode.setNext(this.top);
        this.top = newNode;
    }
    
    public Node pop(){
        StackNode temp = this.top;
        Node tempNode = temp.getNode();
        this.top = top.getNext();
        return(tempNode);
    }
}