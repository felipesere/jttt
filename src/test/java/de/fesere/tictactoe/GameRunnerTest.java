package de.fesere.tictactoe;

import de.fesere.tictactoe.players.ScriptablePlayer;
import de.fesere.tictactoe.ui.FakeConsole;
import org.junit.Before;
import org.junit.Test;

import static de.fesere.tictactoe.PlayerMark.O;
import static de.fesere.tictactoe.PlayerMark.X;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameRunnerTest {
  private final GameRunner runner = new GameRunner();
  private final FakeConsole fakeConsole = new FakeConsole();

  @Before
  public void setup() {
    fakeConsole.reset();
  }

  @Test
  public void testAnnounceWinner() {
    Player[] players = new Player[]{new ScriptablePlayer(X, asList(1, 2, 3, 4)), new ScriptablePlayer(O, asList(5, 6, 7))};
    runner.run(fakeConsole, players);
    assertThat(fakeConsole.hasWinner(X), is(true));
  }

  @Test
  public void testAnnounceDraw() {
    Player[] players = new Player[]{new ScriptablePlayer(X, asList(1, 2, 3, 7, 9, 10, 14, 16)), new ScriptablePlayer(O, asList(4, 5, 6, 8, 11, 12, 13, 15))};
    runner.run(fakeConsole, players);
    assertThat(fakeConsole.hasDraw(), is(true));
  }
}