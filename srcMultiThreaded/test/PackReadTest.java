public class PackReadTest {
  public static void main(String[] args) {
    var cards = PackReader.readPack("pack.txt");
    for (Card c : cards) {
      System.out.println(c.getValue());
    }
  }
}
