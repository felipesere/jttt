package de.fesere.tictactoe;

import de.fesere.tictactoe.players.ScriptablePlayer;
import de.fesere.tictactoe.ui.ConsoleInterface;
import de.fesere.tictactoe.ui.FakeIO;
import org.junit.Test;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GameRunnerTest {
  GameRunner runner = new GameRunner();

  @Test
  public void testAnnounceWinner() {
    Player[] players = new Player[]{new ScriptablePlayer(X, asList(1, 2, 3)), new ScriptablePlayer(O, asList(4, 5))};
    runner.run(fakeInterfaceExpectsWinner(X), players);
  }

  @Test
  public void testAnnounceDraw() {
    Player[] players = new Player[]{new ScriptablePlayer(X, asList(1, 3, 4, 8, 9)), new ScriptablePlayer(O, asList(2, 5, 6, 7))};
    runner.run(fakeInterfaceExpectsDraw(), players);
  }

  private ConsoleInterface fakeInterfaceExpectsWinner(Mark expected) {

    return new ConsoleInterface(new FakeIO()) {
      @Override
      public void announceWinner(Mark winner) {
        assertThat(winner, is(expected));
      }

      @Override
      public void announceDraw() {
        fail("Should not have announecd a Draw");
      }

      @Override
      public boolean requestRematch() {
        return false;
      }
    };
  }

  private ConsoleInterface fakeInterfaceExpectsDraw() {
    return new ConsoleInterface(new FakeIO()) {

      @Override
      public void announceWinner(Mark winner) {
        fail("Should not have announecd a win");
      }

      @Override
      public void announceDraw() {
        assertTrue(true);
      }

      @Override
      public boolean requestRematch() {
        return false;
      }
    };
  }
}