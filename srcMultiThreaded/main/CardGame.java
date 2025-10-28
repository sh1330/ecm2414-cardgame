import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {

    private int numPlayers;
    private List<Player> players;
    private List<CardDeck> decks;
    private List<Card> pack;

    public CardGame(int numPlayers, String packFilePath) {
        this.numPlayers = numPlayers;
        this.players = new ArrayList<>();
        this.decks = new ArrayList<>();
        this.pack = PackReader.readPack(packFilePath);

        validatePack();
        initializePlayersAndDecks();
        dealCards();
    }

    // Ensure pack has the correct size and valid values according to n (8n cards).
     
    private void validatePack() {
        int expectedCards = 8 * numPlayers;
        if (pack.size() != expectedCards) {
            throw new IllegalArgumentException(
                    "Invalid pack. expected " + expectedCards + " cards, but got " + pack.size());
        }
    }

    //Create n players and n decks with appropriate IDs.
     
    private void initializePlayersAndDecks() {
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player(i));
            decks.add(new CardDeck(i));
        }
    }

    // Deal cards in a round-robin fashion to players first, then decks.
     
    private void dealCards() {
        int index = 0;

        // Deal 4 cards to each player
        for (int round = 0; round < 4; round++) {
            for (Player p : players) {
                p.addCard(pack.get(index++));
            }
        }

        // Deal 4 cards to each deck
        for (int round = 0; round < 4; round++) {
            for (CardDeck d : decks) {
                d.addCard(pack.get(index++));
            }
        }
    }

    //Display the current game state for debugging.
     
    public void showInitialState() {
        System.out.println("=== Initial Game State ===");
        for (Player p : players) {
            System.out.println("Player " + p.getId() + " hand: " + p.getHand());
        }
        for (CardDeck d : decks) {
            System.out.println("Deck " + d.getId() + " contents: " + d.getContents());
        }
    }

    //Check if any player has a winning hand immediately.
     
    public boolean checkInitialWinner() {
        for (Player p : players) {
            if (p.hasWinningHand()) {
                System.out.println("Player " + p.getId() + " wins");
                return true;
            }
        }
        return false;
    }

    private CardDeck leftDeck(int playerId) {
      return decks.get(playerId - 1);
    }

    private CardDeck rightDeck(int playerId) {
      int n = decks.size();
      return decks.get(playerId % n);
    }


    private void printAdjacencyMap() {
        System.out.println("Adjacency map (Player -> LeftDeck, RightDeck)");
        for (Player p : players) {
            int i = p.getId();
            System.out.printf("Player %d -> L:%d  R:%d%n",
                i, leftDeck(i).getId(), rightDeck(i).getId());
        }
    }

    private boolean takeTurn(Player p) {
      // check if win
      if (p.hasWinningHand()) return true;

      CardDeck left = leftDeck(p.getId());
      CardDeck right = rightDeck(p.getId());

      //discard right, draw left
      Card discarded = p.chooseDiscard();
      right.addCard(discarded);

      Card drawn = left.drawCard();
      if (drawn != null) p.addCard(drawn);

      return p.hasWinningHand();
    }

    // loops through until someone wins
    public int playPhase() {
      while (true) {
        for (Player p : players) {
          if (takeTurn(p)) {
            System.out.println("Player" + p.getId() + " wins");
            return p.getId();
          }
        }
      }
    }



    //Main entry point for the program.
     
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int numPlayers = 0;
            String packFilePath = "";

            while (numPlayers <= 0) {
                System.out.println("Enter number of players (must be positive):");
                String input = scanner.nextLine().trim();
                try {
                    numPlayers = Integer.parseInt(input);
                    if (numPlayers <= 0) {
                        System.out.println("Number must be positive.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }

            boolean validPack = false;
            while (!validPack) {
                System.out.println("Enter pack file path:");
                packFilePath = scanner.nextLine().trim();
                File f = new File(packFilePath);
                if (!f.exists()) {
                    System.out.println("File not found. Try again.");
                    continue;
                }

            // Temporary validation read
                List<Card> tempPack = PackReader.readPack(packFilePath);
                if (tempPack.size() != 8 * numPlayers) {
                    System.out.println("Invalid pack size. Expected " + (8 * numPlayers) + " cards.");
                    continue;
                }
                validPack = true;
            }

            CardGame game = new CardGame(numPlayers, packFilePath);
            game.showInitialState();

            boolean hasWinner = game.checkInitialWinner();
            if (!hasWinner) {
                System.out.println("No initial winner. Game setup complete, ready for play phase.");
            }

        } catch (Exception e) {
            System.err.println("Error initializing game: " + e.getMessage());
        } finally {
            scanner.close();
        }
    } 

}  