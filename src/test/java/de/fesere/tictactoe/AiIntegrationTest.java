package de.fesere.tictactoe;

import de.fesere.tictactoe.players.AiPlayer;
import de.fesere.tictactoe.players.RandomPlayer;
import de.fesere.tictactoe.ui.FakeConsole;
import org.junit.Ignore;
import org.junit.Test;

import static de.fesere.tictactoe.PlayerMark.O;
import static de.fesere.tictactoe.PlayerMark.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AiIntegrationTest {


  @Ignore
  @Test
  public void unbeatableTest() {
    Player ai     = new AiPlayer(X);
    Player random = new RandomPlayer(O);

    Board board = new Board();
    FakeConsole consoleInterface = new FakeConsole();
    for (int i = 0; i < 100; i++) {
      Game game = new Game(consoleInterface,board, ai, random);
      game.play();
    }

    System.out.println("AI wins: " + consoleInterface.getxWins());
    assertThat(consoleInterface.getOwins(), is(0));
  }
}
