package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.ConsoleInterface;

import java.util.HashMap;
import java.util.Map;

import static de.fesere.tictactoe.Mark.X;

public class Game {

  private Map<Mark, Player> players = new HashMap<>();
  ConsoleInterface console = new ConsoleInterface();
  Board board = new Board();

  public Game(Player... players) {
    for(Player player : players) {
      this.players.put(player.getMark(), player);
    }
  }

  public void play() {
    Player currentPlayer = players.get(X);
    while (!board.isFinished()) {
      playTurn(currentPlayer);
      currentPlayer = getOpponentOf(currentPlayer);
    }
    showFinalBoard();
  }

  private Player getOpponentOf(Player current) {
    return players.get(current.getMark().opponent());
  }

  private void showFinalBoard() {
    console.displayBoard(board);
  }

  private void playTurn(Player player) {
    console.displayBoard(board);
    board = player.performMove(board);
  }

  public boolean hasWinner() {
    return board.hasWinner();
  }

  public Mark getWinner() {
    return board.getWinner();
  }
}
