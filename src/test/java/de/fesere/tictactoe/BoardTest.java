package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static de.fesere.tictactoe.Mark.X;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

public class BoardTest {

  private Board board;

  @Before
  public void before() {
    board = new Board();
  }

  @Test
  public void itStartsWithNinePossibleMoves() {
    assertThat(board.getPossibleMoves(), hasSize(9));
  }

  @Test
  public void itStartsWithNineEmptyMarks() {
    assertThat(marks(board).allMatch(Mark::isEmpty), is(true));
  }

  private Stream<Mark> marks(Board board) {
    return board.getMarks().values().stream();
  }

  @Test
  public void makingAMoveReducesTheNumberOfPossibleMoves() {
    List<Integer> moves = board.getPossibleMoves();
    Board newBoard = board.nextBoardFor(moves.get(0), X);
    assertThat(newBoard.getPossibleMoves(), hasSize(8));
  }

  @Test
  public void markingAMoveIsReflectedInMarks() {
    Board newBoard = board.nextBoardFor(1, Mark.O);
    assertThat(newBoard.getMarks().get(1), is(Mark.O));
    assertThat(marks(newBoard).filter(Mark::isEmpty).count(), is(8L));
  }

  @Test(expected = InvalidMoveException.class)
  public void sameMoveCanNotBeAppliedTwice() {
    board.nextBoardFor(0, X).nextBoardFor(0, X);
  }

  protected Board fromString(List<String> marks) {
    return new Board(convert(marks));
  }

  private List<Mark> convert(List<String> marks) {
    return marks.stream().map(this::convert).collect(toList());
  }

  public Mark convert(String mark) {
    if (mark.equals("")) {
      return Mark.EMPTY;
    } else {
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
    Board board = fromString(asList("O", "X", "X", "", "", "", "", "", ""));
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void aWinnerInTheFirstColumn() {
    Board board = fromString(asList("X", "", "", "X", "", "", "X", "", ""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedColumnHasNoWinner() {
    Board board = fromString(asList("O", "", "", "X", "", "", "X", "", ""));
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void aWinnerInFirstDiagonal() {
    Board board = fromString(asList("X", "", "", "", "X", "", "", "", "X"));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void mixedDiagonalHasNoWinner() {
    Board board = fromString(asList("O", "", "", "", "X", "", "", "", "X"));
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void aWinnerInSecondDiagonal() {
    Board board = fromString(asList("", "", "X", "", "X", "", "X", "", ""));
    assertThat(board.hasWinner(), is(true));
  }
}

