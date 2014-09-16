package de.fesere.tictactoe;

public class RandomPlayer implements Player {

  private Mark mark;

  public RandomPlayer(Mark mark) {
    this.mark = mark;
  }
  @Override
  public Board performMove(Board board) {
    int move = board.getPossibleMoves().get(0);
    return board.nextBoardFor(move, mark);
  }
}
