package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.ConsoleInterface;

import java.util.HashMap;
import java.util.Map;

import static de.fesere.tictactoe.Mark.X;

public class Game {

  private Map<Mark, Player> players = new HashMap<>();
  ConsoleInterface console = new ConsoleInterface();

  public Game(Player... players) {
    for(Player player : players) {
      this.players.put(player.getMark(), player);
    }
  }

  public void play() {
    Board board = new Board();
    Player current = players.get(X);
    while (!board.isFinished()) {
      console.displayBoard(board);
      board = current.performMove(board);
      current = players.get(current.getMark().opponent());
    }
  }

  public boolean hasWinner() {
    return true;
  }

  public Mark getWinner() {
    return Mark.O;
  }
}
