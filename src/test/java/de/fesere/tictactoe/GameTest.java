package de.fesere.tictactoe;

import de.fesere.tictactoe.players.AiPlayer;
import de.fesere.tictactoe.players.HumanPlayer;
import org.junit.Ignore;
import org.junit.Test;

public class GameTest {

  @Ignore
  @Test
  public void test() {
    Game game = new Game(new HumanPlayer(Mark.X), AiPlayer.createAi(Mark.O));
    game.play();
  }
}