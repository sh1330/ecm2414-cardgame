import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.PrintWriter;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TestCardGameMT {

    @TempDir
    Path tempDir;

    @Test
    void gameRunsToCompletion_under8sTimeout() throws Exception {
        int numPlayers = 3;

        Path packPath = tempDir.resolve("pack.txt");
        try (PrintWriter pw = new PrintWriter(packPath.toFile())) {
            for (int i = 0; i < 8 * numPlayers; i++) {
                pw.println((i % 4) + 1);
            }
        }

        CardGame g = new CardGame(numPlayers, packPath.toString());
        long start = System.currentTimeMillis();
        g.playPhase();
        long elapsed = System.currentTimeMillis() - start;

        // Optional assertions if your API supports them:
        // assertNotNull(g.getWinner()); or assertTrue(g.getWinnerId() > 0);
        // assertTrue(g.isFinished()); or assertTrue(g.isGameOver());

        assertTrue(elapsed <= 8000, "Game should finish within 8s (was " + elapsed + " ms)");
    }
}