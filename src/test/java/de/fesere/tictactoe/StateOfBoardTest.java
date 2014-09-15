package de.fesere.tictactoe;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StateOfBoardTest {

  @Test
  public void doesNotHaveAWinner() {
    Board board = new Board(asList("", "X", "X","","","","","",""));
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void aWinnerIsThreeOftheSameKind() {
    Board board = new Board(asList("X", "X", "X","","","","","",""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedRowHasNoWinner() {
    Board board = new Board(asList("O", "X", "X","","","","","",""));
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void aWinnerInTheFirstColumn() {
    Board board = new Board(asList("X","","","X","","","X","",""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedColumnHasNoWinner() {
    Board board = new Board(asList("O","","","X","","","X","",""));
    assertThat(board.hasWinner(), is(false));
  }
  @Test
  public void aWinnerInFirstDiagonal() {
    Board board = new Board(asList("X","","","","X","","","","X"));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedDiagonalHasNoWinner() {
    Board board = new Board(asList("O","","","","X","","","","X"));
    assertThat(board.hasWinner(), is(false));
  }
  @Test
  public void aWinnerInSecondDiagonal() {
    Board board = new Board(asList("","","X","","X","","X","",""));
    assertThat(board.hasWinner(), is(true));
  }
}
