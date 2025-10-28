
import java.io.File;
import java.util.*;

public class PackReader {
    public static List<Card> readPack(String filePath) {
        List<Card> cards = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            int lineNo = 0;
            while (scanner.hasNextLine()) {
                lineNo++;
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                int v;
                try {
                    v = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number on line " + lineNo + " in " + filePath);
                }

                if (v < 0) {
                    throw new IllegalArgumentException("Negative card value on line " + lineNo + " in " + filePath);
                }

                cards.add(new Card(v));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open and read the pack from " + filePath, e);
        }
        return cards;
    }
}

