package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.ui.Console;
import de.fesere.tictactoe.Player;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static de.fesere.tictactoe.Mark.O;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class HumanPlayerTest extends PlayerCommonTest {

  private final Board emptyBoard = new Board();

  @Override
  Player playerForCommonTests() {
    return new HumanPlayer(O, consoleUi(1));
  }

  @Test
  public void deniesNullAsInvalidMoveReadsAgain() {
   Player player = new HumanPlayer(O, consoleUi(null, 1));
   Board newBoard = player.performMove(emptyBoard);
   assertThat(newBoard.getPossibleMoves(), not(hasItem(1)));
  }

  @Test
  public void deniesNegativeNumberAsInvalidMoveReadsAgain() {
    Player player = new HumanPlayer(O, consoleUi(-1, 1));
    Board newBoard = player.performMove(emptyBoard);
    assertThat(newBoard.getPossibleMoves(), not(hasItem(1)));
  }

  @Test
  public void deniesMovesNotWithinPossibleMovesAsInvalidMoveReadsAgain() {
    Player player = new HumanPlayer(O, consoleUi(1, 1,2));
    Board newBoard = player.performMove(player.performMove(emptyBoard));
    assertThat(newBoard.getPossibleMoves(), not(hasItems(1,2)));
  }

  public Console consoleUi(Integer ... items) {
    return new Console() {
      final List<Integer> elements = new LinkedList<>(asList(items));

      @Override
      public Integer requestMove() {
        return elements.remove(0);
      }
    };
  }
}