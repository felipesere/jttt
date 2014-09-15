package de.fesere.tictactoe;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

  public static final String EMPTY = "";
  private List<String> marks;

  public Board() {
    marks = Collections.nCopies(9, EMPTY);
  }

  protected Board(List<String> marks){
    this.marks = marks;
  }

  public Board nextBoardFor(int index, String mark) {
    List<String> modifiedMarks = applyMark(index, mark);
    return new Board(modifiedMarks);
  }

  public List<Integer> getPossibleMoves() {
    return IntegerList(allIndizes().filter(i -> marks.get(i).equals(EMPTY)));
  }

  public boolean hasWinner() {
    return allLines().stream().anyMatch(Line::hasWinner);
  }

  private IntStream allIndizes() {
    return IntStream.range(0, marks.size());
  }

  private List<Integer> IntegerList(IntStream input) {
   return input.boxed().collect(Collectors.toList());
  }

  private List<String> applyMark(int index, String mark) {
    List<String> modified = new LinkedList<>(marks); modified.set(index, mark);
    return modified;
  }

  private List<Line> allLines() {
    List<Line> lines = new LinkedList<>();
    lines.addAll(getRows());
    lines.addAll(getColumns());
    lines.addAll(getDiagonals());
    return lines;
  }

  private List<Line> getRows() {
    List<Line> rows = new LinkedList<>();
    rows.add(line(0, 1, 2));
    rows.add(line(3, 4, 5));
    rows.add(line(6, 7, 8));
    return rows;
  }

  private List<Line> getColumns() {
    List<Line> columns = new LinkedList<>();
    columns.add(line(0, 3, 6));
    columns.add(line(1, 4, 7));
    columns.add(line(2, 5, 8));
    return columns;
  }

  private List<Line> getDiagonals() {
    List<Line> diagonals = new LinkedList<>();
    diagonals.add(line(0,4,8));
    diagonals.add(line(2,4,6));
    return diagonals;
  }

  private Line line(int ... indizes) {
    List<String> elements = new LinkedList<>();
    for(int index : indizes) {
      elements.add(marks.get(index));
    }
    return new Line(elements);
  }

  private class Line {

    private final String first;
    private final String second;
    private final String third;

    public Line(List<String> elements){
      first = elements.get(0);
      second = elements.get(1);
      third = elements.get(2);
    }

    public boolean hasWinner() {
      return first.equals(second) && second.equals(third) && !first.equals(EMPTY);
    }
  }
}
