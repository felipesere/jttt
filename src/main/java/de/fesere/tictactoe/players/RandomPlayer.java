package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.PlayerMark;
import de.fesere.tictactoe.Player;

import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {

  private final PlayerMark mark;
  private final Random random = new Random();


  public RandomPlayer(PlayerMark mark) {
    this.mark = mark;
  }

  @Override
  public PlayerMark getMark() {
    return mark;
  }

  @Override
  public Board performMove(Board board) {
    int move = getRandomMove(board);
    return board.nextBoardFor(move, mark);
  }

  private int getRandomMove(Board board) {
    List<Integer> moves = board.getPossibleMoves();
    return moves.get(random.nextInt(moves.size()));
  }
}
