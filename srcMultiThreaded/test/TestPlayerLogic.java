
public class TestPlayerLogic {
    public static void main(String[] args) {
        System.out.println("=== Running TestPlayerLogic ===");

        // Stub decks and dummy game to satisfy constructor
        CardDeck left = new CardDeck(1);
        CardDeck right = new CardDeck(2);
        CardGame dummyGame = new CardGameStub();

        // Test 1 losing hand
        Player p = new Player(1, left, right, dummyGame);
        p.addCard(new Card(1));
        p.addCard(new Card(2));
        p.addCard(new Card(3));
        p.addCard(new Card(4));

        if (p.hasWinningHand()) {
            System.out.println("FAIL: Should not have a winning hand yet");
            return;
        }

        // Test 2 Winning hand test
        p = new Player(1, left, right, dummyGame);
        for (int i = 0; i < 4; i++) {
            p.addCard(new Card(7));
        }

        if (!p.hasWinningHand()) {
            System.out.println("FAIL: Expected winning hand of 7s");
            return;
        }

        //Test 3 Discard logic 
        p = new Player(2, left, right, dummyGame);
        p.addCard(new Card(2));  // matching value
        p.addCard(new Card(5));  // should be discarded
        Card discarded = p.chooseDiscard();
        if (discarded == null || discarded.getValue() != 5) {
            System.out.println("FAIL: Discard logic incorrect");
            return;
        }

        System.out.println("PASS: Player logic OK");
    }


    static class CardGameStub extends CardGame {
        public CardGameStub() { super(1, createTempPack()); }

        // Always report "game not over"
        @Override public boolean isGameOver() { return false; }

        // Helper to generate a tiny placeholder pack file
        private static String createTempPack() {
            try {
                java.io.File f = java.io.File.createTempFile("dummyPack", ".txt");
                java.io.PrintWriter pw = new java.io.PrintWriter(f);
                for (int i = 0; i < 8; i++) pw.println(1);
                pw.close();
                return f.getAbsolutePath();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}