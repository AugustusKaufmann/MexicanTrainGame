/**
 * The DominoTrain class represents a linked list of Domino objects.
 * It is a more specialized linked list class used in the Mexican Train game.
 * It provides specific operations related to dominoes in a domino train.
 */
public class DominoTrain extends LinkedList{
    /**
     * The starting value of the domino train.
     */
    private int startvalue;
    /**
     * Constructs a new DominoTrain with the specified starting value.
     *
     * @param start The starting value for the domino train.
     */
    public DominoTrain(int start){
        super();
        this.startvalue = start;
    }
    /**
     * Adds a domino to the front of the train based on if the domino matches the
     current front domino
     * Rotates the domino when necessary to ensure the equal values are touching.
     * Overridden from the linked list class.
     * This implementation is used in the Mexican Train game.
     *
     * @param domino The domino to be added to the front of the train.
     */
    public void addToFront(Domino domino){
        if(isEmpty() && domino.getLeftValue()==this.startvalue){
            super.addToFront(domino.getLeftValue(), domino.getRightValue());
        }
        else if(isEmpty() && domino.getRightValue()==this.startvalue){
            domino.rotate();
            super.addToFront(domino.getLeftValue(), domino.getRightValue());
        }
        else if(!isEmpty() &&
                domino.getLeftValue()==(this.getFirstDomino().getRightValue())){
            super.addToFront(domino.getLeftValue(), domino.getRightValue());
        }
        else if(!isEmpty() &&
                domino.getRightValue()==(this.getFirstDomino().getRightValue())){
            domino.rotate();
            super.addToFront(domino.getLeftValue(), domino.getRightValue());
        }
    }
    /**
     * Gets the end value of the domino train.
     * Chose the "end" value to be the right side as personal preference.
     * @return The right value of the first domino in the train.
     */
    public int getEndValue(){
        return this.getFirstDomino().getRightValue();
    }
    /**
     * Checks if a given domino can be added to the train.
     *Dominoes are only added to the front of the train.
     * @param domino The domino to be checked for compatibility.
     * @return true if the domino can be added to the train, false otherwise.
     */
    public boolean canAdd(Domino domino){
        return domino.getLeftValue()==this.firstElement()||
                domino.getRightValue()==this.firstElement();
    }
}