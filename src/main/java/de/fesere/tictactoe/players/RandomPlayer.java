package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.Player;

public class RandomPlayer implements Player {

  private Mark mark;

  public RandomPlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  @Override
  public Board performMove(Board board) {
    int move = board.getPossibleMoves().get(0);
    return board.nextBoardFor(move, mark);
  }
}
