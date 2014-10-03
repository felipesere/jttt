package de.fesere.tictactoe.ui;

import java.io.*;
import java.util.function.Supplier;

public class IO {

  private final BufferedReader stream;
  private final PrintStream output;

  public IO() {
    this(System.in, System.out);
  }

  public IO(InputStream stream, PrintStream output) {
    this.stream = new BufferedReader(new InputStreamReader(stream));
    this.output = output;
  }

  public Supplier<Integer> readInput(Announcer announcer) {
    return () -> {
      announcer.announce();
      return readInput();
    };
  }

  public Integer readInput() {
    try {
      return Integer.parseInt(stream.readLine());
    } catch (NumberFormatException e) {
      return null;
    } catch (IOException e) {
      throw new RuntimeException("Error trying to read from input", e);
    }
  }

  public void write(String s) {
    output.println(s);
  }

  public interface Announcer {
    void announce();
  }
}
