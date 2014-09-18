package de.fesere.tictactoe;

import java.util.Map;
import java.util.stream.Stream;

public class ConsoleInterface {
  public String displayBoard(Board board) {
    Map<Integer, Mark> marks = board.getMarks();
    Template template = new Template();

    nonEmptyMarks(marks).forEach(entity -> {
      template.replace(entity.getKey(), entity.getValue().name());
    });
    return template.get();
  }

  private Stream<Map.Entry<Integer, Mark>> nonEmptyMarks(Map<Integer, Mark> marks) {
    return marks.entrySet().stream().filter(entity -> !entity.getValue().isEmpty());
  }


  private class Template {
    private String template = "[1][2][3]\n[4][5][6]\n[7][8][9]\n";

    public void replace(int location, String symbol) {
      template = template.replaceFirst(""+location, symbol);
    }

    public String get() {
      return template;
    }
  }
}
