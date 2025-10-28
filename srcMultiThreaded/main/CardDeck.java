import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public final class CardDeck {
    private static final Object TURN_LOCK = new Object();
    private final Deque<Card> q = new ArrayDeque<>();

    public CardDeck() { }

    public CardDeck(Collection<Card> init) {
      q.addAll(init);
    }

    public synchronized int size() { return q.size(); }

    public synchronized boolean isEmpty() {
      return q.isEmpty();
    }

    public synchronized void discardRight(Card c) {
      q.addLast(c);
    }

    public synchronized Card drawLeft() { 
      return q.pollFirst();
    }

    public Card drawThenDiscardTo(CardDeck rightDeck, Card discardCard) {
        synchronized (TURN_LOCK) {
            Card drawn;
            synchronized (this) { drawn = q.pollFirst(); }
            synchronized (rightDeck) { rightDeck.q.addLast(discardCard); }
            return drawn;
        }
    }
}
