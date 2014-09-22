package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Player;

import static de.fesere.tictactoe.Mark.O;
import static de.fesere.tictactoe.Mark.X;

public class PlayerFactory {

  public static boolean validPlayerChoice(int choice) {
   return choice > 0 && choice < 5;
  }

  public static Player[] getPlayers(int choice) {
    if(choice == 1) {
      return new Player[] {new HumanPlayer(X), AiPlayer.createAi(O)};
    }
    if(choice == 2) {
      return new Player[] {AiPlayer.createAi(O), new HumanPlayer(X)};
    }
    if(choice == 3) {
      return new Player[] {AiPlayer.createAi(X), AiPlayer.createAi(O)};
    }
    return new Player[] {new HumanPlayer(X), new HumanPlayer(O)};
  }
}
