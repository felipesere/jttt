package de.fesere.tictactoe.ui;

import java.util.Collections;
import java.util.LinkedList;

public class FakeIO extends IO {

  private String written = "";
  private final LinkedList<Integer> inputs = new LinkedList<>();

  public void reset() {
    written = "";
    inputs.clear();
  }
  @Override
  public void write(String s) {
    written = s;
  }

  @Override
  public Integer readInput() {
    return inputs.remove(0);
  }

  public String getWritten() {
    return written;
  }

  public void setInputs(Integer ... input) {
    Collections.addAll(inputs, input);
  }
}
