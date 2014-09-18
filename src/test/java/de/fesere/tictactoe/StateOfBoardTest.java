package de.fesere.tictactoe;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StateOfBoardTest {

  protected Board fromString(List<String> marks) {
    return new Board(convert(marks));
  }

  private List<Mark> convert(List<String> marks) {
    return marks.stream().map(this::convert).collect(Collectors.toList());
  }

  public Mark convert(String mark) {
    if(mark.equals("")) {
      return Mark.EMPTY;
    }
    else {
      return Mark.valueOf(mark);
    }
  }

  @Test
  public void doesNotHaveAWinner() {
    Board board = fromString(asList("", "X", "X", "", "", "", "", "", ""));
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void aWinnerIsThreeOftheSameKind() {
    Board board = fromString(asList("X", "X", "X", "", "", "", "", "", ""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedRowHasNoWinner() {
    Board board = fromString(asList("O", "X", "X","","","","","",""));
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void aWinnerInTheFirstColumn() {
    Board board = fromString(asList("X", "", "", "X", "", "", "X", "", ""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedColumnHasNoWinner() {
    Board board = fromString(asList("O","","","X","","","X","",""));
    assertThat(board.hasWinner(), is(false));
  }
  @Test
  public void aWinnerInFirstDiagonal() {
    Board board = fromString(asList("X","","","","X","","","","X"));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedDiagonalHasNoWinner() {
    Board board = fromString(asList("O","","","","X","","","","X"));
    assertThat(board.hasWinner(), is(false));
  }
  @Test
  public void aWinnerInSecondDiagonal() {
    Board board = fromString(asList("","","X","","X","","X","",""));
    assertThat(board.hasWinner(), is(true));
  }
}
