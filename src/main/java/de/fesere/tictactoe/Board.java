package de.fesere.tictactoe;

import com.google.common.collect.Lists;
import de.fesere.tictactoe.exceptions.InvalidMoveException;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.*;

public class Board {

  private final List<PlayerMark> marks;
  private final int sideSize;

  public Board() {
    this(3);
  }

  public Board(List<PlayerMark> marks) {
    this.marks = marks;
    this.sideSize = (int) Math.sqrt(marks.size());
  }

  public Board(int size) {
    this.sideSize = size;
    int effectiveSize = size * size;
    marks = Collections.nCopies(effectiveSize, PlayerMark.EMPTY);
  }

  public Board nextBoardFor(int index, PlayerMark mark) {
    List<PlayerMark> modifiedMarks = applyMark(index, mark);
    return new Board(modifiedMarks);
  }

  public List<Integer> getPossibleMoves() {
    return IntegerList(allIndices().filter(i -> marks.get(i).isEmpty()).map(i -> i + 1));
  }

  public boolean hasWinner() {
    return allLines().stream().anyMatch(Line::hasWinner);
  }

  public boolean isFinished() {
    return hasWinner() || hasNoMoreMoves();
  }

  private boolean hasNoMoreMoves() {
    return getPossibleMoves().isEmpty();
  }

  private IntStream allIndices() {
    return range(0, marks.size());
  }

  private List<Integer> IntegerList(IntStream input) {
    return input.boxed().collect(toList());
  }

  private List<PlayerMark> applyMark(int index, PlayerMark mark) {
    if (moveAlreadyTaken(index)) {
      throw new InvalidMoveException();
    }
    List<PlayerMark> modified = new LinkedList<>(marks);
    modified.set(index - 1, mark);
    return modified;
  }

  private boolean moveAlreadyTaken(int index) {
    return !getPossibleMoves().contains(index);
  }

  private List<Line> allLines() {
    List<Line> lines = new LinkedList<>();
    lines.addAll(getRows());
    lines.addAll(getColumns());
    lines.addAll(getDiagonals());
    return lines;
  }

  private List<Line> getRows() {
    return Lists.partition(marks, sideSize).stream().map(Line::new).collect(toList());
  }

  private List<Line> getColumns() {
    List<Line> columns = new LinkedList<>();
    range(0, sideSize).forEach(column -> {
      columns.add(new Line(getColumn(column)));
    });
    return columns;
  }

  private List<PlayerMark> getColumn(int column) {
    List<PlayerMark> elements = new LinkedList<>();
    range(0, sideSize).forEach(offset -> {
      elements.add(marks.get(column + offset * sideSize));
    });
    return elements;
  }

  private List<Line> getDiagonals() {
    List<PlayerMark> topLeft = new LinkedList<>();
    List<PlayerMark> topRight = new LinkedList<>();
    range(0, sideSize).forEach(i -> {
      topLeft.add(marks.get(i * (sideSize + 1)));
      topRight.add(marks.get((i + 1) * (sideSize - 1)));
    });
    return asList(new Line(topLeft), new Line(topRight));
  }

  public int getScore() {
    if (hasWinner()) {
      return getPossibleMoves().size() + 1;
    }
    return 0;
  }

  public Map<Integer, PlayerMark> getMarks() {
    Map<Integer, PlayerMark> result = new HashMap<>();
    for (int i = 0; i < marks.size(); i++) {
      result.put(i + 1, marks.get(i));
    }
    return result;
  }

  public PlayerMark getWinner() {
    return allLines().stream().filter(Line::hasWinner).findFirst().get().getWinner();
  }

  public boolean isWinner(PlayerMark player) {
    return allLines().stream().anyMatch(line -> line.isWinner(player));
  }

  public boolean validMove(int move) {
    return getPossibleMoves().contains(move);
  }
}