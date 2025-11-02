import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCard {

    @Test
    void cardStoresValue() {
        Card c = new Card(10);
        assertEquals(10, c.getValue());
    }

    @Test
    void cardEqualityByValue_ifApplicable() {
        // If Card implements equals/hashCode by value, this will pass.
        // If not, you can remove this test.
        Card c1 = new Card(5);
        Card c2 = new Card(5);
        assertEquals(c1.getValue(), c2.getValue());
    }
}