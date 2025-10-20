
public class PlayerTest{
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
