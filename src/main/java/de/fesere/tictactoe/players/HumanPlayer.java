package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.ui.Console;

public class HumanPlayer implements Player {

  private final Mark mark;
  private final Console cli;

  public HumanPlayer(Mark mark, Console cli) {
    this.mark = mark;
    this.cli = cli;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  public HumanPlayer(Mark o) {
    this(o, new Console());
  }

  @Override
  public Board performMove(Board board) {
    Integer move = cli.requestMove();
    while(invalidMove(move,board)) {
      move = cli.requestMove();
    }
    return board.nextBoardFor(move, mark);
  }

  private boolean invalidMove(Integer move, Board board) {
    return move == null || !board.getPossibleMoves().contains(move);
  }
}
