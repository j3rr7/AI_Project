package com.example.halfchess;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class AI {
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
}