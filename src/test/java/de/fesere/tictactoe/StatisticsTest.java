package de.fesere.tictactoe;

import de.fesere.tictactoe.players.AiPlayer;
import de.fesere.tictactoe.players.RandomPlayer;
import de.fesere.tictactoe.ui.ConsoleInterface;
import de.fesere.tictactoe.ui.FakeIO;
import org.junit.Test;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static org.junit.Assert.fail;

public class StatisticsTest {

  Player ai     = AiPlayer.createAi(X);
  Player random = new RandomPlayer(O);

  @Test
  public void test() {
    ConsoleInterface consoleInterface = new ConsoleInterface(new FakeIO());
    for (int i = 0; i < 15; i++) {
      Game game = new Game(consoleInterface, ai, random);
      game.play();
      if(game.hasWinner() && game.getWinner() == O) {
        fail("Random should never win!");
      }
    }
  }
}
