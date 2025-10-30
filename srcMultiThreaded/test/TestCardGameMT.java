import java.io.File;
import java.io.PrintWriter;


public class TestCardGameMT {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Running TestCardGameMT (multi-threaded) ===");

        int numPlayers = 3;

        // Create a temporary pack file 
        // It must have exactly 8 * numPlayers integers
        File tempPack = File.createTempFile("pack", ".txt");
        try (PrintWriter pw = new PrintWriter(tempPack)) {
            for (int i = 0; i < 8 * numPlayers; i++) {
                pw.println((i % 4) + 1); // cyclical pattern 1–4
            }
        }

        //start the game 
        CardGame g = new CardGame(numPlayers, tempPack.getAbsolutePath());
        g.showInitialState();

        // Measure run time for safety
        long start = System.currentTimeMillis();
        g.playPhase();
        long elapsed = System.currentTimeMillis() - start;

        //  Validate end results 
        if (g.getWinnerId() <= 0) {
            System.out.println("FAIL: No winner detected");
            return;
        }

        if (!g.isGameOver()) {
            System.out.println("FAIL: Game over flag not set");
            return;
        }

        if (elapsed > 8000) {
            System.out.println("WARNING: Game took unusually long (" + elapsed + " ms)");
        }

        System.out.println("PASS: Game ended cleanly with winner " + g.getWinnerId());
        System.out.println("Check player*_output.txt logs for per-thread details.");
    }
}