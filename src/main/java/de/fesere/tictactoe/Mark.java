package de.fesere.tictactoe;

public enum Mark {
  X,
  O,
  EMPTY;
  public boolean isEmpty() {
    return this == EMPTY;
  }

}
