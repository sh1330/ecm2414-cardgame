
/*public class PlayerTest{
    public static void main(String[] args) {
        Player p = new Player(1);
        p.addCard(new Card(3));
        p.addCard(new Card(3));
        p.addCard(new Card(3));
        p.addCard(new Card(3));

        System.out.println("Player " + p.getId() + " hand: " + p.getHand());
        System.out.println("Winning hand? " + p.hasWinningHand());
    }
}
*/
public class PlayerTest {

    public static void main(String[] args) {
        int passed = 0, failed = 0;

        // Winning hand test
        Player p1 = new Player(1);
        for (int i = 0; i < 4; i++) p1.addCard(new Card(3));

        if (p1.hasWinningHand()) passed++; else failed++;

        // Non-winning hand
        Player p2 = new Player(2);
        p2.addCard(new Card(1));
        p2.addCard(new Card(2));
        p2.addCard(new Card(3));
        p2.addCard(new Card(4));

        if (!p2.hasWinningHand()) passed++; else failed++;

        // Hand string output test
        String hand = p2.getHand();
        if (hand.contains("1") && hand.contains("4")) passed++; else failed++;

        System.out.println("PlayerTest: " + passed + " passed, " + failed + " failed");
    }
}