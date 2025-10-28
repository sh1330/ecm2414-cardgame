import java.io.*;
import java.util.*;

public class PackReaderTest {

    public static void main(String[] args) {
        int passed = 0, failed = 0;

        try {
            // valid temporary file
            File temp = File.createTempFile("pack", ".txt");
            try (PrintWriter pw = new PrintWriter(temp)) {
                for (int i = 1; i <= 8; i++) pw.println(i);
            }

            List<Card> cards = PackReader.readPack(temp.getAbsolutePath());
            if (cards.size() == 8) passed++; else failed++;
        } catch (Exception e) {
            System.out.println("Unexpected error in valid test: " + e);
            failed++;
        }

        // invalid file test
        boolean caught = false;
        try {
            PackReader.readPack("file_that_does_not_exist.txt");
        } catch (RuntimeException e) {
            caught = true;
        }
        if (caught) passed++; else failed++;

        System.out.println("PackReaderTest: " + passed + " passed, " + failed + " failed");
    }
}