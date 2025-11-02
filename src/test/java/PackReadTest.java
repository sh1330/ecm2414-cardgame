import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PackReadTest {

    @Test
    void readsAndContainsExpectedValues(@TempDir Path tempDir) throws Exception {
        File temp = tempDir.resolve("pack.txt").toFile();
        try (PrintWriter pw = new PrintWriter(temp)) {
            pw.println(3);
            pw.println(7);
            pw.println(11);
        }

        List<Card> cards = PackReader.readPack(temp.getAbsolutePath());
        assertEquals(3, cards.size());
        assertEquals(3, cards.get(0).getValue());
        assertEquals(7, cards.get(1).getValue());
        assertEquals(11, cards.get(2).getValue());
    }
}