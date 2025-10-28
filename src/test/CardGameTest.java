import java.io.*;
import java.util.*;

public class CardGameTest {

    public static void main(String[] args) {
        int passed = 0, failed = 0;

        try {
            // 2 players → 8 * 2 = 16 cards
            File temp = File.createTempFile("pack", ".txt");
            try (PrintWriter pw = new PrintWriter(temp)) {
                for (int i = 1; i <= 16; i++) pw.println(i);
            }

            CardGame game = new CardGame(2, temp.getAbsolutePath());
            if (game != null) passed++; else failed++;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            failed++;
        }

        System.out.println("CardGameTest: " + passed + " passed, " + failed + " failed");
    }
}