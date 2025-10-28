
import java.util.ArrayDeque;
import java.util.Deque;

public final class CardDeck {
    private static final Object TURN_LOCK = new Object();

    private final int id;
    private final Deque<Card> cards;

    public CardDeck(int id) {
        this.id = id;
        this.cards = new ArrayDeque<>();
    }

    public synchronized void addCard(Card card) {
        cards.addLast(card);
    }

    public synchronized Card drawCard() {
        return cards.pollFirst();
    }

    public synchronized String getContents() {
        String contents = "";
        for (Card c : cards) {
            contents += c.getValue() + " ";
        }
        return contents;
    }

    public synchronized int getSize() {
        return cards.size();
    }

    public int getId() {
        return id;
    }

    public Card drawThenDiscardTo(CardDeck rightDeck, Card discardCard) {
        synchronized (TURN_LOCK) {
            Card drawn;
            synchronized (this) { drawn = cards.pollFirst(); }
            synchronized (rightDeck) { rightDeck.cards.addLast(discardCard); }
            return drawn;
        }
    }
}

