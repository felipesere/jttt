package de.fesere.tictactoe;

public enum Mark {
  X,
  O,
  EMPTY;

  public Mark opponent() {
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
