package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Mark;

public class FakeConsole extends ConsoleInterface {
  public FakeConsole() {
    super(new FakeIO());
  }
  private Mark winnner = null;
  private boolean draw = false;

  public void reset() {
    winnner = null;
    draw = false;
  }

  @Override
  public void announceWinner(Mark winner) {
    this.winnner = winner;
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
}
