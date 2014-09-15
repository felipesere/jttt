package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidMoveException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

  private List<Mark> marks;

  public Board() {
    marks = Collections.nCopies(9, Mark.EMPTY);
  }

  protected Board(List<Mark> marks){
    this.marks = marks;
  }

  public Board nextBoardFor(int index, Mark mark) {
    List<Mark> modifiedMarks = applyMark(index, mark);
    return new Board(modifiedMarks);
  }

  public List<Integer> getPossibleMoves() {
    return IntegerList(allIndizes().filter(i -> marks.get(i).isEmpty()));
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

  private List<Mark> applyMark(int index, Mark mark) {
    if(moveAlreadyTaken(index)){
      throw new InvalidMoveException();
    }
    List<Mark> modified = new LinkedList<>(marks); modified.set(index, mark);
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
    List<Mark> elements = new LinkedList<>();
    for(int index : indizes) {
      elements.add(marks.get(index));
    }
    return new Line(elements);
  }

  private class Line {

    private final Mark first;
    private final Mark second;
    private final Mark third;

    public Line(List<Mark> elements){
      first  = elements.get(0);
      second = elements.get(1);
      third  = elements.get(2);
    }

    public boolean hasWinner() {
      return first == second && second == third && !first.isEmpty();
    }
  }
}
