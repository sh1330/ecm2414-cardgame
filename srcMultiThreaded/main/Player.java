import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Player implements Runnable {

    private final int id;
    private final ArrayList<Card> hand;
    private final CardDeck leftDeck;
    private final CardDeck rightDeck;
    private volatile boolean running = true; // to stop gracefully
    private final CardGame gameRef; // used to signal winner and check gameOver
    private final String logFile;

    public Player(int id, CardDeck leftDeck, CardDeck rightDeck, CardGame gameRef) {
        this.id = id;
        this.hand = new ArrayList<>();
        this.leftDeck = leftDeck;
        this.rightDeck = rightDeck;
        this.gameRef = gameRef;
        this.logFile = "player" + id + "_output.txt";
    }

    public int getId() {
        return id;
    }

    public synchronized void addCard(Card c) {
        hand.add(c);
    }

    // Player’s logic loop when running as a thread
    @Override
    public void run() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(logFile))) {
            pw.println("Player " + id + " initial hand: " + getHand());
            while (!gameRef.isGameOver() && running) {

                if (hasWinningHand()) {
                    gameRef.declareWinner(id);
                    break;
                }

                // draw left
                Card drawn = leftDeck.drawCard();
                if (drawn != null) {
                    synchronized (hand) {
                        hand.add(drawn);
                    }
                }

                // discard one to the right deck
                Card discard = chooseDiscard();
                if (discard != null) {
                    rightDeck.addCard(discard);
                    pw.println("Player " + id + " discards " + discard.getValue() + " to deck " + rightDeck.getId());
                }

                pw.println("Player " + id + " current hand: " + getHand());
                Thread.sleep(10); // brief delay to prevent busy‑looping
            }

            // End of thread actions
            if (gameRef.getWinnerId() == id) {
                pw.println("Player " + id + " wins");
            } else {
                pw.println("Player " + id + " has been told that player " + gameRef.getWinnerId() + " has won");
            }

            pw.println("Player " + id + " final hand: " + getHand());

        } catch (IOException | InterruptedException e) {
            System.err.println("Player " + id + " error: " + e.getMessage());
        }
    }

    // Tell this player to stop looping 
    public void stopRunning() {
        running = false;
    }

    public synchronized boolean hasWinningHand() {
        if (hand.size() != 4) return false;
        int v = hand.get(0).getValue();
        for (Card c : hand) {
            if (c.getValue() != v)
                return false;
        }
        return true;
    }

    public synchronized String getHand() {
        StringBuilder sb = new StringBuilder();
        for (Card c : hand) sb.append(c.getValue()).append(' ');
        return sb.toString().trim();
    }

    // Simple discard strategy per spec 
    public synchronized Card chooseDiscard() {
        int target = this.id;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getValue() != target) {
                return hand.remove(i);
            }
        }
        // if all match ID then remove first card
        return hand.isEmpty() ? null : hand.remove(0);
    }
}