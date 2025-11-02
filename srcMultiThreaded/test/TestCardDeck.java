import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCardDeck {

    private int deckSize(CardDeck deck) {
        try { return (int) CardDeck.class.getMethod("size").invoke(deck); } catch (Exception ignored) {}
        try { return (int) CardDeck.class.getMethod("getSize").invoke(deck); } catch (Exception ignored) {}
        try { return (int) CardDeck.class.getMethod("count").invoke(deck); } catch (Exception ignored) {}
        fail("CardDeck needs a size/count method named size(), getSize(), or count()");
        return -1;
    }

    @Test
    void fifoOrder() {
               // capacity 1 forces discards/behavior decisions in some implementations but here we just add and draw to check FIFO order.
        CardDeck deck = new CardDeck(1);
        deck.addCard(new Card(10));
        deck.addCard(new Card(20));

        assertEquals(2, deckSize(deck), "Deck size should be 2");
        Card c1 = deck.drawCard();
        Card c2 = deck.drawCard();

        assertNotNull(c1);
        assertNotNull(c2);
        assertEquals(10, c1.getValue());
        assertEquals(20, c2.getValue());
    }

    @Test
    void threadSafety_addsFromTwoThreads() throws InterruptedException {
        //Shared deck and two threads with 1000 cards each
        CardDeck sharedDeck = new CardDeck(2);
        Runnable adder = () -> {
            for (int i = 0; i < 1000; i++) sharedDeck.addCard(new Card(i));
        };
            //Start with reasonable timeout to avoid deadlock
        Thread t1 = new Thread(adder);
        Thread t2 = new Thread(adder);
        t1.start();
        t2.start();

        t1.join(5000);
        t2.join(5000);
            // Assert with threads correct
        assertFalse(t1.isAlive(), "t1 should finish");
        assertFalse(t2.isAlive(), "t2 should finish");
        assertEquals(2000, deckSize(sharedDeck), "Expected 2000 cards");
    }
}