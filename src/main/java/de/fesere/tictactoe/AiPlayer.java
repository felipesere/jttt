package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidPlayerException;

public class AiPlayer {
  private Mark mark;

  public AiPlayer(Mark mark) {
    this.mark = mark;
    if(mark.isEmpty()) {
      throw new InvalidPlayerException();
    }
    this.mark = mark;
  }

  public int valueOfMove(Board board, int move) {
    Board newBoard = board.nextBoardFor(move, mark);

    if(newBoard.hasWinner()) {
      return 10;
    }
    return 0;
  }
}
