package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.players.PlayerFactory;

import java.util.Map;
import java.util.stream.Stream;

public class Console {
  private final IO io;

  public Console(IO io) {
    this.io = io;
  }

  public Console() {
    this(new IO());
  }

  public void displayBoard(Board board) {
    Map<Integer, Mark> marks = board.getMarks();
    Template template = new Template();

    nonEmptyMarks(marks).forEach(entity -> {
      template.add(entity.getKey(), entity.getValue().name());
    });
    io.write(template.get());
  }

  private Stream<Map.Entry<Integer, Mark>> nonEmptyMarks(Map<Integer, Mark> marks) {
    return marks.entrySet().stream().filter(entity -> !entity.getValue().isEmpty());
  }

  public void displayMenu() {
    io.write("Choose game:\n(1) Human vs. Computer\n(2) Computer vs. Human\n(3) Computer vs. Computer\n(4) Human vs. Human");
  }

  public void requestReplay() {
    io.write("Do you want to play again? (1) Yes  (2) No");
  }

  public int requestPlayers() {
    displayMenu();
    Integer choice = io.readInput();

    while(!validPlayerChoice(choice)) {
      displayMenu();
      choice = io.readInput();
    }
    return choice;
  }

  private boolean validPlayerChoice(Integer choice) {
    return choice != null && PlayerFactory.validPlayerChoice(choice);
  }

  public void announceWinner(Mark winner) {
    io.write("The winner is " + winner);
  }

  public void announceDraw() {
    io.write("There was a draw");
  }

  public boolean requestRematch() {
    requestReplay();
    Integer choice = io.readInput();
    while(!isValidChoice(choice)) {
      requestReplay();
      choice = io.readInput();
    }
    return choice == 1;
  }

  private boolean isValidChoice(Integer choice) {
    return choice != null && 0 < choice  && choice < 3;
  }

  public Integer requestMove() {
    io.write("Please enter a move number: ");
    return io.readInput();
  }
}
