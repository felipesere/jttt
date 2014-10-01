package de.fesere.tictactoe.ui;

class Template {
  private String template = "[1][2][3]\n[4][5][6]\n[7][8][9]\n";

  public void add(int location, String symbol) {
    template = template.replaceFirst(""+location, symbol);
  }

  public String get() {
    return template;
  }
}
