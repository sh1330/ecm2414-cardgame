public class CardDeckTest {

    public static void main(String[] args) {
        int passed = 0, failed = 0;

        CardDeck deck = new CardDeck(1);
        deck.addCard(new Card(10));
        deck.addCard(new Card(20));

        if (deck.drawCard().getValue() == 10) passed++; else failed++;
        if (deck.drawCard().getValue() == 20) passed++; else failed++;

        deck.addCard(new Card(5));
        deck.addCard(new Card(6));
        if (deck.getSize() == 2) passed++; else failed++;

        deck.drawCard(); // remove one
        if (deck.getSize() == 1) passed++; else failed++;

        System.out.println("CardDeckTest: " + passed + " passed, " + failed + " failed");
    }
}