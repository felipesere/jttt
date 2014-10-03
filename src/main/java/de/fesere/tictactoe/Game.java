package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.Console;

import java.util.HashMap;
import java.util.Map;

import static de.fesere.tictactoe.PlayerMark.X;

public class Game {

  private final Map<PlayerMark, Player> players;
  private final Console console;
  private Board board = new Board(4);

  public Game(Console console,Board board, Player...players) {
    this.console = console;
    this.board = board;
    this.players = convertToMap(players);
  }
  public void play() {
    playAllTurns();
    showFinalBoard();
    announceResult();
  }

  private void playAllTurns() {
    Player currentPlayer = players.get(X);
    while (!board.isFinished()) {
      playTurn(currentPlayer);
      currentPlayer = getOpponentOf(currentPlayer);
    }
  }

  private void announceResult() {
    if(hasWinner()) {
      console.announceWinner(getWinner());
    } else {
      console.announceDraw();
    }
  }

  private boolean hasWinner() {
    return board.hasWinner();
  }

  private PlayerMark getWinner() {
    return board.getWinner();
  }

  private Map<PlayerMark, Player> convertToMap(Player[] players) {
    Map<PlayerMark, Player> result = new HashMap<>();
    for(Player player : players) {
      result.put(player.getMark(), player);
    }
    return result;
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
}
