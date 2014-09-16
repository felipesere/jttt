package de.fesere.tictactoe;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;

public class StatisticsTest {

  Player first = AiPlayer.createAi(X);
  Player second = new RandomPlayer(O);
  Map<Player, Integer> score = new HashMap<>();
  int draws = 0;

  @Test
  public void test() {

    score.put(first, 0);
    score.put(second, 0);
    Player current = first;
    Board board = new Board();
    int iterations = 0;
    while(!board.isFinished()) {
      System.out.println(iterations++);
      current = changePlayer(current);
      board = current.performMove(board);
      if(board.hasWinner()) {
        int t = score.get(current);
        score.put(current, t + 1);
      }
      else if (board.hasDraw()) {
        draws++;
      }
    }
    System.out.println(current);
  }

  private Player changePlayer(Player current) {
   if(current == first) {
     return second;
   }
    else {
     return first;
   }
  }
}
