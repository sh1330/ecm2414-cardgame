import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    void value_isReturned() {
        Card c = new Card(5);
        assertEquals(5, c.getValue());
    }

    @Test
    void toString_returnsValueString() {
        assertEquals("7", new Card(7).toString());
    }   
}