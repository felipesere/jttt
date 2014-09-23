package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.ConsoleInterface;

public class GameRunner {

  public void run(ConsoleInterface consoleInterface, Player[] players) {
    boolean play = true;

    while(play) {
      Game game = new Game(consoleInterface, players);
      game.play();
      play = consoleInterface.requestRematch();
    }
  }
}
