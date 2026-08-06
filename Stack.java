
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
     * Converts nodes into the stack
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
    /* object variables */
    private StackNode top;
    
    /* constructor */
    public Stack(){}//empty
    
    /*
     * checks if the stack is empty or not
     * @param null
     * @return boolean
     */
    public boolean isEmpty(){
        return(this.top==null);
    }
    
    /**
     * Adds a node to the top of the stack
     * @param node - that will be added
     * @return void/null
     */ 
    public void push(Node node){
        StackNode newNode = new StackNode(node);
        newNode.setNext(this.top);
        this.top = newNode;
    }
    /**
     * Removes a node from the top of the stack
     * @param null
     * @return node - that was removed
     */ 
    public Node pop(){
        StackNode temp = this.top;
        Node tempNode = temp.getNode();
        this.top = top.getNext();
        return(tempNode);
    }
}