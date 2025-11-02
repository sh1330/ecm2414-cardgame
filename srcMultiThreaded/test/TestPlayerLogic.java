import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerLogic {

    @Test
    void nonWinningAndWinningHands_andDiscardLogic() {
        CardDeck left = new CardDeck(1);
        CardDeck right = new CardDeck(2);

        // Non-winning hand
        Player p = new Player(1); // constructor requires only id

        p.addCard(new Card(1));
        p.addCard(new Card(2));
        p.addCard(new Card(3));
        p.addCard(new Card(4));
        assertFalse(p.hasWinningHand(), "Should be a losing hand");

        // Winning hand
        p = new Player(1);
        for (int i = 0; i < 4; i++) p.addCard(new Card(7));
        assertTrue(p.hasWinningHand(), "Should be a winning hand of 7s");

        // Discard logic
        p = new Player(2);
        p.addCard(new Card(2));  // matching value
        p.addCard(new Card(5));  // should be discarded
        Card discarded = p.chooseDiscard();
        assertNotNull(discarded, "discard a card");
        assertEquals(5, discarded.getValue(), "Should discard the non-matching card");
    }
}