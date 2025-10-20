import java.util.ArrayList;

public class Player {
  
  private int id;
  private ArrayList<Card> hand;

  public Player(int id) {
    this.id = id;
    this.hand = new ArrayList<>();
  }

  public void addCard(Card card) {
    hand.add(card);
  }

  public boolean hasWinningHand() {
    if (hand.size() != 4) {
      return false;
    }

    int firstValue = hand.get(0).getValue();
    for (Card c : hand) {
      if (c.getValue() != firstValue){
        return false;
      }
    }
    return true;
  }

  public String getHand() {
    String result = "";
    for (Card c : hand) {
      result += c.getValue() + " ";
    }
    return result;
  }

  public int getId() {
    return id;
  }

}
