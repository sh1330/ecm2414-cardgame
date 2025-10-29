
public class TestCardDeck{
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
