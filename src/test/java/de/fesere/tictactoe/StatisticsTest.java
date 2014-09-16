package de.fesere.tictactoe;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StatisticsTest {

  Player first = AiPlayer.createAi(X);
  Player second = new RandomPlayer(O);
  int draws = 0;
  int ai = 0;
  int random = 0;

  @Test
  public void test() {
    for (int i = 0; i < 100; i++) {
      Player current = second;
      Board board = new Board();
      while (!board.isFinished()) {
        current = changePlayer(current);
        board = current.performMove(board);
        if (board.hasWinner()) {
          if (current == first) {
            ai++;
          } else {
            random++;
          }
        } else if (board.hasDraw()) {
          draws++;
        }
      }
    }
    System.out.println("AI wins: " + ai);
    System.out.println("Random wins: " + random);
    System.out.println("Draws: " + draws);
    assertThat(random, is(0));
  }

  private Player changePlayer(Player current) {
    if (current == first) {
      return second;
    } else {
      return first;
    }
  }

  private class BoardSequence {
    List<Board> sequence = new LinkedList<>();

    public void add(Board board) {
      sequence.add(board);
    }

    public String toString() {
      String result = "";
      for(int i = 0; i < sequence.size(); i++){
        Board board = sequence.get(i);
       result += i + " " + board.toString() + "\n";
      }
      return result;
    }
  }
}
