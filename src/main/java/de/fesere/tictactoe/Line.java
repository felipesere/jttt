package de.fesere.tictactoe;

import java.util.LinkedList;
import java.util.List;

class Line {

  private final List<PlayerMark> lineMarks;

  public Line(List<PlayerMark> elements) {
    lineMarks = new LinkedList<>(elements);
  }

  public boolean hasWinner() {
    return allSame() && !lineMarks.get(0).isEmpty();
  }

  private boolean allSame() {
    return lineMarks.stream().allMatch(mark -> mark.equals(lineMarks.get(0)));
  }

  public boolean isWinner(PlayerMark player) {
    return lineMarks.stream().allMatch(mark -> mark == player);
  }

  public PlayerMark getWinner() {
    if (hasWinner()) {
      return lineMarks.get(0);
    } else {
      return null;
    }
  }
}
