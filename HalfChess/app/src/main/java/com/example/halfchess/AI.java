package com.example.halfchess;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AI {
<<<<<<< Updated upstream
//	static class Node{
//		Papan[][] papan;
//
//		Node parent; // NULL if ROOT
//		ArrayList<Node> possible_children = new ArrayList<>();
//
//		// VALUE ===================
//		int value; // untuk A/B pruning
//		public int getValue() {
//			return value;
//		}
//		public void setValue(int value) {
//			this.value = value;
//		}
//		// ===================
//
//		// Depth/Ply ===================
//		int depth;
//		// ===================
//
//		Node(Papan[][] state, Node parent)
//		{
//			this.papan = state;
//			this.value = 0;
//		}
//	}
//
//	static class AIBehaviour{
//		List<Papan[][]> all_possible_move = new ArrayList<>();
//
//		public static int boardEvaluation(Papan[][] papan){
//			int black = 0;
//			int white = 0;
//			for(int i=0;i<8;i++)
//			{
//				for (int j=0;j<4;j++)
//				{
//					// ToDo Add Black & White Check Here
//					if (papan[i][j].getBidak().isP1())
//					{
//						black += papan[i][j].getBidak().getValue();
//					}
//					else
//					{
//						white += papan[i][j].getBidak().getValue();
//					}
//				}
//			}
//			return (black - white); // if positive black winning, if negative white winning
//		}
//
//		@RequiresApi(api = Build.VERSION_CODES.N)
//		public static int minimax(Papan[][] board, int depth, int alpha, int beta, boolean ismaximizingPlayer) {
//			if (depth == 0)
//				return boardEvaluation(board);
//
//			int value;
//			// ToDo Add legal move check, add move player according spaces, check for bestmove
//			if (ismaximizingPlayer)
//			{
//				value = Integer.MIN_VALUE;
//			}
//			else
//			{
//				value = Integer.MAX_VALUE;
//			}
//			// just in case when no move can be made
//			if(value == Integer.MIN_VALUE ) value++;
//			return value;
//		}
//	}
=======
	static class Node{
		Papan[][] papan;
		Node parent;
		Node(Papan[][] state, Node parent)
		{
			this.papan = state;
		}
	}
	
	static class AIBehaviour{
		final float[][] evalPawn = {
				{0.0f,  0.0f,  0.0f, 0.0f},
				{5.0f,  5.0f,  5.0f, 5.0f},
				{1.0f,  1.0f,  1.0f, 1.0f},
				{0.5f,  0.5f,  0.5f, 0.5f},
				{0.0f,  0.0f,  0.0f, 0.0f},
				{0.5f,  0.5f,  0.5f, 0.5f},
				{0.5f,  1.0f,  1.0f, 0.5f},
				{0.0f,  0.0f,  0.0f, 0.0f}
		};
		final float[][] evalKnight = {
				{-5.0f, -4.0f, -4.0f, -5.0f},
				{-4.0f, -2.0f, -2.0f, -4.0f},
				{-3.0f,  0.0f,  0.0f, -3.0f},
				{-3.0f,  0.5f,  0.5f, -3.0f},
				{-3.0f,  0.0f,  0.0f, -3.0f},
				{-3.0f,  0.5f,  0.5f, -3.0f},
				{-4.0f, -2.0f, -2.0f, -4.0f},
				{-5.0f, -4.0f, -4.0f, -5.0f}
		};
		final float[][] evalBishop = {
				{-2.0f, -1.0f, -1.0f, -2.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  0.5f,  0.5f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  1.0f,  1.0f, -1.0f},
				{-1.0f,  0.5f,  0.5f, -1.0f},
				{-2.0f, -1.0f, -1.0f, -2.0f}
		};
		final float[][] evalQueen = {
				{-2.0f, -1.0f, -1.0f, -2.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-0.5f,  0.0f,  0.0f, -0.5f},
				{ 0.0f,  0.0f,  0.0f, -0.5f},
				{-1.0f,  0.5f,  0.0f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-2.0f, -1.0f, -1.0f, -2.0f}
		};
		final float[][] evalKing = {
				{-3.0f, -4.0f, -4.0f, -3.0f},
				{-3.0f, -4.0f, -4.0f, -3.0f},
				{-3.0f, -4.0f, -4.0f, -3.0f},
				{-3.0f, -4.0f, -4.0f, -3.0f},
				{-2.0f, -3.0f, -3.0f, -2.0f},
				{-1.0f, -2.0f, -2.0f, -1.0f},
				{ 2.0f,  2.0f,  2.0f,  2.0f},
				{ 2.0f,  3.0f,  3.0f,  2.0f}
		};

		public static float[][] reverseArray(final float[][] array)
		{
			float[][] ar = new float[array.length][];
			int len, index;
			for (int i=0; i < array.length; i++) {
				len = array[i].length;
				ar[i] = new float[len];
				index = len-1;
				for (int j=0; j < len; j++) {
					ar[i][j] = array[i][index];
					index--;
				}
			}
			return ar;
		}

		public static int getAbsoluteValue(Bidak piece, boolean isWhite, int x, int y)
		{
			// ToDo add check what Bidak is this
			// Pawn = 10 + isWhite ? evalPawn[x][y] : reverseArray(evalPawn)[x][y];
			// Knight = 30 + isWhite ? evalKnight[x][y] : reverseArray(evalKnight)[x][y];
			// Bishop = 30 + isWhite ? evalBishop[x][y] : reverseArray(evalBishop)[x][y];
			// Queen = 90 + isWhite ? evalQueen[x][y] : reverseArray(evalQueen)[x][y];
			// King = 900 + isWhite ? evalKing[x][y] : reverseArray(evalKing)[x][y];
			return 0;
		}

		public static int getPieceValue(Bidak piece, int x, int y){
			if (piece == null)
				return 0;
			// ToDo add Check isWhite
			return isWhite ? getAbsoluteValue(piece, /* ToDo Check is White */false , x, y) : -getAbsoluteValue(piece, /* ToDo Check is White */false , x, y);

		}

		public static float evaluateBoard(Papan[][] board){
			float boardValue = 0.0f;
			for (int i=0;i<8;i++)
			{
				for (int j=0;j<4;j++)
				{
					boardValue += (float)getPieceValue(/*GET BIDAK*/)
				}
			}
		}

		public static float BoardEvaluation(Papan[][] papan){
			/*
			* Board Evaluation Function
			* f(p) = 200(K-K')
			*        + 9(Q-Q')
			*        + 5(R-R')
			*        + 3(B-B' + N-N')
			*        + 1(P-P')
			*        - 0.5(D-D' + S-S' + I-I')
			*        + 0.1(M-M') + ...
			* KQRBNP = number of kings, queens, rooks, bishops, knights and pawns
			* D,S,I = doubled, blocked and isolated pawns
			* M = Mobility (the number of legal moves)
			* OR
			* materialScore = kingWt  * (wK-bK)
			*               + queenWt * (wQ-bQ)
			*               + rookWt  * (wR-bR)
			*               + knightWt* (wN-bN)
			*               + bishopWt* (wB-bB)
			*               + pawnWt  * (wP-bP)
			* mobilityScore = mobilityWt * (wMobility-bMobility)
			* Eval  = (materialScore + mobilityScore) * who2Move
			* or just go with it :D .. jere was here
			* */

			for (int i=0;i<8;i++)
			{
				for (int j=0;j<4;j++)
				{
					//Todo Get piece value
				}
			}
			return 0;
		}

		public static void minimaxRoot(Papan[][] board, int depth, boolean isMaximizingPlayer)
		{
			// ToDo insert ArrayListMove here
			int bestMove = -99999; // Big negative value
			//boolean bestMoveFound = false;

			// ToDo Enumerate ArrayListMove
			// ToDo Enumerate Move in ArrayListMove
			float value =  minimax(board, depth - 1, -10000, 10000, !isMaximizingPlayer);
			// ToDo Undo Move
			if(value >= bestMove) {
				bestMove = (int)Math.round(value);
				// bestMoveFound = newGameMove;
			}
			// return Move
		}

		public static float minimax(Papan[][] board, int depth, int alpha, int beta, boolean isMaximizingPlayer)
		{
			int bestMoveValue;
			if (depth == 0)
			{
				return -evaluateBoard(board);
			}

			// Todo add arraylist containing all available move
			// ArrayList Here

			if (isMaximizingPlayer)
			{
				bestMoveValue = -99999; // Big negative value
				// ToDo get another move from ArrayListMove and count bestMove
				// using this Collections.max();
				// bestMove = Math.max(bestMove, minimax(depth - 1, game, alpha, beta, !isMaximisingPlayer));
				// alpha = Math.max(alpha, bestMove);
				// if (beta <= alpha) {
				//	return bestMove;
				// }
				// return bestMove
			}
			else
			{
				bestMoveValue = 99999; // Big positive value
				// ToDo get another move from ArrayListMove and count bestMove
				// using this Collections.max();
				// bestMove = Math.min(bestMove, minimax(depth - 1, game, alpha, beta, !isMaximisingPlayer));
				// beta = Math.min(beta, bestMove);
				// if (beta <= alpha) {
				//	return bestMove;
				// }
				// return bestMove
			}
			return bestMoveValue;
		}
	}
>>>>>>> Stashed changes
}