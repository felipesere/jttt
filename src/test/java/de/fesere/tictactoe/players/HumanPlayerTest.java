package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.Player;

public class HumanPlayerTest extends PlayerCommonTest {


  @Override
  Player playerForCommonTests() {
    return new HumanPlayer(Mark.O, 3);
  }


}