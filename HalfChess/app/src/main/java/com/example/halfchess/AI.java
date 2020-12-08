package com.example.halfchess;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AI {
	static class Node{
		Papan[][] papan;
		int value;
		Node(Papan[][] state)
		{
			this.papan = state;
		}
	}
	
	static class AIBehaviour{
		// ToDo Add Ai
		int depth;
		ArrayList<Node> possible_move = new ArrayList<>();

		// ToDo fix this to get all moves from black and white
		public Iterable<MovementBehavior> getAllLegalMoves() {
			List<MovementBehavior> allMoves = new ArrayList<>();
			return Collections.unmodifiableList(allMoves);
		}

		public static float positionEvaluation(Papan[][] papan)
		{
			// ToDo FIX THIS
			// Kalo + putih kemungkinan menang kalo - putih kemungkinan kalah
			float finalScore = 0;
			for(int i=0;i<8;i++)
			{
				for (int j=0;j<4;j++)
				{
					// P1 putih
					if (papan[i][j].getBidak().isP1())
					{
						finalScore += papan[i][j].getBidak().getValue();
					}
					else
					{
						finalScore -= papan[i][j].getBidak().getValue();
					}
				}
			}
			return 0.0f;
		}

		public static int boardEvaluation(Papan[][] papan){
			// ToDo Entah :'v
		}

		public static int alphaBeta(Papan[][] board, int depth, int alpha, int beta, boolean is_max)
		{
			if (depth == 0)
				return boardEvaluation(board);

			int value;
			// ToDo Add legal move check, add move player according spaces, check for bestmove
			if (is_max)
			{
				value = Integer.MIN_VALUE;
			}
			else
			{
				value = Integer.MAX_VALUE;
			}
			// just in case when no move can be made
			if(value == Integer.MIN_VALUE ) value++;
			return value;
		}
	}
}