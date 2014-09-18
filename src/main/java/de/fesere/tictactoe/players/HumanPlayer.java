package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.Player;

public class HumanPlayer implements Player {

  private final Mark mark;
  private final int move;

  public HumanPlayer(Mark mark, int move) {
    this.mark = mark;
    this.move = move;
  }

  @Override
  public Board performMove(Board board) {
    return board.nextBoardFor(move, mark);
  }
}
