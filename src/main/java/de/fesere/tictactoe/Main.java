package de.fesere.tictactoe;

import de.fesere.tictactoe.players.PlayerFactory;
import de.fesere.tictactoe.ui.Console;

public class Main {
  public static void main(String [] args) {
    Console console = new Console();
    int choice = console.requestPlayers();
    Player [] players = PlayerFactory.getPlayers(choice);
    choice = console.requestBoardSize(new int []{3,4});
    new GameRunner().run(console,new Board(choice), players);
  }
}
