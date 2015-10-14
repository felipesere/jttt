package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static de.fesere.tictactoe.PlayerMark.*;
import static java.util.Arrays.asList;
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
  public void itStartsWithSixteenPossibleMoves() {
    assertThat(board.getPossibleMoves(), hasSize(16));
  }

  @Test
  public void itStartsWithSixteenEmptyMarks() {
    assertThat(marks(board).allMatch(PlayerMark::isEmpty), is(true));
  }

  @Test
  public void makingAMoveReducesTheNumberOfPossibleMoves() {
    List<Integer> moves = board.getPossibleMoves();
    Board newBoard = board.nextBoardFor(moves.get(0), X);
    assertThat(newBoard.getPossibleMoves(), hasSize(15));
  }

  @Test
  public void markingAMoveIsReflectedInMarks() {
    Board newBoard = board.nextBoardFor(1, O);
    assertThat(newBoard.getMarks().get(1), is(O));
    assertThat(marks(newBoard).filter(PlayerMark::isEmpty).count(), is(15L));
  }

  @Test(expected = InvalidMoveException.class)
  public void sameMoveCanNotBeAppliedTwice() {
    board.nextBoardFor(0, X).nextBoardFor(0, X);
  }

  @Test
  public void doesNotHaveAWinner() {
    Board board = new Board(asList(EMPTY,     X,     X, EMPTY,
                                   EMPTY, EMPTY, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY, EMPTY));
    noWinner(board);
  }


  @Test
  public void aWinnerIsFourOftheSameKind() {
    Board board = new Board(asList(X,     X,     X, X,
                                   EMPTY, EMPTY, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY, EMPTY));
    hasWinner(board);
  }

  @Test
  public void mixedRowHasNoWinner() {
    Board board = new Board(asList(O,     X,     X, X,
            EMPTY, EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY));
    noWinner(board);
  }

  @Test
  public void aWinnerInTheFirstColumn() {
    Board board = new Board(asList(X, EMPTY, EMPTY , EMPTY,
            X, EMPTY, EMPTY, EMPTY,
            X, EMPTY, EMPTY, EMPTY,
                                   X, EMPTY, EMPTY, EMPTY));
    hasWinner(board);
  }

  @Test
  public void mixedColumnHasNoWinner() {
    Board board = new Board(asList(O, EMPTY, EMPTY , EMPTY,
            X, EMPTY, EMPTY, EMPTY,
            X, EMPTY, EMPTY, EMPTY,
            X, EMPTY, EMPTY, EMPTY));
    noWinner(board);
  }

  @Test
  public void aWinnerInFirstDiagonal() {
    Board board = new Board(asList(X, EMPTY, EMPTY , EMPTY,
            EMPTY, X, EMPTY, EMPTY,
            EMPTY, EMPTY, X, EMPTY,
            EMPTY, EMPTY, EMPTY, X));
    hasWinner(board);
  }

  @Test
  public void mixedDiagonalHasNoWinner() {
    Board board = new Board(asList(O,     EMPTY, EMPTY, EMPTY,
                                   EMPTY, X,     EMPTY, EMPTY,
            EMPTY, EMPTY, X, EMPTY,
            EMPTY, EMPTY, EMPTY, X));
    noWinner(board);
  }

  @Test
  public void aWinnerInSecondDiagonal() {
    Board board = new Board(asList(EMPTY, EMPTY, EMPTY, X,
                                   EMPTY, EMPTY, X, EMPTY,
            EMPTY, X,     EMPTY, EMPTY,
    X,     EMPTY, EMPTY, EMPTY));
    hasWinner(board);
  }

  private void hasWinner(Board board) {
    assertThat(board.hasWinner(), is(true));
  }

  private void noWinner(Board board) {
    assertThat(board.hasWinner(), is(false));
  }

  private Stream<PlayerMark> marks(Board board) {
    return board.getMarks().values().stream();
  }
}
