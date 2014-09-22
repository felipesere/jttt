package de.fesere.tictactoe;

import de.fesere.tictactoe.ui.ConsoleInterface;
import de.fesere.tictactoe.ui.FakeIO;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  @Test
  public void firstPlayerWins() {
    Game game = new Game(new ConsoleInterface(new FakeIO()), scriptedPlayer(X, asList(1,2,3)),scriptedPlayer(O, asList(4,5)));
    game.play();
    assertThat(game.hasWinner(), is(true));
    assertThat(game.getWinner(), is(X));
  }

  public Player scriptedPlayer(Mark mark, List<Integer> inputMoves) {
    return new Player() {

      List<Integer> moveList = new LinkedList<>(inputMoves);

      @Override
      public Board performMove(Board board) {
        int move = moveList.remove(0);
        return board.nextBoardFor(move, mark);
      }

      @Override
      public Mark getMark() {
        return mark;
      }
    };
  }
}