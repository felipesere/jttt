package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Console4x4Test {

  private final Board emptyBoard = new Board(4);
  private final FakeIO fakeIO = new FakeIO();
  private final Console console = new Console(fakeIO);

  @Test
  public void canPrintABoardAnEmpty() {
    console.displayBoard(emptyBoard);
    expectOutputToBe("[1][2][3][4]\n[5][6][7][8]\n[9][10][11][12]\n[13][14][15][16]\n");
  }

  private void expectOutputToBe(String expected) {
    assertThat(fakeIO.getWritten(), is(expected));
  }
}
