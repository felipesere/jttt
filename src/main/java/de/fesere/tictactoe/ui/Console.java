package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.PlayerMark;
import de.fesere.tictactoe.players.PlayerFactory;

import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
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
    Map<Integer, PlayerMark> marks = board.getMarks();
    Template template = new Template(board.getSize());

    nonEmptyMarks(marks).forEach(entity -> {
      template.add(entity.getKey(), entity.getValue().name());
    });
    io.write(template.get());
  }

  private Stream<Map.Entry<Integer, PlayerMark>> nonEmptyMarks(Map<Integer, PlayerMark> marks) {
    return marks.entrySet().stream().filter(entity -> !entity.getValue().isEmpty());
  }

  public void displayMenu() {
    io.write("Choose game:\n(1) Human vs. Computer\n(2) Computer vs. Human\n(3) Computer vs. Computer\n(4) Human vs. Human");
  }

  public void requestReplay() {
    io.write("Do you want to play again? (1) Yes  (2) No");
  }

  public int requestPlayers() {
    return validate(io.readInput(this::displayMenu),
                                 PlayerFactory::validPlayerChoice);
  }

  public void announceWinner(PlayerMark winner) {
    io.write("The winner is " + winner);
  }

  public void announceDraw() {
    io.write("There was a draw");
  }

  public boolean requestRematch() {
    return 1 == validate(io.readInput(this::requestReplay),
                                      this::isValidChoice);
  }

  private boolean isValidChoice(int choice) {
    return 0 < choice  && choice < 3;
  }

  public Integer requestMove() {
    io.write("Please enter a move number: ");
    return io.readInput();
  }

  public int requestBoardSize(int [] options) {
    return validate(io.readInput(() -> displayBoardSizes(options)),
                     value -> isValidBoardChoice(options, value));
  }

  private int validate(Supplier<Integer> supplier, IntPredicate isValid) {
    Integer input = supplier.get();
    while(input == null || !isValid.test(input)){
      input = supplier.get();
    }
    return input;
  }

  private boolean isValidBoardChoice(int[] options, int choice) {
    return 0 < choice && choice <= options.length;
  }

  private void displayBoardSizes(int[] options) {
    String output = "Choose board size:" + buildOptions(options);
    io.write(output);
  }

  private String buildOptions(int[] options) {
    String s = "";
    for (int i = 0; i < options.length; i++) {
      s += " ("+(i+1)+") "+options[i]+"x"+options[i];
    }
    return s;
  }
}
