package com.example.halfchess;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AI {
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

		public static int getAbsoluteValue(Bidak piece, boolean isWhite)
		{
			// ToDo add check what Bidak is this
			// Pawn = 10 + isWhite ? evalPawn[x][y] : reverseArray(evalPawn)[x][y];
			// Knight = 30 + isWhite ? evalKnight[x][y] : reverseArray(evalKnight)[x][y];
			// Bishop = 30 + isWhite ? evalBishop[x][y] : reverseArray(evalBishop)[x][y];
			// Queen = 90 + isWhite ? evalQueen[x][y] : reverseArray(evalQueen)[x][y];
			// King = 900 + isWhite ? evalKing[x][y] : reverseArray(evalKing)[x][y];
			return 0;
		}

		public static int getPieceValue(Bidak piece){
			if (piece == null)
				return 0;
			// ToDo add Check isWhite
			return piece.isWhite() ? getAbsoluteValue(piece, piece.isWhite()) : -getAbsoluteValue(piece, piece.isWhite() );

		}

		public static float evaluateBoard(Papan[][] board ){
			float boardValue = 0.0f;
			for (int i=0;i<8;i++)
			{
				for (int j=0;j<4;j++)
				{
					boardValue += (float)getPieceValue(board[i][j].getBidak());
				}
			}
			return  boardValue;
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

}