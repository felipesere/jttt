package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidPlayerException;

import java.util.Comparator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.maxBy;

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

    if(newBoard.isFinished()) {
      return newBoard.getValue();
    }
    else {
      return -opponent.valueOfMove(newBoard, opponent.findOptimalMove(newBoard));
    }
  }

  public Board performMove(Board board) {
    int move = findOptimalMove(board);
    return board.nextBoardFor(move, mark);
  }

  private int findOptimalMove(Board board) {
    return movesOf(board).collect(maxBy(moveValueComparator(board))).get();
  }

  private Comparator<Integer> moveValueComparator(Board board) {
    return (first, second) -> Integer.compare(valueOfMove(board, first), valueOfMove(board, second));
  }

  private Stream<Integer> movesOf(Board board) {
    return board.getPossibleMoves().stream();
  }
}
