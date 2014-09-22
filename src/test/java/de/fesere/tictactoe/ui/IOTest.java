package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.ui.IO;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IOTest {

  ByteArrayOutputStream baos;

  @Test
  public void returnsFoundIntegerInStream() {
    InputStream input = getInput("1");
    IO io = new IO(input, getOutput());
    assertThat(io.readInput(), is(1));
  }

  @Test
  public void returnsNullIfNoIntegerFound() {
    InputStream input = getInput("foo");
    IO io = new IO(input, getOutput());
    assertThat(io.readInput(), nullValue());
  }

  private ByteArrayInputStream getInput(String input) {
    return new ByteArrayInputStream(input.getBytes(UTF_8));
  }

  @Test
  public void writesToStream() {
   IO io = new IO(getInput(""), getOutput());
   io.write("wat");
   assertThat(output(), is("wat"));
  }

  private String output() {
    return baos.toString().trim();
  }

  private PrintStream getOutput() {
    baos = new ByteArrayOutputStream();
    return new PrintStream(baos);
  }

}