public class TestCard {
    public static void main(String[] args) {
        //Create a card for testing
        Card c = new Card(5);
        System.out.println("Testing Card...");

        //Check that getValue() returns the correct number
        if (c.getValue() != 5) {
            System.out.println("FAIL: Expected 5, got " + c.getValue());
            return; //Early Quit on failure     
        }
        //Check that tostring works correctly
        if (!c.toString().equals("5")) {
            System.out.println("FAIL: toString() incorrect: " + c.toString());
            return;
        }
        
        System.out.println("PASS: Card basic tests");
    }
}