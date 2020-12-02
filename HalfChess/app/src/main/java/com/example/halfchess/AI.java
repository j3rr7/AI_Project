package com.example.halfchess;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

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
		ArrayList<Node> possible_move = new ArrayList<>();

		public static int boardEvaluation(Papan[][] papan){
			int black = 0;
			int white = 0;

			for(int i=0;i<8;i++)
			{
				for (int j=0;j<4;j++)
				{
					// ToDo Add Black & White Check Here
				}
			}
			return 0;
		}

		@RequiresApi(api = Build.VERSION_CODES.N)
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