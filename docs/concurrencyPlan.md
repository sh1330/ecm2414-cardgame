# Concurrency Plan

### Checklist - Spec Requirements
    -Multithreaded card game, at least thread-safe Card and thread-safe Player, maybe also a thread-safe CardDeck
    -Decks are thread-safe FIFO, players draw from left, discard to right; draw+discard should be one atomic action, when the game ends every player should still have 4 cards, each deck should also written to a txt file at the end of game, each turn must also be logged, each player has there own txt (playerX_output.txt)
    -Start threads after dealing, so the game must be setup fully first then start the player threads.

### Implementation Plan:

#### CardGame
    - CardGame.java role will change to a single-threaded coordinator and thread launcher, methods such as takeTurn must be moved to Player so that concurrency can be implemented 
    -This class will deal with input, validation and deal cards, create deck and player instances, start the threads and write each deck to the correct output files once the game is complete (playerX_output.txt).

#### Player:
    - make player a thread task: implement Runnable
    - Each Player must run its own loop: draw from left deck -> choose discard -> dicsard to right deck -> log -> check win -> repeat until winner
    - Each Player must:
        -have its own ID,
        -A reference to its left deck, and right deck
        -A shared gameOver flag and winnerID, so first winner stops everyone else
        -Own and manage its hand
        -write turns and final hand to playerX_output.txt
        -check initial winning hand
        -play concurrently until someone wins the game
        -check for win after each turn
        -exit once another player wins

#### Card:
    -Keep it immutable
    -No setters
    -Must be thread safe
    


