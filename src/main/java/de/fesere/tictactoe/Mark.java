package de.fesere.tictactoe;

public enum Mark {
  X,
  O;

  public Mark opponent() {
    if(this == X) {
      return O;
    }
    else{
      return X;
    }
  }
}
