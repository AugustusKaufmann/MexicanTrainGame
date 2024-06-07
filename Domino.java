/**
 * The Domino class represents a domino piece with two values (left and right) and
 a reference to the domino that comes next.
 * Each domino can be rotated, and its state can be accessed or modified using
 various methods.
 */
public class Domino {
    /**
     * The left value of the domino as an integer.
     */
    private int leftvalue;
    /**
     * The right value of the domino as an integer.
     */
    private int rightvalue;
    /**
     * The next domino in the list.
     */
    private Domino nextdomino;
    /**
     * Constructs a new Domino with the specified left and right values and a
     reference to the next domino.
     *
     * @param left The left value of the domino.
     * @param right The right value of the domino.
     * @param next The next domino.
     */
    public Domino(int left, int right, Domino next){
        this.leftvalue = left;
        this.rightvalue = right;
        this.nextdomino = next;
    }
    /**
     * Gets the left value of the domino.
     *
     * @return The left value of the domino.
     */
    public int getLeftValue(){
        return this.leftvalue;
    }
    /**
     * Gets the right value of the domino.
     *
     * @return The right value of the domino.
     */
    public int getRightValue(){
        return this.rightvalue;
    }
    /**
     * Gets the next domino.
     *
     * @return The next domino in a linked list.
     */
    public Domino getNext(){
        return this.nextdomino;
    }
    /**
     * Sets the next domino in a list.
     *
     * @param domino The domino to set as the next in the linked list.
     */
    public void setNext(Domino domino){
        this.nextdomino = domino;
    }
    /**
     * Rotates the domino.
     */
    public void rotate(){
        int temp = this.leftvalue;
        this.leftvalue = this.rightvalue;
        this.rightvalue = temp;
    }
    /**
     * Expresses the domino as a string.
     *
     * @return A string representation of the domino giving the domino's left and
    right values.
     */
    public String toString(){
        return "["+this.getLeftValue()+"|"+this.getRightValue()+"]";
    }
}
