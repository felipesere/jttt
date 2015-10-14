package de.fesere.tictactoe.ui;

class Template {
  private String template = " [1] [2] [3] [4]\n [5] [6] [7] [8]\n [9][10][11][12]\n[13][14][15][16]\n";

  public void add(int location, String symbol) {
    template = template.replaceFirst(""+location, symbol);
  }

  public String get() {
    return template;
  }
}
