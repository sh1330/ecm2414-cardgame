import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void winningHand_allSameValues() {
        // four identical cards
        Player p1 = new Player(1);
        for (int i = 0; i < 4; i++) {
            p1.addCard(new Card(3));
        }// four of a kind should win
        assertTrue(p1.hasWinningHand(), "Player should have a winning hand when all 4 are same value");
    }

    @Test
    void nonWinningHand_mixedValues() {
        Player p2 = new Player(2);
        p2.addCard(new Card(1));
        p2.addCard(new Card(2));
        p2.addCard(new Card(3));
        p2.addCard(new Card(4)); //losing hand
        assertFalse(p2.hasWinningHand(), "Mixed values should not be a winning hand");
    }

    @Test
    void getHand_containsValues() {
        Player p2 = new Player(2);
        p2.addCard(new Card(1));
        p2.addCard(new Card(2));
        p2.addCard(new Card(3));
        p2.addCard(new Card(4));

        String hand = p2.getHand();
        assertNotNull(hand); //expected values ->
        assertTrue(hand.contains("1"), "Hand should contain 1");
        assertTrue(hand.contains("4"), "Hand should contain 4");
    }
}