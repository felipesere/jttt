package de.fesere.tictactoe;

public interface Player {
  Board performMove(Board board);

  PlayerMark getMark();
}
