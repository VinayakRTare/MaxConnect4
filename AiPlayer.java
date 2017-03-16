

import java.util.*;
/***
 * 
 * @author Vinayak Tare
 */


/**
 * 
 * This is the AiPlayer class. It simulates a minimax(alpha beta search) player for the max connect
 * four game. The constructor essentially does nothing.
 * 
 * 
 *
 */

public class AiPlayer {

	public int depthLevel;
	public GameBoard board;

	/**
	 * The constructor essentially does nothing except instantiate an AiPlayer
	 * object.
	 *
	 */
	public AiPlayer() {
		// nothing to do here
		this.depthLevel = 5;
	}

	public AiPlayer(GameBoard currentGame, int depthLevel) {
		super();
		this.board = currentGame;
		this.depthLevel = depthLevel;
	}

	public AiPlayer(int depthLevel) {
		super();
		this.depthLevel = depthLevel;
	}

	public int findBestPlayPlayer2(GameBoard currentGame) {
		Random randy = new Random();
		int playChoice = 99;

		playChoice = randy.nextInt(7);

		while (!currentGame.isValidPlay(playChoice))
			playChoice = randy.nextInt(7);

		// end random play code

		return playChoice;
	}

	/**
	 * This method plays a piece on the board
	 * alpha beta with depth limited search
	 * 
	 * @param currentGame
	 *            The GameBoard object that is currently being used to play the
	 *            game.
	 * @return an integer indicating which column the AiPlayer would like to
	 *         play in.
	 */

	public int findBestPlay(GameBoard currentGame) {
		int playChoice = 0;
		if (currentGame.getCurrentTurn() == 1) {
			int v = Integer.MAX_VALUE;
			for (int i = 0; i < maxconnect4.COLS; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard nextGame = new GameBoard(currentGame.getGameBoard());
					nextGame.playPiece(i);
					int val = MaxValue(nextGame, depthLevel, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if (v > val) {
						playChoice = i;
						v = val;
					}
				}
			}
		} else {
			int v = Integer.MIN_VALUE;
			for (int i = 0; i < maxconnect4.COLS; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard nextGame = new GameBoard(currentGame.getGameBoard());
					nextGame.playPiece(i);
					int val = MinValue(nextGame, depthLevel, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if (v < val) {
						playChoice = i;
						v = val;
					}
				}
			}
		}
		return playChoice;
	}

	public int MaxValue(GameBoard currentGame, int depthLevel, int alpha, int beta) {

		if (currentGame.getPieceCount() < 42 && depthLevel > 0) {
			int v = Integer.MIN_VALUE;
			for (int i = 0; i < maxconnect4.COLS; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
					nextMoveBoard.playPiece(i);
					int val = MinValue(nextMoveBoard, depthLevel - 1, alpha, beta);
					v = Math.max(v, val);
					if (v >= beta) {
						return v;
					}
					alpha = Math.max(v, alpha);
				}
			}
			return v;
		} else {
			return currentGame.getScore(2) - currentGame.getScore(1);
		}
	}

	public int MinValue(GameBoard currentGame, int depthLevel, int alpha, int beta) {

		if (currentGame.getPieceCount() < 42 && depthLevel > 0) {
			int v = Integer.MAX_VALUE;
			for (int i = 0; i < maxconnect4.COLS; i++) {
				if (currentGame.isValidPlay(i)) {
					GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
					nextMoveBoard.playPiece(i);
					int val = MaxValue(nextMoveBoard, depthLevel - 1, alpha, beta);
					v = Math.min(val, v);
					if (v <= alpha) {
						return v;
					}
					beta = Math.min(beta, v);
				}
			}
			return v;
		} else {
			return currentGame.getScore(2) - currentGame.getScore(1);
		}

	}
}