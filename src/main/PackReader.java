
import java.io.File;
import java.util.*;

public class PackReader {
    public static List<Card> readPack(String filePath) {
        List<Card> cards = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                int v = Integer.parseInt(scanner.nextLine().trim());
                cards.add(new Card(v));
            }
            scanner.close();
        } catch (Exception ignored) {}
        return cards;
    }
}

