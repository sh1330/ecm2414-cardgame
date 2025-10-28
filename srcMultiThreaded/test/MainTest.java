public class MainTest {
    public static void main(String[] args) {
        System.out.println("\n=== RUNNING MANUAL TEST SUITE ===\n");

        CardTest.main(null);
        CardDeckTest.main(null);
        PlayerTest.main(null);
        PackReaderTest.main(null);
        CardGameTest.main(null);

        System.out.println("\n=== TEST RUN COMPLETE ===");
    }
}