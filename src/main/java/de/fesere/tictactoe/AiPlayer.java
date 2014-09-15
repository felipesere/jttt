package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidPlayerException;

import java.util.stream.Stream;

public class AiPlayer {
  private Mark mark;
  private AiPlayer opponent;

  public static AiPlayer createAi(Mark mark) {
    if(mark.isEmpty()) {
      throw new InvalidPlayerException();
    }
    AiPlayer self = new AiPlayer(mark);
    AiPlayer opponent = new AiPlayer(mark.opponent());
    self.opponent = opponent;
    opponent.opponent = self;
    return self;
  }

  private AiPlayer(Mark mark) {
    this.mark = mark;
  }

  public int valueOfMove(Board board, int move) {
    Board newBoard = board.nextBoardFor(move, mark);

    if(newBoard.hasWinner()) {
      return 1 + newBoard.getPossibleMoves().size();
    }
    else if(newBoard.hasDraw()) {
      return 0;
    }
    else {
      return -opponent.valueOfMove(newBoard, findOptimalMove(newBoard));
    }
  }

  public Board performMove(Board board) {
    int move = findOptimalMove(board);
    return board.nextBoardFor(move, mark);
  }

  private int findOptimalMove(Board board) {
   return firstOf(moves(board).sorted((first, second) -> Integer.compare(valueOfMove(board, first), valueOfMove(board, second))));
  }

  private Stream<Integer> moves(Board board) {
    return board.getPossibleMoves().stream();
  }

  private int firstOf(Stream<Integer> stream) {
    return stream.findFirst().get();
  }

}
