
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    public static void main(String[] args){
        Node one = new Node();
        Node two = new Node();
        Node three = new Node();
        one.addDestination(two, 5);
        two.addDestination(three, 3);
    }
}