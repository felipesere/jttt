package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidPlayerException;
import org.junit.Before;
import org.junit.Test;

import static de.fesere.tictactoe.Mark.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class AiPlayerTest {

  private AiPlayer player;

  @Before
  public void before() {
    player = new AiPlayer(X);
  }

  @Test(expected = InvalidPlayerException.class)
  public void canNotBeConstructedWithEmptyMark(){
    new AiPlayer(EMPTY);
  }

  @Test
  public void valueOfWinningMoveIsPositive() {
    Board board = new Board(asList(X, X, EMPTY,
                                   O, O, X,
                                   O, X, O));

    assertThat(player.valueOfMove(board, 2), is(greaterThan(0)));
  }

  @Test
  public void valueOfDrawIsZero() {
    Board board = new Board(asList(X, O, EMPTY,
                                   O, X, X,
                                   O, X, O));

    assertThat(player.valueOfMove(board, 2), is(0));
  }
}