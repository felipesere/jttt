package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.PlayerMark;

public class FakeConsole extends Console {

  public FakeConsole() {
    super(new FakeIO());
  }

  private PlayerMark winnner = null;
  private boolean draw = false;
  private int Owins = 0;
  private int xWins = 0;

  public void reset() {
    winnner = null;
    draw = false;
  }

  @Override
  public void announceWinner(PlayerMark winner) {
    this.winnner = winner;
    if (winner == PlayerMark.O) {
      Owins++;
    }
    else {
      xWins++;
    }
  }

  @Override
  public void announceDraw() {
    this.draw = true;
  }

  @Override
  public boolean requestRematch() {
    return false;
  }

  public boolean hasWinner(PlayerMark winner) {
    return this.winnner == winner;
  }

  public boolean hasDraw() {
    return draw;
  }

  public int getOwins() {
    return Owins;
  }

  public int getxWins() {
    return xWins;
  }
}
