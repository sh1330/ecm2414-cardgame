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
        // create a valid pack with 8*numplayers
        Path packPath = tempDir.resolve("pack.txt");
        try (PrintWriter pw = new PrintWriter(packPath.toFile())) {
            for (int i = 0; i < 8 * numPlayers; i++) {
                pw.println((i % 4) + 1);
            }
        }

        CardGame g = new CardGame(numPlayers, packPath.toString()); //run the play phase and measure time
        long start = System.currentTimeMillis();
        g.playPhase();
        long elapsed = System.currentTimeMillis() - start;

            // game completes in reasonable time bound 
        assertTrue(elapsed <= 8000, "Game should finish within 8s (was " + elapsed + " ms)");
    }
}