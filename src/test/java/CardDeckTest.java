import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardDeckTest {

    @Test
    void drawOrder_isFIFO() {
        //add two cards in order
        CardDeck deck = new CardDeck(1);
        deck.addCard(new Card(10));
        deck.addCard(new Card(20));
        //should return in order FIFO
        assertEquals(10, deck.drawCard().getValue(), "First draw should be 10");
        assertEquals(20, deck.drawCard().getValue(), "Second draw should be 20");
    }

    @Test
    void size_updatesOnAddAndDraw() {
        CardDeck deck = new CardDeck(1);
        deck.addCard(new Card(5));
        deck.addCard(new Card(6));
        //size reflecets addition
        assertEquals(2, deck.getSize(), "Size should be 2 after adding two cards");

        deck.drawCard(); // remove one
        assertEquals(1, deck.getSize(), "Size should be 1 after drawing one card");
    }
}