package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import org.junit.Before;
import org.junit.Test;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsoleInterfaceTest {

  private Board emptyBoard = new Board();
  private FakeIO fakeIO = new FakeIO();
  private ConsoleInterface console = new ConsoleInterface(fakeIO);

  @Before
  public void setup() {
    fakeIO.reset();
  }

  @Test
  public void canPrintABoardAnEmpty() {
    console.displayBoard(emptyBoard);
    expectOutputToBe("[1][2][3]\n[4][5][6]\n[7][8][9]\n");
  }

  @Test
  public void canPrintABoardWithASingleMarker() {
    Board newBoard = emptyBoard.nextBoardFor(1, X);
    console.displayBoard(newBoard);
    expectOutputToBe("[X][2][3]\n[4][5][6]\n[7][8][9]\n");
  }

  @Test
  public void canPrintABoardWithAMultpleMarkers() {
    Board newBoard = emptyBoard.nextBoardFor(1, O).nextBoardFor(5, X);
    console.displayBoard(newBoard);
    expectOutputToBe("[O][2][3]\n[4][X][6]\n[7][8][9]\n");
  }

  @Test
  public void canAnnounceAWinner() {
    console.announceWinner(X);
    expectOutputToBe("The winner is X");
  }

  @Test
  public void canAnnounceADraw() {
    console.announceDraw();
    expectOutputToBe("There was a draw");
  }

  @Test
  public void canRequestARematchAccepted() {
    fakeIO.setInputs(1);
    boolean answer = console.requestRematch();
    expectOutputToBe("Do you want to play again? (1) Yes  (2) No");
    assertThat(answer, is(true));
  }

  @Test
  public void canRequestARematchDenied() {
    fakeIO.setInputs(2);
    boolean answer = console.requestRematch();
    expectOutputToBe("Do you want to play again? (1) Yes  (2) No");
    assertThat(answer, is(false));
  }

  @Test
  public void canRequestAMoveToBeMade() {
    fakeIO.setInputs(1);
    console.requestMove();
    expectOutputToBe("Please enter a move number: ");
  }
  @Test
  public void requestsARematchUntilCorrectAnswer() {
    fakeIO.setInputs(-1, 1);
    boolean answer = console.requestRematch();
    assertThat(answer, is(true));
  }


  private void expectOutputToBe(String expected) {
    assertThat(fakeIO.getWritten(), is(expected));
  }
}