package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Mark;

public class FakeConsole extends ConsoleInterface {

  public FakeConsole() {
    super(new FakeIO());
  }

  private Mark winnner = null;
  private boolean draw = false;
  private int Owins = 0;
  private int xWins = 0;

  public void reset() {
    winnner = null;
    draw = false;
  }

  @Override
  public void announceWinner(Mark winner) {
    this.winnner = winner;
    if (winner == Mark.O) {
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

  public boolean hasWinner(Mark winner) {
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
