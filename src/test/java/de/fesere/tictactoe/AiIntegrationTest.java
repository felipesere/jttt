package de.fesere.tictactoe;

import de.fesere.tictactoe.players.AiPlayer;
import de.fesere.tictactoe.players.RandomPlayer;
import de.fesere.tictactoe.ui.ConsoleInterface;
import de.fesere.tictactoe.ui.FakeIO;
import org.junit.Test;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;

public class AiIntegrationTest {


  @Test
  public void unbeatableTest() {
    Player ai     = AiPlayer.createAi(X);
    Player random = new RandomPlayer(O);

    ConsoleInterface consoleInterface = new ConsoleInterface(new FakeIO());
    for (int i = 0; i < 15; i++) {
      Game game = new Game(consoleInterface, ai, random);
      game.play();
    }
  }
}
