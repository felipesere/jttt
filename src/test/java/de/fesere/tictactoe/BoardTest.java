package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static de.fesere.tictactoe.Mark.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class BoardTest {

  private Board board;

  @Before
  public void before() {
    board = new Board();
  }
  @Test
  public void itStartsWithNinePossibleMoves(){
    assertThat(board.getPossibleMoves(), hasSize(9));
  }

  @Test
  public void makingAMoveReducesTheNumberOfPossibleMoves(){
    List<Integer> moves = board.getPossibleMoves();
    Board newBoard = board.nextBoardFor(moves.get(0), X);
    assertThat(newBoard.getPossibleMoves(), hasSize(8));
  }

  @Test(expected = InvalidMoveException.class)
  public void sameMoveCanNotBeAppliedTwice() {
    board.nextBoardFor(0, X).nextBoardFor(0, X);
  }
}