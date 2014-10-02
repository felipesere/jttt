package de.fesere.tictactoe;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class Board4x4Test {

  @Test
  public void has16possibleMoves() {
    Board board = new Board(4);
    assertThat(board.getPossibleMoves(), hasSize(16));
  }
}
