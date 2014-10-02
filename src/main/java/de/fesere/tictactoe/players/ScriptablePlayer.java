package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.PlayerMark;
import de.fesere.tictactoe.Player;

import java.util.LinkedList;
import java.util.List;

public class ScriptablePlayer implements Player {

  private final PlayerMark mark;
  private final List<Integer> moveList;

  public ScriptablePlayer(PlayerMark mark, List<Integer> moves) {
    this.mark = mark;
    this.moveList = new LinkedList<>(moves);
  }

  @Override
  public Board performMove(Board board) {
    int move = moveList.remove(0);
    return board.nextBoardFor(move, mark);
  }

  @Override
  public PlayerMark getMark() {
    return mark;
  }
}
