package de.fesere.tictactoe.ui;

class Template {
  private String template;
  private int sideSize;

  public Template(int sideSize) {
    this.sideSize = sideSize;
    template = consructTemplate();
  }

  public void add(int location, String symbol) {
    template = template.replaceFirst(""+location, symbol);
  }

  public String get() {
    return template;
  }

  private String consructTemplate() {
    String result = "";
    for(int i=1; i <= this.sideSize*this.sideSize;i++) {
      result += createCell(i);
      if(isEndOfRow(i)) {
        result += "\n";
      }
    }
    return result;
  }

  private String createCell(int i) {
    return "["+i+"]";
  }

  private boolean isEndOfRow(int i) {
    return i % this.sideSize == 0;
  }
}
