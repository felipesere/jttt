package de.fesere.tictactoe;

public enum Mark {
  X,
  O,
  EMPTY;
  public boolean isEmpty() {
    return this == EMPTY;
  }

  public Mark opponent() {
    if(this == X) {
      return O;
    }
    else if(this == O) {
      return X;
    }
    else{
      throw new RuntimeException("EMPTY has no opponent");
    }
  }
}
