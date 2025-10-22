
import java.io.File;
import java.util.*;

public class PackReader {
    public static List<Card> readPack(String filePath) {
        List<Card> cards = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
              String line = scanner.nextLine().trim();
              if (line.isEmpty()) continue;
              int v = Integer.parseInt(line);
              cards.add(new Card(v));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open and read the pack from " + filePath, e);
        }
        return cards;
    }
}

