package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Player;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static de.fesere.tictactoe.Mark.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AiPlayerTest extends PlayerCommonTest {

  private Player player;

  @Before
  public void before() {
    player =  playerForCommonTests();
  }

  @Override
  Player playerForCommonTests() {
    return AiPlayer.createAi(X);
  }

  @Test
  public void makesDirectWinningMove(){
    Board board = new Board(asList(X, X, EMPTY,
                                   O, O, X,
                                   O, X, O));
    Board result = player.performMove(board);
    assertThat(result.hasWinner(), is(true));
  }

  @Test
  public void forcesOpponentsHandVersion1(){
    Board board = new Board(asList(O,     EMPTY, EMPTY,
                                   EMPTY, O,     EMPTY,
                                   EMPTY, EMPTY, X));
    Board result = player.performMove(board);
    assertThat(result.getPossibleMoves(), madeOneOfTheseMoves(3, 7));
  }

  @Test
  public void forcesOpponentsHandVersion2(){
    Board board = new Board(asList(O,     EMPTY, EMPTY,
                                   EMPTY, X,     EMPTY,
                                   EMPTY, EMPTY, O));
    Board result = player.performMove(board);
    assertThat(result.getPossibleMoves(), madeOneOfTheseMoves(2, 4, 6, 8));
  }

  @Test
  public void forcesOponentsHandVersion3(){
    Board board = new Board(asList(EMPTY,     O, EMPTY,
                                   O,         X, EMPTY,
                                   EMPTY, EMPTY, EMPTY));
    Board result = player.performMove(board);
    assertThat(result.getPossibleMoves(), madeOneOfTheseMoves(1, 3, 7, 8));
  }

  private Matcher<List<Integer>> madeOneOfTheseMoves(int... numbers) {
    return new TypeSafeMatcher<List<Integer>>() {
      @Override
      protected boolean matchesSafely(List<Integer> item) {
        for(int number : numbers) {
          if(!item.contains(number)) {
            return true;
          }
        }
        return false;
      }

      @Override
      public void describeTo(Description description) {
       description.appendText("Expected to make one of these moves " + Arrays.toString(numbers));
      }
    };
  }
}
