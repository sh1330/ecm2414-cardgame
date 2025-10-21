# Roadmap

- Setup Initial Structure:
    - Folders, initial classes, initial doc files.

- Card Class:
    - Stores card value.

- CardDeck Class:
    - Stores and draws cards.

- PackReader Class:
    - Read the pack.txt file, ensure its validity, and convert it to a list of Card Objects.

- Player Class:
    - Stores hand, must be able to draw/discard cards, win condition check.

- Game State Initialisation:
    - Done in main, before the game loop starts, must use the PackReader class, must create players, create all the CardDeck objects, and deal cards to players.

- Game loop:
    - Player draws card, Checks for win, Discards a card, next player, also add a turn limit or something similar to prevent infinite loops if no one wins.

- Add logging:
    - Records actions and final results, match wordings to the spec

- Add invalid input and error handling:
    - Check input file, game setup, game loop, users inputs

- Testing
    - Create tests that have good coverage, unit testing should also be done for each class, corner cases/extreme input testing. I reckon mostly black box dynamic testing is ideal for this project

### Concurrency
- Research what needs to be threaded, and what doesn't need to be, ensure it meets spec, then get to implementation.


