package de.fesere.tictactoe;

import org.junit.Test;

import static de.fesere.tictactoe.PlayerMark.EMPTY;
import static de.fesere.tictactoe.PlayerMark.X;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

public class Board4x4Test {
    @Test
    public void itStartsWithSixteenPossibleMoves() {
        Board board = new Board(4);
        assertThat(board.getPossibleMoves(), hasSize(16));
    }

    @Test
    public void doesNotHaveAWinner() {
        Board board = new Board(asList(X, X, X, EMPTY,
                                       EMPTY, EMPTY, EMPTY, EMPTY,
                                       EMPTY, EMPTY, EMPTY, EMPTY,
                                       EMPTY, EMPTY, EMPTY, EMPTY));
        noWinner(board);
    }

    private void noWinner(Board board) {
        assertThat(board.hasWinner(), is(false));
    }


}
