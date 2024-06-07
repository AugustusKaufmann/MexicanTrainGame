import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * The LinkedListIterator class provides an iterator for iterating over a linked
 list of Dominoes.
 * It implements the Iterator interface for iterating over elements of type
 Integer.
 *
 * @param <Integer> The type of elements returned by the iterator.
 */
public class LinkedListIterator<Integer> implements Iterator<java.lang.Integer> {
    /**
     * The current position of the iterator in the list.
     */
    private Domino nodeptr;
    /**
     * The reference to the previous domino in the linked list.
     */
    private Domino previous;
    /**
     * Constructs a new LinkedListIterator with a first domino.
     *
     * @param firstdomino The first domino in the linked list.
     */
    public LinkedListIterator(Domino firstdomino){
        this.nodeptr = firstdomino;
    }
    /**
     * Checks if there are more elements in the linked list.
     *
     * @return true or false based on if there are more elements in the list.
     */
    public boolean hasNext(){
        return nodeptr != null;
    }
    /**
     * Returns the next element in the linked list.
     *
     * @return The next element in the linked list.
     * @throws NoSuchElementException if there are no more elements.
     */
    public java.lang.Integer next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        else{
            Domino temp = nodeptr;
            nodeptr = nodeptr.getNext();
            return temp.getRightValue();
        }
    }
    public void remove(Domino domino){
        Domino current = nodeptr;
        Domino previous = null;
        while (current != null) {
            if (current == domino) {
                previous.setNext(current.getNext());
            }
            previous = current;
            current = current.getNext();
        }
    }
}
