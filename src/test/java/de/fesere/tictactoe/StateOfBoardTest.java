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
  public void aWinnerInTheSecondRow() {
    Board board = new Board(asList("","","","X","X", "X","","",""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void aWinnerInTheThirdRow() {
    Board board = new Board(asList("","","","","","","X","X","X"));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void aWinnerInTheFirstColumn() {
    Board board = new Board(asList("X","","","X","","","X","",""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void aWinnerInFirstDiagonal() {
    Board board = new Board(asList("X","","","","X","","","","X"));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void aWinnerInSecondDiagonal() {
    Board board = new Board(asList("","","X","","X","","X","",""));
    assertThat(board.hasWinner(), is(true));
  }
}
