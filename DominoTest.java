import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DominoTest {
    @Test
    void testConstructorAndGetters() {
        Domino domino = new Domino(3, 5, null);
        assertEquals(3, domino.getLeftValue());
        assertEquals(5, domino.getRightValue());
        assertNull(domino.getNext());
    }
    @Test
    void testSetNextAndGetNext() {
        Domino domino1 = new Domino(2, 4, null);
        Domino domino2 = new Domino(4, 6, null);
        domino1.setNext(domino2);
        assertEquals(domino2, domino1.getNext());
    }
    @Test
    void testRotate() {
        Domino domino = new Domino(1, 2, null);
        domino.rotate();
        assertEquals(2, domino.getLeftValue());
        assertEquals(1, domino.getRightValue());
    }
    @Test
    void testToString() {
        Domino domino = new Domino(3, 4, null);
        assertEquals("[3|4]", domino.toString());
    }
}

