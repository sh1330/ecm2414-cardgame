import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PackReader {
    public static List<Card> readPack(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Pack file path is empty.");
        }

        List<Card> cards = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(path);
             Scanner scanner = new Scanner(inputStream)) {

            int lineNo = 0;
            while (scanner.hasNextLine()) {
                lineNo++;
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                int v;
                try {
                    v = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number on line " + lineNo + " in " + path);
                }

                if (v < 0) {
                    throw new IllegalArgumentException("Negative card value on line " + lineNo + " in " + path);
                }

                cards.add(new Card(v));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Pack file not found: " + path, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to open and read the pack from " + path, e);
        }
        return cards;
    }
}