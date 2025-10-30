import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {

    private final int numPlayers;
    private final List<Player> players;
    private final List<CardDeck> decks;
    private final List<Card> pack;
    private volatile boolean gameOver = false;
    private volatile int winnerId = -1;

    public CardGame(int numPlayers, String packFilePath) {
        this.numPlayers = numPlayers;
        this.players = new ArrayList<>();
        this.decks = new ArrayList<>();
        this.pack = PackReader.readPack(packFilePath);

        validatePack();
        initializePlayersAndDecks();
        dealCards();
    }

    private void validatePack() {
        int expectedCards = 8 * numPlayers;
        if (pack.size() != expectedCards) {
            throw new IllegalArgumentException(
                "Invalid pack: expected " + expectedCards + " cards, got " + pack.size());
        }
    }

    private void initializePlayersAndDecks() {
        for (int i = 1; i <= numPlayers; i++) decks.add(new CardDeck(i));
        for (int i = 1; i <= numPlayers; i++) {
            CardDeck left = decks.get(i - 1);
            CardDeck right = decks.get(i % numPlayers);
            players.add(new Player(i, left, right, this));
        }
    }

    private void dealCards() {
        int index = 0;
        // 4 to each player
        for (int round = 0; round < 4; round++)
            for (Player p : players)
                p.addCard(pack.get(index++));
        // 4 to each deck
        for (int round = 0; round < 4; round++)
            for (CardDeck d : decks)
                d.addCard(pack.get(index++));
    }

    public void showInitialState() {
        System.out.println("=== Initial Game State ===");
        for (Player p : players)
            System.out.println("Player " + p.getId() + " hand: " + p.getHand());
        for (CardDeck d : decks)
            System.out.println("Deck " + d.getId() + " contents: " + d.getContents());
    }

    /** Called by players when they have a winning hand */
    public synchronized void declareWinner(int id) {
        if (!gameOver) {
            gameOver = true;
            winnerId = id;
            System.out.println(">>> Player " + id + " wins the game! <<<");
            // notify others they can finish
            for (Player p : players) p.stopRunning();
        }
    }

    public boolean isGameOver() { return gameOver; }

    public int getWinnerId() { return winnerId; }

    /** Starts and joins all player threads */
    public void playPhase() {
        List<Thread> threads = new ArrayList<>();
        for (Player p : players) {
            Thread t = new Thread(p, "Player-" + p.getId());
            threads.add(t);
            t.start();
        }

        // Wait until winner declared
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ignored) {}
        }

        System.out.println("Game over. Player " + winnerId + " has won.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter number of players:");
            int n = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Enter pack file path:");
            String path = scanner.nextLine().trim();

            CardGame g = new CardGame(n, path);
            g.showInitialState();

            boolean instantWin = g.players.stream().anyMatch(Player::hasWinningHand);
            if (instantWin) {
                g.players.stream()
                        .filter(Player::hasWinningHand)
                        .findFirst()
                        .ifPresent(p -> g.declareWinner(p.getId()));
            } else {
                g.playPhase();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}