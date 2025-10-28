import java.util.ArrayDeque;
import java.util.Deque;

public class CardDeck {

  private int id;
  private Deque<Card> cards;

  public CardDeck(int id) {
    this.id = id;
    this.cards = new ArrayDeque<>();
  }

  public void addCard(Card card) {
    cards.addLast(card);
  }

  public Card drawCard() {
    return cards.pollFirst();
  }

  public String getContents() {
    String contents = "";
    for (Card c : cards) {
      contents += c.getValue() + " ";
    }
    return contents;
  }

  public int getId(){
    return id;
  }

  public int getSize() {
    return cards.size();
  }
  
}
