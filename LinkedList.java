import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * The LinkedList class represents a chain of objects and provides various
 operations on the list.
 * This LinkedList is only expected to contain dominoes.
 * It implements the Iterable interface.
 */
public class LinkedList implements Iterable<Integer> {
    /**
     * The first domino in the linked list.
     */
    private Domino firstDomino;
    /**
     * Creates an empty linked list.
     */
    public LinkedList(){
        this.firstDomino = null;
    }
    /**
     * Gets the first domino in the linked list.
     *
     * @return The first domino in the linked list.
     */
    protected Domino getFirstDomino(){
        return this.firstDomino;
    }
    /**
     * Sets the first domino in the linked list.
     *
     * @param domino The domino to set as the first domino.
     */
    protected void setFirstDomino(Domino domino){
        this.firstDomino = domino;
    }
    /**
     * Checks if the linked list is empty.
     *
     * @return true or false based on if the linked list is empty or not.
     */
    public boolean isEmpty(){
        return this.getFirstDomino() == null;
    }
    /**
     * Gets the right value of the first domino in the linked list.
     *
     * @return The right value of the first domino.
     * @throws NoSuchElementException If the linked list is empty.
     */
    public int firstElement(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            return this.getFirstDomino().getRightValue();
        }
    }
    /**
     * Returns the length of the linked list.
     *
     * @return The number of elements in the linked list.
     */
    public int length() {
        int lengthSoFar = 0;
        Domino nodeptr = getFirstDomino();
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
    }
    /**
     * Adds a new domino to the front of the linked list.
     *
     * @param left The left value of the domino to be added.
     * @param right The right value of the domino to be added.
     */
    public void addToFront(int left, int right) {
        setFirstDomino(new Domino(left,right,getFirstDomino()));
    }
    /**
     * Removes the first domino from the front of the linked list.
     */
    public void removeFromFront(){
        this.setFirstDomino(getFirstDomino().getNext());
    }
    /**
     * Adds a new domino to the end of the linked list.
     *
     * @param left The left value of the new domino.
     * @param right The right value of the new domino.
     */
    public void addToEnd(int left, int right) {
        if (isEmpty())
            addToFront(left,right);
        else {
            Domino nodeptr = getFirstDomino();
            while (nodeptr.getNext() != null)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new Domino(left,right, null));
        }
    }
    /**
     * Returns an iterator for the list.
     *
     * @return An iterator for the linked list.
     */
    public Iterator<Integer> iterator(){
        return new LinkedListIterator(getFirstDomino());
    }
}