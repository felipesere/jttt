package de.fesere.tictactoe;

import de.fesere.tictactoe.players.PlayerFactory;
import de.fesere.tictactoe.ui.Console;

public class Main {
  public static void main(String [] args) {
    Console console = new Console();
    int choice = console.requestPlayers();
    Player [] players = PlayerFactory.getPlayers(choice);

    new GameRunner().run(console, players);
  }
}
