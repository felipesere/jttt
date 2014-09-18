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

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AiPlayerTest extends PlayerCommonTest {

  private Player player;

  @Before
  public void before() {
    player =  getPlayer();
  }

  @Override
  Player getPlayer() {
    return AiPlayer.createAi(X);
  }
  @Test
  public void makesDirectWinningMove(){
    Board board = new Board(asList(X, X, null,
                                   O, O, X,
                                   O, X, O));
    Board result = player.performMove(board);
    assertThat(result.hasWinner(), is(true));
  }

  @Test
  public void forcesOponentsHandVersion1(){
    Board board = new Board(asList(O,     null, null,
                                   null, O,     null,
                                   null, null, X));
    Board result = player.performMove(board);
    System.out.println(result);
    assertThat(result.getPossibleMoves(), madeOneOfTheseMoves(2, 6));
  }

  @Test
  public void forcesOponentsHandVersion2(){
    Board board = new Board(asList(O,     null, null,
                                   null, X,     null,
                                   null, null, O));
    Board result = player.performMove(board);
    assertThat(result.getPossibleMoves(), madeOneOfTheseMoves(1, 3, 5, 7));
  }

  @Test
  public void forcesOponentsHandVersion3(){
    Board board = new Board(asList(null,     O, null,
                                   O,         X, null,
                                   null, null, null));
    Board result = player.performMove(board);
    assertThat(result.getPossibleMoves(), madeOneOfTheseMoves(0, 2, 6, 7));
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