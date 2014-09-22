package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.ConsoleInterface;

public class Main {
  public static void main(String [] args) {
    ConsoleInterface consoleInterface = new ConsoleInterface();

    Player [] players = consoleInterface.requestPlayers();

    boolean play = true;

    while(play) {
      Game game = new Game(consoleInterface, players);
      game.play();
      if(game.hasWinner()) {
        consoleInterface.announceWinner(game.getWinner());
      } else {
        consoleInterface.announceDraw();
      }

      play = consoleInterface.requestRematch();
    }
  }
}
