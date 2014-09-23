package de.fesere.tictactoe;

import de.fesere.tictactoe.players.ScriptablePlayer;
import de.fesere.tictactoe.ui.FakeConsole;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  private FakeConsole fakeConsole = new FakeConsole();

  @Before
  public void setup() {
    fakeConsole.reset();
  }

  @Test
  public void firstPlayerWins() {
    Game game = new Game(fakeConsole, scriptedPlayer(X, asList(1,2,3)),
                                      scriptedPlayer(O, asList(4,5)));
    game.play();
    assertThat(fakeConsole.hasWinner(X), is(true));
  }

  @Test
  public void testAnnounceDraw() {
    Game game = new Game(fakeConsole, scriptedPlayer(X, asList(1, 3, 4, 8, 9)),
                                      scriptedPlayer(O, asList(2, 5, 6, 7)));
    game.play();
    assertThat(fakeConsole.hasDraw(), is(true));
  }

  public Player scriptedPlayer(Mark mark, List<Integer> inputMoves) {
    return new ScriptablePlayer(mark, inputMoves);
  }
}