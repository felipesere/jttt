package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.PlayerMark;
import de.fesere.tictactoe.Player;

import java.util.Collections;
import java.util.List;

public class AiPlayer implements Player {

  private PlayerMark mark;

  public AiPlayer(PlayerMark mark) {
    this.mark = mark;
  }

  @Override
  public Board performMove(Board board) {
    int bestMove = bestMove(board);
    return board.nextBoardFor(bestMove, mark);
  }

  private int bestMove(Board board) {
    int bestMove = -1;
    int bestScore = -10;
    for(int move : shuffledMoves(board)) {
      Board newBoard = board.nextBoardFor(move, mark);
      int score = -negamax(newBoard,bestScore, 10, mark.opponent());

      if(score > bestScore) {
        bestScore = score;
        bestMove = move;
      }
    }
    return bestMove;
  }

  private int negamax(Board board, int alpha, int beta, PlayerMark mark) {
    if (board.isFinished()) {
      return valueOfBoard(board, mark);
    } else {
      return scoreUnfinishedBoard(board, alpha, beta, mark);
    }
  }

  private int scoreUnfinishedBoard(Board board, int alpha, int beta, PlayerMark mark) {
    int bestScore = alpha;
    for(int move : shuffledMoves(board)) {
      Board newBoard = board.nextBoardFor(move, mark);
      int score = -negamax(newBoard, -beta, -alpha, mark.opponent());

      if(score > bestScore) {
        bestScore = score;
      }

      alpha = Math.max(score, alpha);
      if(alpha >= beta) {
        break;
      }
    }
    return bestScore;
  }

  private int valueOfBoard(Board board, PlayerMark mark) {
    if (board.isWinner(mark)) {
      return board.getScore();
    } else {
      return -board.getScore();
    }
  }

  private List<Integer> shuffledMoves(Board board) {
    List<Integer> result = board.getPossibleMoves();
    Collections.shuffle(result);
    return result;
  }

  @Override
  public PlayerMark getMark() {
    return mark;
  }
}
