package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.players.AiPlayer;
import de.fesere.tictactoe.players.HumanPlayer;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsoleInterfaceTest {

  Board emptyBoard = new Board();
  FakeIO accessibleIO = new FakeIO();
  ConsoleInterface console = new ConsoleInterface(accessibleIO);

  @Before
  public void setup() {
    accessibleIO.reset();
  }
  @Test
  public void canPrintABoardAnEmpty() {
    console.displayBoard(emptyBoard);
    expectOutputToBe("[1][2][3]\n[4][5][6]\n[7][8][9]\n");
  }

  @Test
  public void canPrintABoardWithASingleMarker() {
    Board newBoard = emptyBoard.nextBoardFor(1, O);
    console.displayBoard(newBoard);
    expectOutputToBe("[O][2][3]\n[4][5][6]\n[7][8][9]\n");
  }

  @Test
  public void canPrintABoardWithAMultpleMarkers() {
    Board newBoard = emptyBoard.nextBoardFor(1, O).nextBoardFor(5, X);
    console.displayBoard(newBoard);
    expectOutputToBe("[O][2][3]\n[4][X][6]\n[7][8][9]\n");
  }

  @Test
  public void canPrintAMenuWithFourPlayerCombinations() {
   console.displayMenu();
   expectOutputToBe("Choose game:\n(1) Human vs. Computer\n(2) Computer vs. Human\n(3) Computer vs. Computer\n(4) Human vs. Human");
  }

  @Test
  public void canRequestThePlayerToPlayAgain() {
    console.requestReplay();
    expectOutputToBe("Do you want to play again? (1) Yes  (2) No");
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
    accessibleIO.setInputs(1);
    boolean answer = console.requestRematch();
    expectOutputToBe("Do you want to play again? (1) Yes  (2) No");
    assertThat(answer, is(true));
  }

  @Test
  public void canRequestARematchDenied() {
    accessibleIO.setInputs(2);
    boolean answer = console.requestRematch();
    expectOutputToBe("Do you want to play again? (1) Yes  (2) No");
    assertThat(answer, is(false));
  }

  @Test
  public void canRequestAMoveToBeMade() {
    accessibleIO.setInputs(1);
    console.requestMove();
    expectOutputToBe("Please enter a move number: ");
  }
  @Test
  public void requestsARematchUntilCorrectAnswer() {
    accessibleIO.setInputs(-1,1);
    boolean answer = console.requestRematch();
    assertThat(answer, is(true));
  }

  @Test
  public void canRequestHumanAndComputer() {
    Player[] players = new ConsoleInterface(userEnters(1)).requestPlayers();
    assertThat(players, are(HumanPlayer.class, AiPlayer.class));
  }

  @Test
  public void canRequestComputerAndHuman() {
    Player[] players = new ConsoleInterface(userEnters(2)).requestPlayers();
    assertThat(players, are(AiPlayer.class,HumanPlayer.class));
  }

  @Test
  public void canRequestTwoAiPlayers() {
    Player[] players = new ConsoleInterface(userEnters(3)).requestPlayers();
    assertThat(players, are(AiPlayer.class, AiPlayer.class));
  }

  @Test
  public void canRequestTwoHumanPlayers() {
    Player[] players = new ConsoleInterface(userEnters(4)).requestPlayers();
    assertThat(players, are(HumanPlayer.class, HumanPlayer.class));
  }

  private void expectOutputToBe(String expected) {
    assertThat(accessibleIO.getWritten(), is(expected));
  }

  private IO userEnters(int choice) {
    FakeIO fakeIO = new FakeIO();
    fakeIO.setInputs(choice);
    return fakeIO;
  }

  private Matcher<Player[]> are(Class ... klass) {
    return new TypeSafeMatcher<Player[]>() {
      @Override
      protected boolean matchesSafely(Player[] item) {
        if (item.length != klass.length) {
          return false;
        }
        for (int i = 0; i < item.length; i++) {
          if (!klass[i].isInstance(item[i])) {
            return false;
          }
        }
        return true;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(classes(klass));
      }

      private String classes(Class[] klass) {
        return Stream.of(klass).map(Class::getCanonicalName).collect(toList()).toString();
      }
    };
  }
}