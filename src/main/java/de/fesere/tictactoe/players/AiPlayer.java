package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.Player;

public class AiPlayer implements Player {
  private Mark mark;

  private final static int MIN_VALUE = -10;
  private final static int MAX_VALUE =  10;

  public static AiPlayer createAi(Mark mark) {
    return new AiPlayer(mark);
  }

  private AiPlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Board performMove(Board board) {
    int move = selectMove(board);
    return board.nextBoardFor(move, mark);
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  private int selectMove(Board board) {
    int best_move = 0;
    int best_score = MIN_VALUE;
    for (int move : board.getPossibleMoves()) {
      Board newBoard = board.nextBoardFor(move, mark);
      int score = -alpha_beta(newBoard, MIN_VALUE, MAX_VALUE, mark.opponent());
      if (score > best_score) {
        best_move = move;
        best_score = score;
      }
    }
    return best_move;
  }

  private int alpha_beta(Board board, int alpha, int beta, Mark player) {
    if (board.isFinished()) {
      return valueOfBoard(board, player);
    } else {
      return calculateBestScore(board, alpha, beta, player);
    }
  }

  private int valueOfBoard(Board board, Mark player) {
    if (board.isWinner(player)) {
      return board.getScore();
    } else {
      return -board.getScore();
    }
  }

  private int calculateBestScore(Board board, int alpha, int beta, Mark player) {
    int score = 0;
    for (int move : board.getPossibleMoves()) {
      Board newBoard = board.nextBoardFor(move, player);
      score = -alpha_beta(newBoard, -beta, -alpha, player.opponent());
      alpha = Math.max(alpha, score);
      if (alpha >= beta) {
        break;
      }
    }
    return score;
  }
}
