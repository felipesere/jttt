package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.ConsoleInterface;

public class Main {
  public static void main(String [] args) {
    ConsoleInterface consoleInterface = new ConsoleInterface();
    Player [] players = consoleInterface.requestPlayers();

    new GameRunner().run(consoleInterface, players);
  }
}
