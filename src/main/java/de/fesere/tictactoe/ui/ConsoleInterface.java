package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.players.AiPlayer;
import de.fesere.tictactoe.players.HumanPlayer;

import java.util.Map;
import java.util.stream.Stream;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;

public class ConsoleInterface {
  private IO io;

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

  public Player[] requestPlayers() {
    displayMenu();
    Integer choice = io.readInput();
    if(choice == null || choice < 1 || choice > 4)  {
      return requestPlayers();
    }

    if(choice == 1) {
      return new Player[] {new HumanPlayer(X), AiPlayer.createAi(O)};
    }
    if(choice == 2) {
      return new Player[] {AiPlayer.createAi(O), new HumanPlayer(X)};
    }
    if(choice == 3) {
      return new Player[] {AiPlayer.createAi(X), AiPlayer.createAi(O)};
    }

    return new Player[] {new HumanPlayer(X), new HumanPlayer(O)};
  }

  public void announceWinner(Mark winner) {
  }

  public void announceDraw() {
  }

  public boolean requestRematch() {
    return false;
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
