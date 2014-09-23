package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.players.PlayerFactory;

import java.util.Map;
import java.util.stream.Stream;

public class ConsoleInterface {
  private final IO io;

  public ConsoleInterface(IO io) {
    this.io = io;
  }

  public ConsoleInterface() {
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

    if(!validPlayerChoice(choice)){
      return requestPlayers();
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
    if(!isValidChoice(choice)) {
      return requestRematch();
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

  private class Template {
    private String template = "[1][2][3]\n[4][5][6]\n[7][8][9]\n";

    public void add(int location, String symbol) {
      template = template.replaceFirst(""+location, symbol);
    }

    public String get() {
      return template;
    }
  }
}
