package de.fesere.tictactoe.players;

import de.fesere.tictactoe.*;
import de.fesere.tictactoe.ui.ConsoleInterface;

public class HumanPlayer implements Player {

  private final Mark mark;
  private final ConsoleInterface cli;

  public HumanPlayer(Mark mark, ConsoleInterface cli) {
    this.mark = mark;
    this.cli = cli;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  public HumanPlayer(Mark o) {
    this(o, new ConsoleInterface());
  }

  @Override
  public Board performMove(Board board) {
    Integer move = cli.requestMove();
    if(invalidMove(move, board)) {
      return performMove(board);
    }
    return board.nextBoardFor(move, mark);
  }

  private boolean invalidMove(Integer move, Board board) {
    return move == null || !board.getPossibleMoves().contains(move);
  }
}
