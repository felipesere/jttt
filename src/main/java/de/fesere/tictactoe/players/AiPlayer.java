package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Mark;
import de.fesere.tictactoe.Player;

public class AiPlayer implements Player {
  private final Mark mark;
  private AiPlayer opponent;

  public static AiPlayer createAi(Mark mark) {
    AiPlayer self = new AiPlayer(mark);
    AiPlayer other = new AiPlayer(mark.opponent());
    self.opponent = other;
    other.opponent = self;
    return self;
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
    int best_score = -Integer.MAX_VALUE;
    for(int move : board.getPossibleMoves()) {
      Board newBoard = board.nextBoardFor(move, mark);
      int score = -opponent.alpha_beta(newBoard, -Integer.MAX_VALUE, Integer.MAX_VALUE, mark);
      if(score > best_score) {
        best_move = move;
        best_score = score;
      }
    }
    return best_move;
  }

  private int alpha_beta(Board board, int alpha, int beta, Mark mark) {
    if(board.isFinished()) {
      if(mark == this.mark) {
        return board.getScore();
      } else {
        return -board.getScore();
      }
    }
    else {
      return calculateBestScore(board, alpha, beta);
    }
  }

  private int calculateBestScore(Board board, int alpha, int beta) {
    int score;
    for(int move : board.getPossibleMoves() ) {
      Board newBoard = board.nextBoardFor(move, mark);
      score = -opponent.alpha_beta(newBoard, -beta, -alpha, mark);
      alpha = Math.max(alpha, score);
      if(alpha >= beta) {
        break;
      }
    }
    return alpha;
  }
}
