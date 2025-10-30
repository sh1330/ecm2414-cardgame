public class TestCardDeck {
    public static void main(String[] args) {
        //basic FIFO order
        CardDeck deck = new CardDeck(1);
        deck.addCard(new Card(10));
        deck.addCard(new Card(20));
        if (deck.size() != 2) {
            System.out.println("FAIL: Deck size expected 2, got " + deck.size());
            return;
        }
        //Should draw 10 then 20
        Card c1 = deck.drawCard();
        Card c2 = deck.drawCard();
        if (c1.getValue() != 10 || c2.getValue() != 20) {
            System.out.println("FAIL: Deck order wrong");
            return;
        }

        // Thread safety check
        System.out.println("Running concurrency safety test...");
        CardDeck sharedDeck = new CardDeck(2);
        Runnable adder = () -> {
            for (int i = 0; i < 1000; i++) sharedDeck.addCard(new Card(i));
        };
        Thread t1 = new Thread(adder);
        Thread t2 = new Thread(adder);
        //start both threads and wait for them to finish
        t1.start(); t2.start();
        try {
            t1.join(); t2.join();
        } catch (InterruptedException e) {}
        //expect 2000 cards if  synchronization works
        if (sharedDeck.size() != 2000) {
            System.out.println("FAIL: Expected 2000, got " + sharedDeck.size());
            return;
        }
        System.out.println("PASS: CardDeck thread safety OK");
    }
}
/*public class TestCardDeck{
    public static void main(String[] args) {
        CardDeck left = new CardDeck(1);
        CardDeck right = new CardDeck(2);

        // load left with 1,2,3
        left.addCard(new Card(1));
        left.addCard(new Card(2));
        left.addCard(new Card(3));

        // do three atomic moves, discarding 9s to the right
        for (int i = 0; i < 3; i++) {
            Card drawn = left.drawThenDiscardTo(right, new Card(9));
            System.out.println("drawn = " + (drawn == null ? "null" : drawn.getValue()));
        }

        System.out.println("left:  " + left.contentsString());  // should be ""
        System.out.println("right: " + right.contentsString()); // should be "9 9 9"
    }
}
**/
