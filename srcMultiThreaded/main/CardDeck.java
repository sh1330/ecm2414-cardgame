
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class CardDeck {
    private final int id;
    private final Deque<Card> q = new ArrayDeque<>();

    public CardDeck(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    
    public synchronized void addCard(Card c) {
        q.addLast(c);
    }

    public synchronized Card drawCard() {
        return q.pollFirst();
    }

    public synchronized int size() { return q.size(); }

    public synchronized boolean isEmpty() { return q.isEmpty(); }

    
    public synchronized List<Integer> snapshot() {
        List<Integer> vals = new ArrayList<>(q.size());
        for (Card c : q) vals.add(c.getValue());
        return vals;
    }
    
    public synchronized String getContents() {
      return contentsString();
    }

    
    public synchronized String contentsString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Card c : q) {
            if (!first) sb.append(' ');
            sb.append(c.getValue());
            first = false;
        }
        return sb.toString();
    }

    public Card drawThenDiscardTo(CardDeck rightDeck, Card discardCard) {
        if (rightDeck == null) throw new IllegalArgumentException("rightDeck is null");
        if (discardCard == null) throw new IllegalArgumentException("discardCard is null");

        CardDeck first = this.id <= rightDeck.id ? this : rightDeck;
        CardDeck second = this.id <= rightDeck.id ? rightDeck : this;

        synchronized (first) {
            synchronized (second) {
                Card drawn = q.pollFirst();
                rightDeck.q.addLast(discardCard);
                return drawn;
            }
        }
    }
}
