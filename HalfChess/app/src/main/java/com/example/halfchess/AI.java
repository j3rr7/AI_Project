package com.example.halfchess;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AI {
	static class Move{
		private int srcx, srcy, destx, desty;

		public Move(int srcx, int srcy, int destx, int desty) {
			this.srcx = srcx;
			this.srcy = srcy;
			this.destx = destx;
			this.desty = desty;
		}

		public int getSrcx() {
			return srcx;
		}

		public void setSrcx(int srcx) {
			this.srcx = srcx;
		}

		public int getSrcy() {
			return srcy;
		}

		public void setSrcy(int srcy) {
			this.srcy = srcy;
		}

		public int getDestx() {
			return destx;
		}

		public void setDestx(int destx) {
			this.destx = destx;
		}

		public int getDesty() {
			return desty;
		}

		public void setDesty(int desty) {
			this.desty = desty;
		}
	}
	static class AIBehaviour{
		static final float[][] evalPawn = {
				{0.0f,  0.0f,  0.0f, 0.0f},
				{5.0f,  5.0f,  5.0f, 5.0f},
				{1.0f,  1.0f,  1.0f, 1.0f},
				{0.5f,  0.5f,  0.5f, 0.5f},
				{0.0f,  0.0f,  0.0f, 0.0f},
				{0.5f,  0.5f,  0.5f, 0.5f},
				{0.5f,  1.0f,  1.0f, 0.5f},
				{0.0f,  0.0f,  0.0f, 0.0f}
		};
		static final float[][] evalKnight = {
				{-5.0f, -4.0f, -4.0f, -5.0f},
				{-4.0f, -2.0f, -2.0f, -4.0f},
				{-3.0f,  0.0f,  0.0f, -3.0f},
				{-3.0f,  0.5f,  0.5f, -3.0f},
				{-3.0f,  0.0f,  0.0f, -3.0f},
				{-3.0f,  0.5f,  0.5f, -3.0f},
				{-4.0f, -2.0f, -2.0f, -4.0f},
				{-5.0f, -4.0f, -4.0f, -5.0f}
		};
		static final float[][] evalBishop = {
				{-2.0f, -1.0f, -1.0f, -2.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  0.5f,  0.5f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  1.0f,  1.0f, -1.0f},
				{-1.0f,  0.5f,  0.5f, -1.0f},
				{-2.0f, -1.0f, -1.0f, -2.0f}
		};
		static final float[][] evalQueen = {
				{-2.0f, -1.0f, -1.0f, -2.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-0.5f,  0.0f,  0.0f, -0.5f},
				{ 0.0f,  0.0f,  0.0f, -0.5f},
				{-1.0f,  0.5f,  0.0f, -1.0f},
				{-1.0f,  0.0f,  0.0f, -1.0f},
				{-2.0f, -1.0f, -1.0f, -2.0f}
		};
		static final float[][] evalKing = {
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
			// Pawn = 10 + isWhite ? evalPawn[x][y] : reverseArray(evalPawn)[x][y];
			if (piece.getValue() == 1)
			{
				return  (int)(10 + (piece.isWhite() ? evalPawn[x][y] : reverseArray(evalPawn)[x][y]));
			}
			// Bishop = 30 + isWhite ? evalBishop[x][y] : reverseArray(evalBishop)[x][y];
			if (piece.getValue() == 2)
			{
				return  (int)(30 + (piece.isWhite() ? evalKnight[x][y] : reverseArray(evalKnight)[x][y]));
			}
			// Knight = 30 + isWhite ? evalKnight[x][y] : reverseArray(evalKnight)[x][y];
			if (piece.getValue() == 3)
			{
				return  (int)(30 + (piece.isWhite() ? evalKnight[x][y] : reverseArray(evalKnight)[x][y]));
			}
			// Queen = 90 + isWhite ? evalQueen[x][y] : reverseArray(evalQueen)[x][y];
			if (piece.getValue() == 4)
			{
				return  (int)(90 + (piece.isWhite() ? evalQueen[x][y] : reverseArray(evalQueen)[x][y]));
			}
			// King = 900 + isWhite ? evalKing[x][y] : reverseArray(evalKing)[x][y];
			if (piece.getValue() == 5)
			{
				return  (int)(900 + (piece.isWhite() ? evalKing[x][y] : reverseArray(evalKing)[x][y]));
			}
			return 0;
		}

		public static int getPieceValue(Bidak piece, int x, int y){
			if (piece == null)
				return 0;
			return piece.isWhite() ? getAbsoluteValue(piece, piece.isWhite(), x, y) : -getAbsoluteValue(piece, piece.isWhite() , x, y);
		}

		public static float evaluateBoard(Papan[][] board , int x, int y){
			float boardValue = 0.0f;
			for (int i=0;i<8;i++)
			{
				for (int j=0;j<4;j++)
				{
					boardValue += (float)getPieceValue(board[i][j].getBidak(), x, y);
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
					//Todo remove unused code
				}
			}
			return 0;
		}



		static boolean isSave(int baris, int kolom,Papan[][] papan, boolean turnP1){
			if(turnP1){
				//pawn
				if(baris - 1 >= 0 && kolom-1 >= 0 && papan[baris-1][kolom-1].getBidak().getValue() == 1 && papan[baris-1][kolom-1].getBidak().isWhite() != turnP1){
					return false;
				}
				if(baris - 1 >= 0 && kolom+1 <= 3 && papan[baris-1][kolom+1].getBidak().getValue() == 1 && papan[baris-1][kolom+1].getBidak().isWhite() != turnP1){
					return false;
				}
			}else{
				if(baris + 1 <= 7 && kolom-1 >= 0 && papan[baris + 1][kolom - 1].getBidak().getValue() == 1 && papan[baris+1][kolom-1].getBidak().isWhite() != turnP1){
					return false;
				}
				if(baris + 1 <= 7 && kolom+1 <= 3 && papan[baris + 1][kolom + 1].getBidak().getValue() == 1 && papan[baris+1][kolom+1].getBidak().isWhite() != turnP1){
					return false;
				}
			}//bishop & queen(diagonal)
			//kanan bawah
			for (int i = baris + 1,j = kolom + 1; i < 8 && j < 4; i++, j++) {
				if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
					return false;
				}else if(papan[i][j].getBidak().getValue() != 0){
					if(papan[i][j].getBidak().getValue() != 5)break;
				}
			}
			//kanan atas
			for (int i = baris - 1,j = kolom + 1; i >= 0 && j < 4; i--, j++) {
				if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
					return false;
				}else if(papan[i][j].getBidak().getValue() != 0){
					if(papan[i][j].getBidak().getValue() != 5)break;
				}
			}
			//kiri atas
			for (int i = baris - 1,j = kolom - 1; i >= 0 && j >= 0; i--, j--) {
				if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
					return false;
				}else if(papan[i][j].getBidak().getValue() != 0) {
					if(papan[i][j].getBidak().getValue() != 5)break;
				}
			}
			//kiri bawah
			for (int i = baris + 1,j = kolom - 1; i < 8 && j >= 0; i++, j--) {
				if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
					return false;
				}else if(papan[i][j].getBidak().getValue() != 0) {
					if(papan[i][j].getBidak().getValue() != 5)break;
				}
			}
			//knight
			if(baris - 2 >= 0 && kolom - 1  >= 0){
				if(papan[baris - 2][kolom - 1].getBidak().getValue() == 3 && papan[baris - 2][kolom - 1].getBidak().isWhite() != turnP1){
					return false;
				}
			}if(baris - 2 >= 0 && kolom + 1  <= 3){
				if(papan[baris - 2][kolom + 1].getBidak().getValue() == 3 && papan[baris - 2][kolom + 1].getBidak().isWhite() != turnP1){
					return false;
				}
			}if(baris + 2 <= 7 && kolom - 1  >= 0){
				if(papan[baris + 2][kolom - 1].getBidak().getValue() == 3 && papan[baris + 2][kolom - 1].getBidak().isWhite() != turnP1){
					return false;
				}
			}if(baris + 2 <= 7 && kolom + 1  <= 3){
				if(papan[baris + 2][kolom + 1].getBidak().getValue() == 3 && papan[baris + 2][kolom + 1].getBidak().isWhite() != turnP1){
					return false;
				}
			}if(baris - 1 >= 0 && kolom + 2  <= 3){
				if(papan[baris - 1][kolom + 2].getBidak().getValue() == 3 && papan[baris - 1][kolom + 2].getBidak().isWhite() != turnP1){
					return false;
				}
			}if(baris - 1 >= 0 && kolom - 2  >= 0){
				if(papan[baris - 1][kolom - 2].getBidak().getValue() == 3 && papan[baris - 1][kolom - 2].getBidak().isWhite() != turnP1){
					return false;
				}
			}if(baris + 1 <= 7 && kolom - 2  >= 0){
				if(papan[baris + 1][kolom - 2].getBidak().getValue() == 3 && papan[baris + 1][kolom - 2].getBidak().isWhite() != turnP1){
					return false;
				}
			}if(baris + 1 <= 7 && kolom + 2  <= 3){
				if(papan[baris + 1][kolom + 2].getBidak().getValue() == 3 && papan[baris + 1][kolom + 2].getBidak().isWhite() != turnP1){
					return false;
				}
			}
			//queen
			for (int i = baris; i < 8; i++) {
				if(papan[i][kolom].getBidak().getValue() == 4 && papan[i][kolom].getBidak().isWhite() !=  papan[baris][kolom].getBidak().isWhite()){
					return false;
				}
				else if(papan[i][kolom].getBidak().getValue() != 0) {
					if(papan[i][kolom].getBidak().getValue() != 5) break;
				}
			}
			for (int i = baris; i >= 0; i--) {
				if(papan[i][kolom].getBidak().getValue() == 4 && papan[i][kolom].getBidak().isWhite() !=  papan[baris][kolom].getBidak().isWhite()){
					return false;
				}
				else if(papan[i][kolom].getBidak().getValue() != 0 ) {
					if(papan[i][kolom].getBidak().getValue() != 5) break;
				}
			}
			for (int i = kolom; i < 4; i++) {
				if(papan[baris][i].getBidak().getValue() == 4 && papan[baris][i].getBidak().isWhite() != papan[baris][kolom].getBidak().isWhite()){
					return false;
				}
				else if(papan[baris][i].getBidak().getValue() != 0) {
					if(papan[baris][i].getBidak().getValue() != 5) break;
				}
			}

			for (int i = kolom; i >= 0; i--) {
				if(papan[baris][i].getBidak().getValue() == 4 && papan[baris][i].getBidak().isWhite() !=  papan[baris][kolom].getBidak().isWhite()){
					return false;
				}
				else if(papan[baris][i].getBidak().getValue() != 0) {
					if(papan[baris][i].getBidak().getValue() != 5) break;
				}
			}

			//king
			for (int i = baris-1; i < baris+2; i++) {
				for (int j = kolom-1; j < kolom+2; j++) {
					if(i <= 7 && i >= 0 && j <= 3 && j >= 0 && (baris != i || kolom != j)){
						if(papan[i][j].getBidak().getValue() == 5 && papan[i][j].getBidak().isWhite() != turnP1){
							return false;
						}
					}
				}
			}
			return true;
		}
		public static Boolean isValid(int i, int j,Papan[][] papan, int baris, int kolom, boolean turnP1){
			Papan[][] tempPapan = new Papan[8][4];
			// Mbuat papan bersih baru
			for (int k = 0; k < 8; k++) {
				for (int l = 0; l < 4; l++) {
					tempPapan[k][l] = new Papan(new Bidak(papan[k][l].getBidak().getValue(),papan[k][l].getBidak().isWhite()),papan[k][l].letak,"#FFFFFF");
				}
			}

			tempPapan[i][j].enPassant = tempPapan[baris][kolom].enPassant;
			tempPapan[i][j].setBidak(new Bidak(tempPapan[baris][kolom].getBidak().getValue(),tempPapan[baris][kolom].getBidak().isWhite()));
			if(tempPapan[i][j].getBidak().getValue() == 1 && Math.abs(baris-i) == 2){
				tempPapan[i][j].enPassant = true;
			}
			else {
				tempPapan[i][j].enPassant = false;
				for (int k = 0; k < 8; k++) {
					for (int l = 0; l < 4; l++) {
						tempPapan[k][l].enPassant = false;
					}
				}
			}
			tempPapan[baris][kolom].setBidak(new Bidak(0,false));
			tempPapan[i][j].untouched = false;
			tempPapan[baris][kolom].untouched = false;
			for (int k = 0; k < 8; k++) {
				for (int l = 0; l < 4; l++) {
					if(tempPapan[k][l].getBidak().getValue() == 5 && tempPapan[k][l].getBidak().isWhite() == turnP1){
						if(!isSave(k,l,tempPapan,turnP1)){
//                        Toast.makeText(this, "Invalid Move", Toast.LENGTH_SHORT).show();
							return false;
						}
						break;
					}
				}
			}
			return true;

		}
		public static void canMove(int x, int y, Papan[][] papan, boolean turnP1){
			System.out.println("Can Move" + x +" - "+y );
			int baris = y;
			int kolom = x;
			//king
			if(papan[baris][kolom].getBidak().getValue() == 5){
				for (int i = baris-1; i < baris+2; i++) {
					for (int j = kolom-1; j < kolom+2; j++) {
						if(i <= 7 && i >= 0 && j <= 3 && j >= 0 && (baris != i || kolom != j)){
							if(papan[i][j].getBidak().getValue() == 0){
								if(isValid(i,j,papan,baris,kolom, turnP1)){
									papan[i][j].setStatus(1);
								}
							}else if((!turnP1 && papan[i][j].getBidak().isWhite()) || (turnP1 && !papan[i][j].getBidak().isWhite())){
								if(isValid(i,j,papan,baris,kolom,turnP1)){
									papan[i][j].setStatus(1);
								}
							}
						}
					}
				}
			} // King
			else if(papan[baris][kolom].getBidak().getValue() == 4){
				Boolean kiriAtas = true;
				Boolean kananAtas = true;
				Boolean kiriBawah = true;
				Boolean kananBawah = true;
				//buat ngecek dee sudah nambrak 1 apa belom

				for(int i=1;i<=7;i++){
					//serong kiri atas
					// i pergerakan y  -> baris +- i
					// j pergerakan x  -> kolom +- i
					if(kolom - i >=0 && baris - i >=0 && kiriAtas){
						if(papan[baris-i][kolom-i].getBidak().getValue() == 0
								|| papan[baris-i][kolom-i].getBidak().isWhite() != turnP1  ){

							if(isValid(baris-i,kolom-i,papan,baris,kolom,turnP1)){
								papan[baris-i][kolom-i].setStatus(1);
								if(papan[baris-i][kolom-i].getBidak().getValue() != 0){
									kiriAtas = false;
									// ben kalo nabrak dee berhenti
								}
							}

						}else if(papan[baris-i][kolom-i].getBidak().isWhite() == turnP1 ){
							kiriAtas = false;
						}
					}

					//serong kanan bawah
					if(kolom + i <=3 && baris + i <=7 && kananBawah){
						if(papan[baris+i][kolom+i].getBidak().getValue() == 0
								|| papan[baris+i][kolom+i].getBidak().isWhite() != turnP1 ){
							if(isValid(baris+i,kolom+i,papan,baris,kolom,turnP1)){
								papan[baris+i][kolom+i].setStatus(1);
								if(papan[baris+i][kolom+i].getBidak().getValue() != 0){
									kananBawah = false;
								}
							}
						}else if(papan[baris+i][kolom+i].getBidak().isWhite() == turnP1){
							kananBawah = false;
						}
					}

					//serong Kiri Bawh
					if(kolom - i >=0 && baris + i <=7 && kiriBawah){
						if(papan[baris+i][kolom-i].getBidak().getValue() == 0
								|| papan[baris+i][kolom-i].getBidak().isWhite() != turnP1 ){
							if(isValid(baris+i,kolom-i,papan,baris,kolom,turnP1)){
								papan[baris+i][kolom-i].setStatus(1);
								if(papan[baris+i][kolom-i].getBidak().getValue() != 0){
									kiriBawah = false;
								}
							}
						}else if(papan[baris+i][kolom-i].getBidak().isWhite() == turnP1 ){
							kiriBawah = false;
						}
					}

					// kanan atas
					if(kolom + i <=3 && baris - i >=0 && kananAtas){
						if(papan[baris-i][kolom+i].getBidak().getValue() == 0
								|| papan[baris-i][kolom+i].getBidak().isWhite() != turnP1 ){

							if(isValid(baris-i,kolom+i,papan,baris,kolom,turnP1)){
								papan[baris-i][kolom+i].setStatus(1);
								if(papan[baris-i][kolom+i].getBidak().getValue() != 0){
									kananAtas = false;
								}
							}
						}else if(papan[baris-i][kolom+i].getBidak().isWhite() == turnP1){
							kananAtas = false;
						}
					}
				}

				Boolean kiri = true;
				Boolean kanan = true;
				Boolean atas = true;
				Boolean bawah = true;
				// vertikal horizontal
				for (int i=1;i<=7;i++){
					if(kolom-i >= 0 && kiri){
						if(papan[baris][kolom-i].getBidak().getValue() == 0 || papan[baris][kolom-i].getBidak().isWhite() != turnP1 ){
							if(isValid(baris,kolom-i,papan,baris,kolom,turnP1)){
								papan[baris][kolom-i].setStatus(1);

								if(papan[baris][kolom-i].getBidak().getValue() != 0){
									kiri = false;
								}
							}
						}else if(papan[baris][kolom-i].getBidak().isWhite() ==turnP1){
							kiri = false;
						}
					}

					if(kolom+i <= 3 && kanan){
						if(papan[baris][kolom+i].getBidak().getValue() == 0 || papan[baris][kolom+i].getBidak().isWhite() != turnP1 ){
							if(isValid(baris,kolom+i,papan,baris,kolom,turnP1)){
								papan[baris][kolom+i].setStatus(1);

								if(papan[baris][kolom+i].getBidak().getValue() != 0){
									kanan = false;
								}
							}
						}else if(papan[baris][kolom+i].getBidak().isWhite() == turnP1){
							kanan = false;
						}
					}

					if(baris-i >= 0 && atas){
						if(papan[baris-i][kolom].getBidak().getValue() == 0 || papan[baris-i][kolom].getBidak().isWhite() !=turnP1 ){
							if(isValid(baris-i,kolom,papan,baris,kolom,turnP1)){
								papan[baris-i][kolom].setStatus(1);
								if(papan[baris-i][kolom].getBidak().getValue() != 0){
									atas = false;
								}
							}
						}else if(papan[baris-i][kolom].getBidak().isWhite() == turnP1){
							atas = false;
						}
					}

					if(baris+i <= 7 && bawah){
						if(papan[baris+i][kolom].getBidak().getValue() == 0 || papan[baris+i][kolom].getBidak().isWhite() != turnP1 ){
							if(isValid(baris+i,kolom,papan,baris,kolom,turnP1)){
								papan[baris+i][kolom].setStatus(1);
								if(papan[baris+i][kolom].getBidak().getValue() != 0){
									bawah = false;
								}
							}
						}else if(papan[baris+i][kolom].getBidak().isWhite() == turnP1){
							bawah = false;
						}
					}

				}
			} //Queen
			else if(papan[baris][kolom].getBidak().getValue() == 3){
				//knight
				int[] moveX = new int[]{-1,1,2,2,1,-1,-2,-2};
				int[] moveY = new int[]{-2,-2,-1,1,2,2,1,-1};
				for(int i=0;i<8;i++){
					if(x+moveX[i]>=0 && y+moveY[i]>=0 && x+moveX[i]<=3 && y+moveY[i]<=7){
						if(papan[y+moveY[i]][x+moveX[i]].getBidak().getValue()==0 || papan[y+moveY[i]][x+moveX[i]].getBidak().isWhite() != turnP1 ){
							if(isValid(y+moveY[i],x+moveX[i],papan,baris,kolom,turnP1)){
								papan[y+moveY[i]][x+moveX[i]].setStatus(1);
							}
						}
					}
				}
			} //Knight
			else if(papan[baris][kolom].getBidak().getValue() == 2){
				//bishop
				Boolean kiriAtas = true;
				Boolean kananAtas = true;
				Boolean kiriBawah = true;
				Boolean kananBawah = true;
				//buat ngecek dee sudah nambrak 1 apa belom

				for(int i=1;i<=7;i++){
					//serong kiri atas
					// i pergerakan y  -> baris +- i
					// j pergerakan x  -> kolom +- i
					if(kolom - i >=0 && baris - i >=0 && kiriAtas){
						if(papan[baris-i][kolom-i].getBidak().getValue() == 0
								|| papan[baris-i][kolom-i].getBidak().isWhite() != turnP1  ){

							if(isValid(baris-i,kolom-i,papan,baris,kolom,turnP1)){
								papan[baris-i][kolom-i].setStatus(1);
								if(papan[baris-i][kolom-i].getBidak().getValue() != 0){
									kiriAtas = false;
									// ben kalo nabrak dee berhenti
								}
							}

						}else if(papan[baris-i][kolom-i].getBidak().isWhite() == turnP1 ){
							kiriAtas = false;
						}
					}

					//serong kanan bawah
					if(kolom + i <=3 && baris + i <=7 && kananBawah){
						if(papan[baris+i][kolom+i].getBidak().getValue() == 0
								|| papan[baris+i][kolom+i].getBidak().isWhite() != turnP1 ){
							if(isValid(baris+i,kolom+i,papan,baris,kolom,turnP1)){
								papan[baris+i][kolom+i].setStatus(1);
								if(papan[baris+i][kolom+i].getBidak().getValue() != 0){
									kananBawah = false;
								}
							}
						}else if(papan[baris+i][kolom+i].getBidak().isWhite() == turnP1){
							kananBawah = false;
						}
					}

					//serong Kiri Bawh
					if(kolom - i >=0 && baris + i <=7 && kiriBawah){
						if(papan[baris+i][kolom-i].getBidak().getValue() == 0
								|| papan[baris+i][kolom-i].getBidak().isWhite() != turnP1 ){
							if(isValid(baris+i,kolom-i,papan,baris,kolom,turnP1)){
								papan[baris+i][kolom-i].setStatus(1);
								if(papan[baris+i][kolom-i].getBidak().getValue() != 0){
									kiriBawah = false;
								}
							}
						}else if(papan[baris+i][kolom-i].getBidak().isWhite() == turnP1 ){
							kiriBawah = false;
						}
					}

					// kanan atas
					if(kolom + i <=3 && baris - i >=0 && kananAtas){
						if(papan[baris-i][kolom+i].getBidak().getValue() == 0
								|| papan[baris-i][kolom+i].getBidak().isWhite() != turnP1 ){

							if(isValid(baris-i,kolom+i,papan,baris,kolom,turnP1)){
								papan[baris-i][kolom+i].setStatus(1);
								if(papan[baris-i][kolom+i].getBidak().getValue() != 0){
									kananAtas = false;
								}
							}
						}else if(papan[baris-i][kolom+i].getBidak().isWhite() == turnP1){
							kananAtas = false;
						}
					}
				}

			} //Bishop
			else if(papan[baris][kolom].getBidak().getValue() == 1){
//            Toast.makeText(this, "Pawn", Toast.LENGTH_SHORT).show();
				//pawn
				if(turnP1 && y-1>=0){ //white move up
					if(papan[y-1][x].getBidak().getValue()==0){
//                    isValid(baris-1,kolom,papan,baris,kolom)
						if (isValid(y-1,kolom,papan,baris,kolom,turnP1)){
							papan[y-1][x].setStatus(1);
							//;
						}

					}
					//langka awal
					if(y==6 && papan[y-1][x].getBidak().getValue()==0){
//                    isValid(y-1,kolom,papan,baris,kolom)
						if(isValid(y-1,x,papan,baris,kolom,turnP1)){
							papan[y-1][x].setStatus(1);
							//;
						}
						if(papan[y-2][x].getBidak().getValue()==0){
							if(isValid(y-2,x,papan,baris,kolom,turnP1)){
								papan[y-2][x].setStatus(1);
								//;

							}
						}
					}
					//Makan
					if(x-1 >= 0 && papan[y-1][x-1].getBidak().getValue()!=0 && !papan[y-1][x-1].getBidak().isWhite()){
						if(isValid(y-1,x-1,papan,baris,kolom,turnP1)){
							papan[y-1][x-1].setStatus(1);
							//;
						}
					}
					if(x+1 <= 3 && papan[y-1][x+1].getBidak().getValue()!=0 && !papan[y-1][x+1].getBidak().isWhite() ){
						if(isValid(y-1,x+1,papan,baris,kolom,turnP1)){
							papan[y-1][x+1].setStatus(1);
							//;
						}
					}

				}
				if(!turnP1 && y+1<=7){ // black move down
					if(papan[y+1][x].getBidak().getValue() == 0){
						if(isValid(y+1,x,papan,baris,kolom,turnP1)){
							papan[y+1][x].setStatus(1);
							//;
						}
					}

					if(y==1 && papan[y+1][x].getBidak().getValue()==0){
						if(isValid(y+1,x,papan,baris,kolom,turnP1)){
							papan[y+1][x].setStatus(1);
							//;
						}
						if(papan[y+2][x].getBidak().getValue()==0){
							if(isValid(y+2,x,papan,baris,kolom,turnP1)){
								papan[y+2][x].setStatus(1);
								//;
							}
						}
					}

					//Makan
					if(x-1 >= 0 && papan[y+1][x-1].getBidak().getValue()!=0 && papan[y+1][x-1].getBidak().isWhite() ){
						if(isValid(y+1,x-1,papan,baris,kolom,turnP1)){
							papan[y+1][x-1].setStatus(1);
							//;
						}
					}
					if(x+1 <= 3 && papan[y+1][x+1].getBidak().getValue()!=0 && papan[y+1][x+1].getBidak().isWhite() ){
						if(isValid(y+1,x+1,papan,baris,kolom,turnP1)){
							papan[y+1][x+1].setStatus(1);
							//;
						}
					}
				}
			} // Pawn
		}

		public static ArrayList<Move> getAllPossibleMove(Papan[][] board, boolean turnP1){
			ArrayList<Move> possibleMove = new ArrayList<>();
			for (int i=0;i<8;i++) {
				for (int j=0;j<4;j++) {
					if (board[i][j].getBidak().getValue() != 0) {
						if (!turnP1)
						{
							// Panggil Manual disini

						}
					}
				}
			}
			return possibleMove;
		}

		public static void minimaxRoot(Papan[][] board, int x, int y, int depth, boolean isMaximizingPlayer)
		{
			// ToDo insert ArrayListMove here
			ArrayList<Move> listMove = new ArrayList<>();
			listMove = getAllPossibleMove(board, board[x][y].getBidak().isWhite());
			int bestMoveValue = -99999; // Big negative value
			Move bestMove = null;

			// ToDo Enumerate ArrayListMove
			for(int i=0;i<listMove.size();i++)
			{

			}
			// ToDo Enumerate Move in ArrayListMove
			float value =  minimax(board, depth - 1, -10000, 10000, !isMaximizingPlayer);
			// ToDo Undo Move
			if(value >= bestMoveValue) {
				bestMoveValue = (int)Math.round(value);
				// bestMoveFound = newGameMove;
			}
			// return Move
		}

		public static float minimax(Papan[][] board, int depth, int alpha, int beta, boolean isMaximizingPlayer)
		{
			int bestMoveValue;
			if (depth == 0)
			{
				//return -evaluateBoard(board);
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