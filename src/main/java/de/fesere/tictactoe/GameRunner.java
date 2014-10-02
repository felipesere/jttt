package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.Console;

public class GameRunner {

  public void run(Console console, Player[] players) {
    boolean play = true;

    while(play) {
      Game game = new Game(console, players);
      game.play();
      play = console.requestRematch();
    }
  }
}
