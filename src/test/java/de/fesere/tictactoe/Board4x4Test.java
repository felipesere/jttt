package de.fesere.tictactoe;

import org.junit.Test;

import static de.fesere.tictactoe.PlayerMark.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class Board4x4Test {

  private Board board = new Board(4);

  @Test
  public void has16possibleMoves() {
    assertThat(board.getPossibleMoves(), hasSize(16));
  }

  @Test
  public void hasNoWinner() {
    assertThat(board.hasWinner(), is(false));
    assertThat(board.isFinished(), is(false));
  }

  @Test
  public void hasNoWinnerForOnlyThreeInTopRow() {
    Board newBoard = makeMoves(1,2,3);
    assertThat(newBoard.hasWinner(), is(false));
    assertThat(newBoard.isFinished(), is(false));
  }

  @Test
  public void hasWinnerInTopRow() {
    Board newBoard = makeMoves(1, 2, 3, 4);
    assertThat(newBoard.hasWinner(), is(true));
    assertThat(newBoard.isWinner(X), is(true));
    assertThat(newBoard.isFinished(), is(true));
  }

  @Test
  public void hasWinnerInFirstDiagonal() {
    Board newBoard = makeMoves(1, 6, 11, 16);
    assertThat(newBoard.hasWinner(), is(true));
    assertThat(newBoard.isWinner(X), is(true));
    assertThat(newBoard.isFinished(), is(true));
  }

  @Test
  public void hasWinnerInFirstColumn() {
    Board newBoard = makeMoves(1, 5, 9, 13);
    assertThat(newBoard.hasWinner(), is(true));
    assertThat(newBoard.isWinner(X), is(true));
    assertThat(newBoard.isFinished(), is(true));
  }
  private Board makeMoves(int ... moves) {
    Board result = board;
    for(int move : moves) {
      result = result.nextBoardFor(move, X);
    }
    return result;
  }
}
