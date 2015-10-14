package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidMoveException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final List<PlayerMark> marks;
    private int numberOfCells;
    private int numberOfRows;

    public Board(int numberOfCells) {
        this.numberOfCells = numberOfCells;
        marks = Collections.nCopies(this.numberOfCells, PlayerMark.EMPTY);
        this.numberOfRows = (int) Math.sqrt(numberOfCells);
    }

    public Board(List<PlayerMark> marks) {
        this.marks = marks;
        this.numberOfCells = marks.size();
        this.numberOfRows = (int) Math.sqrt(numberOfCells);
    }

    public Board nextBoardFor(int index, PlayerMark mark) {
        List<PlayerMark> modifiedMarks = applyMark(index, mark);
        return new Board(modifiedMarks);
    }

    public List<Integer> getPossibleMoves() {
        List<Integer> moves = possibleMoves();
        return moves;
    }

    private List<Integer> possibleMoves() {
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
        List<Line> rows = new LinkedList<>();
        int counter = 0;
        for (int i = 0; i < numberOfRows; i++) {
            rows.add(line(getIndicesForRow(counter)));
            counter += numberOfRows;
        }
        return rows;
    }

    private int[] getIndicesForRow(int startingIndex) {
        int[] indices = new int[numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            indices[i] = startingIndex;
            startingIndex++;
        }
        return indices;
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

    private Line line(int... indices) {
        List<PlayerMark> elements = new LinkedList<>();
        for (int index : indices) {
            elements.add(marks.get(index));
        }
        return new Line(elements);
    }

    public int getScore() {
        if (hasWinner()) {
            return possibleMoves().size() + 1;
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
        return allLines().stream().filter(Line::hasWinner).findFirst().get().getFirst();
    }

    public boolean isWinner(PlayerMark player) {
        return allLines().stream().anyMatch(line -> line.isWinner(player));
    }

    public boolean validMove(int move) {
        return getPossibleMoves().contains(move);
    }

    private class Line {

        private List<PlayerMark> playerMarks;

        public Line(List<PlayerMark> elements) {
            playerMarks = elements;
        }

        public boolean hasWinner() {
            return allSame() && !getFirst().isEmpty();
        }

        private boolean allSame() {
            PlayerMark checkMark = getFirst();
            for (PlayerMark playerMark : playerMarks) {
                if (playerMark != checkMark)
                    return false;
            }
            return true;
        }

        private PlayerMark getFirst() {
            return playerMarks.get(0);
        }

        public boolean isWinner(PlayerMark player) {
            return allSame() && getFirst() == player;
        }
    }
}