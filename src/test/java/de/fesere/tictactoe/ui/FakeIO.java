package de.fesere.tictactoe.ui;

public class FakeIO extends IO {

  private String written = "";

  public void reset() {
    written = "";
  }
  @Override
  public void write(String s) {
    written = s;
  }

  public String getWritten() {
    return written;
  }
}
