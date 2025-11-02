import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PackReaderTest {

    @Test
    void readsValidFile(@TempDir Path tempDir) throws Exception {
        File temp = tempDir.resolve("pack.txt").toFile();
        try (PrintWriter pw = new PrintWriter(temp)) {
            for (int i = 1; i <= 8; i++) {
                pw.println(i);
            }
        }

        List<Card> cards = PackReader.readPack(temp.getAbsolutePath());
        assertEquals(8, cards.size(), "Should read 8 cards from valid file");
    }

    @Test
    void throwsOnMissingFile() {
        assertThrows(RuntimeException.class, () ->
                PackReader.readPack("file_that_does_not_exist.txt"));
    }
}