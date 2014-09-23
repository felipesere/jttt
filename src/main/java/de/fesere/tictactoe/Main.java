package de.fesere.tictactoe;

import de.fesere.tictactoe.players.PlayerFactory;
import de.fesere.tictactoe.ui.ConsoleInterface;

public class Main {
  public static void main(String [] args) {
    ConsoleInterface consoleInterface = new ConsoleInterface();
    int choice = consoleInterface.requestPlayers();
    Player [] players = PlayerFactory.getPlayers(choice);

    new GameRunner().run(consoleInterface, players);
  }
}
