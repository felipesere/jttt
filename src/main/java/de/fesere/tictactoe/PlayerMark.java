package de.fesere.tictactoe;

public enum PlayerMark {
  X,
  O,
  EMPTY;

  public PlayerMark opponent() {
    if(this == EMPTY) {
      throw new RuntimeException("no opponent of empty!");
    }
    if(this == X) {
      return O;
    }
    else{
      return X;
    }
  }

  public boolean isEmpty() {
    return this == EMPTY;
  }
}
