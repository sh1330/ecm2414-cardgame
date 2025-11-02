import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CardGameTest {

    @Test
    void constructsWithValidPack(@TempDir Path tempDir) throws Exception {
        // 2 players → 8 * 2 = 16 cards
        File temp = tempDir.resolve("pack.txt").toFile();
        try (PrintWriter pw = new PrintWriter(temp)) {
            for (int i = 1; i <= 16; i++) {
                pw.println(i);
            }
        }

        CardGame game = new CardGame(2, temp.getAbsolutePath());
        assertNotNull(game, "CardGame should be constructed with valid inputs");
    }
}