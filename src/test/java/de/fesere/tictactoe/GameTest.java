package de.fesere.tictactoe;

import de.fesere.tictactoe.players.ScriptablePlayer;
import de.fesere.tictactoe.ui.FakeConsole;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static de.fesere.tictactoe.PlayerMark.O;
import static de.fesere.tictactoe.PlayerMark.X;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  private final FakeConsole fakeConsole = new FakeConsole();

  @Before
  public void setup() {
    fakeConsole.reset();
  }

  @Test
  public void firstPlayerWins() {
    Game game = new Game(fakeConsole, scriptedPlayer(X, asList(1, 2, 3, 4)),
                                      scriptedPlayer(O, asList(5, 6, 7)));
    game.play();
    assertThat(fakeConsole.hasWinner(X), is(true));
  }

  @Test
  public void testAnnounceDraw() {
    Game game = new Game(fakeConsole, scriptedPlayer(X, asList(1, 2, 3, 7, 9, 10, 14, 16)),
                                      scriptedPlayer(O, asList(4, 5, 6, 8, 11, 12, 13, 15)));
    game.play();
    assertThat(fakeConsole.hasDraw(), is(true));
  }

  public Player scriptedPlayer(PlayerMark mark, List<Integer> inputMoves) {
    return new ScriptablePlayer(mark, inputMoves);
  }
}