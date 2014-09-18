package de.fesere.tictactoe;

import org.junit.Test;

import static de.fesere.tictactoe.Mark.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsoleInterfaceTest {

  Board emptyBoard = new Board();
  ConsoleInterface console = new ConsoleInterface();

  @Test
  public void canPrintABoardAnEmpty() {
    assertThat(console.displayBoard(emptyBoard), is("[1][2][3]\n[4][5][6]\n[7][8][9]\n"));
  }

  @Test
  public void canPrintABoardWithASingleMarker() {
    Board newBoard = emptyBoard.nextBoardFor(1, O);
    assertThat(console.displayBoard(newBoard), is("[O][2][3]\n[4][5][6]\n[7][8][9]\n"));
  }

  @Test
  public void canPrintABoardWithAMultpleMarkers() {
    Board newBoard = emptyBoard.nextBoardFor(1, O).nextBoardFor(5, X);
    assertThat(console.displayBoard(newBoard), is("[O][2][3]\n[4][X][6]\n[7][8][9]\n"));
  }
}