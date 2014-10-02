package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidMoveException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

  private final List<PlayerMark> marks;

  public Board() {
    marks = Collections.nCopies(9, PlayerMark.EMPTY);
  }

  public Board(List<PlayerMark> marks){
    this.marks = marks;
  }

  public Board(int size) {
    int effectiveSize = size * size;
    marks = Collections.nCopies(effectiveSize, PlayerMark.EMPTY);
  }

  public Board nextBoardFor(int index, PlayerMark mark) {
    List<PlayerMark> modifiedMarks = applyMark(index, mark);
    return new Board(modifiedMarks);
  }

  public List<Integer> getPossibleMoves() {
    return IntegerList(allIndices()
            .filter(i -> marks.get(i).isEmpty())
            .map(i -> i + 1));
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
    return IntStream.range(0, marks.size());
  }

  private List<Integer> IntegerList(IntStream input) {
   return input.boxed().collect(Collectors.toList());
  }

  private List<PlayerMark> applyMark(int index, PlayerMark mark) {
    if(moveAlreadyTaken(index)){
      throw new InvalidMoveException();
    }
    List<PlayerMark> modified = new LinkedList<>(marks);
    modified.set(index-1, mark);
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
    diagonals.add(line(0, 4, 8));
    diagonals.add(line(2, 4, 6));
    return diagonals;
  }

  private Line line(int ... indizes) {
    List<PlayerMark> elements = new LinkedList<>();
    for(int index : indizes) {
      elements.add(marks.get(index));
    }
    return new Line(elements);
  }

  public int getScore() {
    if(hasWinner()) {
      return getPossibleMoves().size() + 1;
    }
    return 0;
  }

  public Map<Integer, PlayerMark> getMarks() {
    Map<Integer, PlayerMark> result = new HashMap<>();
    for(int i = 0; i < marks.size(); i++) {
      result.put(i+1, marks.get(i));
    }
    return result;
  }

  public PlayerMark getWinner() {
    return allLines().stream().filter(Line::hasWinner).findFirst().get().first;
  }

  public boolean isWinner(PlayerMark player) {
    return allLines().stream().anyMatch(line -> line.isWinner(player));
  }

  public boolean validMove(int move) {
    return getPossibleMoves().contains(move);
  }

  private class Line {

    private final PlayerMark first;
    private final PlayerMark second;
    private final PlayerMark third;

    public Line(List<PlayerMark> elements){
      first  = elements.get(0);
      second = elements.get(1);
      third  = elements.get(2);
    }

    public boolean hasWinner() {
      return allSame() && !first.isEmpty();
    }

    private boolean allSame() {
      return first == second && second == third;
    }

    public boolean isWinner(PlayerMark player) {
      return allSame() && first == player;
    }
  }
}