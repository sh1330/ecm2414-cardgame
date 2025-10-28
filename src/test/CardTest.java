public class CardTest {

    public static void main(String[] args) {
        int passed = 0, failed = 0;

        Card c = new Card(5);
        if (c.getValue() == 5) passed++; else failed++;

        if ("7".equals(new Card(7).toString())) passed++; else failed++;

        System.out.println("CardTest: " + passed + " passed, " + failed + " failed");
    }
}