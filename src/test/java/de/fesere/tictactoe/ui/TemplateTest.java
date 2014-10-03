package de.fesere.tictactoe.ui;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TemplateTest {

  @Test
  public void drawEmpty3x3() {
    Template template = new Template(3);
    assertThat(template.get(), is("[1][2][3]\n[4][5][6]\n[7][8][9]\n"));
  }

  @Test
  public void drawEmpty4x4() {
    Template template = new Template(4);
    assertThat(template.get(), is("[1][2][3][4]\n[5][6][7][8]\n[9][10][11][12]\n[13][14][15][16]\n"));
  }
}