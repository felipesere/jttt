package de.fesere.tictactoe;

import de.fesere.tictactoe.players.AiPlayer;
import de.fesere.tictactoe.players.RandomPlayer;
import org.junit.Ignore;
import org.junit.Test;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static junit.framework.Assert.fail;

@Ignore
public class StatisticsTest {

  Player ai     = AiPlayer.createAi(X);
  Player random = new RandomPlayer(O);

  @Test
  public void test() {
    for (int i = 0; i < 15; i++) {
      Player current = random;
      Board board = new Board();
      while (!board.isFinished()) {
        current = changePlayer(current);
        board = current.performMove(board);
        if (board.hasWinner() && current == random) {
          fail("Random player should not win!");
        }
      }
    }
  }

  private Player changePlayer(Player current) {
    if (current == ai) {
      return random;
    } else {
      return ai;
    }
  }
}
