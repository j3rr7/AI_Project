package com.example.halfchess;

import android.graphics.Color;
import android.widget.Toast;

public interface MarkArea { // panggil pas sebelum dan sesudah gerak
//    public void Mark(Boolean p1, int x, int y);
//    public void SimulationMark(Boolean p1,int x,int y);
//
//    public class PawnArea implements MarkArea {
//
//        @Override
//        public void Mark(Boolean p1, int x, int y) {
//            if (p1) { // move to up
//                if (y - 1 >= 0) {
////                    if (MainActivity.papan[y - 1][x].getBidak() == null) {
////                        MainActivity.markCheck[y - 1][x] = true;
////                    }
//                    if (x - 1 >= 0 ) { // kan dee isa njaga sg ngiri atao kanan
//                        MainActivity.markCheck[y - 1][x - 1] = true;
//                        MainActivity.cekCheck(p1,x-1,y-1);
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markCheck[y - 1][x + 1] = true;
//                        MainActivity.cekCheck(p1,x+1,y-1);
//                    }
//                }
//
//            } else { // black moves down
//                if (y + 1 <= 7) {
//                    if (x - 1 >= 0 ) {
//                        MainActivity.markCheck2[y + 1][x - 1] = true;
//                        MainActivity.cekCheck(p1,x-1,y+1);
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markCheck2[y + 1][x + 1] = true;
//                        MainActivity.cekCheck(p1,x+1,y+1);
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void SimulationMark(Boolean p1, int x, int y) {
//            if (p1) { // move to up
//                if (y - 1 >= 0) {
////
//                    if (x - 1 >= 0 ) { // kan dee isa njaga sg ngiri atao kanan
//                        MainActivity.markSimulation[y - 1][x - 1] = true;
//                        MainActivity.cekCheck(p1,x-1,y-1);
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markSimulation[y - 1][x + 1] = true;
//                        MainActivity.cekCheck(p1,x+1,y-1);
//                    }
//                }
//
//            } else { // black moves down
//                if (y + 1 <= 7) {
//                    if (x - 1 >= 0 ) {
//                        MainActivity.markSimulation[y + 1][x - 1] = true;
//                        MainActivity.cekCheck(p1,x-1,y+1);
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markSimulation[y + 1][x + 1] = true;
//                        MainActivity.cekCheck(p1,x+1,y+1);
//                    }
//                }
//            }
//        }
//    }
//    public class QueenArea implements MarkArea {
//
//        public void setMark(Boolean player1, int y, int x) {
//            if (player1) { //area e player 1
//                MainActivity.markCheck[y][x] = true;
//            } else {
//                MainActivity.markCheck2[y][x] = true;
//            }
//
//        }
//        public void setMarkSimulation(Boolean player1, int y, int x) {
//            if (player1) { //area e player 1
//                MainActivity.markSimulation[y][x] = true;
//            } else {
//                MainActivity.markSimulation[y][x] = true;
//            }
//
//        }
//
//        @Override
//        public void Mark(Boolean player1, int x, int y) { // x dan y dari posisi bidak
//            Boolean kiri = true;
//            Boolean kanan = true;
//            Boolean atas = true;
//            Boolean bawah = true;
//
//            Boolean kananbawah = true; //x+ y+
//            Boolean kiribawah = true; // x- y+
//            Boolean kananatas = true; /// x+ y-
//            Boolean kiriatas = true; // x- y-
//
////            System.out.println("X = " + x + " || Y = " + y + "||" + player1);
//            for (int i = 1; i <= 7; i++) {
//                if (x - i >= 0 && kiri) { // harus e kan berdasar dee dewe , yaampun tolol e cu
//                    if (MainActivity.papan[y][x - i].getBidak() == null || MainActivity.papan[y][x - i].getBidak().isP1() != player1) {
////                        System.out.println("tes kiri");
//                        setMark(player1, y, x - i);
//                        if (MainActivity.papan[y][x - i].getBidak() != null) {
//                            kiri = false;
//                            MainActivity.cekCheck(player1,x-i,y);
//                        }
//                    } else if (MainActivity.papan[y][x - i].getBidak().isP1() == player1) {
//                        setMark(player1, y, x - i);
//                        kiri = false;
//                    }
//                }
//
//                if (x + i <= 3 && kanan) {
//                    if (MainActivity.papan[y][x + i].getBidak() == null || MainActivity.papan[y][x + i].getBidak().isP1() != player1) {
////                        System.out.println("tes kanan");
//                        setMark(player1, y, x + i);
//                        if (MainActivity.papan[y][x + i].getBidak() != null) {
//                            kanan = false;
//                            MainActivity.cekCheck(player1,x+i,y);
//
//                        }
//                    } else if (MainActivity.papan[y][x + i].getBidak().isP1() == player1) {
//                        setMark(player1, y, x + i);
//                        kanan = false;
//                    }
//                }
//
//                if (atas && y - i >= 0) {
//                    if (MainActivity.papan[y - i][x].getBidak() == null || MainActivity.papan[y - i][x].getBidak().isP1() != player1) {
////                        System.out.println("tes atas");
//                        setMark(player1, y - i, x);
//                        if (MainActivity.papan[y - i][x].getBidak() != null) {
//                            atas = false;
//                            MainActivity.cekCheck(player1,x,y-i);
//
//                        }
//                    } else if (MainActivity.papan[y - i][x].getBidak().isP1() == player1) {
//                        setMark(player1, y - i, x);
//                        atas = false;
//                    }
//                }
//
//                if (bawah && y + i <= 7) {
//                    if (MainActivity.papan[y + i][x].getBidak() == null || MainActivity.papan[y + i][x].getBidak().isP1() != player1) {
////                        System.out.println("tes bawah");
//                        setMark(player1, y + i, x);
//                        if (MainActivity.papan[y + i][x].getBidak() != null) {
//                            bawah = false;
//                            MainActivity.cekCheck(player1,x,y+i);
//                        }
//                    } else if (MainActivity.papan[y + i][x].getBidak().isP1() == player1) {
//                        setMark(player1, y + i, x);
//                        bawah = false;
//                    }
//                }
//
//                if (kananbawah && y + i <= 7 && x + i <= 3) {
//                    if (MainActivity.papan[y + i][x + i].getBidak() == null || MainActivity.papan[y + i][x + i].getBidak().isP1() != player1) {
////                        System.out.println("tes kanan bawah");
//                        setMark(player1, y + i, x + i);
//                        if (MainActivity.papan[y + i][x + i].getBidak() != null) {
//                            kananbawah = false;
//                            MainActivity.cekCheck(player1,x+i,y+i);
//
//                        }
//                    } else if (MainActivity.papan[y + i][x + i].getBidak().isP1() == player1) {
//                        setMark(player1, y + i, x + i);
//                        kananbawah = false;
//                    }
//                }
//
//                if (kiribawah && y + i <= 7 && x - i >= 0) {
//                    if (MainActivity.papan[y + i][x - i].getBidak() == null || MainActivity.papan[y + i][x - i].getBidak().isP1() != player1) {
////                        System.out.println("tes kiri bawah");
//                        setMark(player1, y + i, x - i);
//                        if (MainActivity.papan[y + i][x - i].getBidak() != null) {
//                            kiribawah = false;
//                            MainActivity.cekCheck(player1,x-i,y+i);
//
//                        }
//                    } else if (MainActivity.papan[y + i][x - i].getBidak().isP1() == player1) {
//                        setMark(player1, y + i, x - i);
//                        kiribawah = false;
//                    }
//                }
//
//                if (kananatas && y - i >= 0 && x + i <= 3) {
//                    if (MainActivity.papan[y - i][x + i].getBidak() == null || MainActivity.papan[y - i][x + i].getBidak().isP1() != player1) {
////                        System.out.println("tes kanan atas");
//                        setMark(player1, y - i, x + i);
//                        if (MainActivity.papan[y - i][x + i].getBidak() != null) {
//                            kananatas = false;
//                            MainActivity.cekCheck(player1,x+i,y-i);
//
//                        }
//                    } else if (MainActivity.papan[y - i][x + i].getBidak().isP1() == player1) {
//                        setMark(player1, y - i, x + i);
//                        kananatas = false;
//                    }
//                }
//
//                if (kiriatas && y - i >= 0 && x - i >= 0) {
//                    if (MainActivity.papan[y - i][x - i].getBidak() == null || MainActivity.papan[y - i][x - i].getBidak().isP1() != player1) {
////                        System.out.println("tes kiri atasa");
//                        setMark(player1, y - i, x - i);
//                        if (MainActivity.papan[y - i][x - i].getBidak() != null) {
//                            kiriatas = false;
//                            MainActivity.cekCheck(player1,x-i,y-i);
//
//                        }
//                    } else if (MainActivity.papan[y - i][x - i].getBidak().isP1() == player1) {
//                        setMark(player1, y - i, x - i);
//                        kiriatas = false;
//                    }
//                }
//
//
//            }
//        }
//
//        @Override
//        public void SimulationMark(Boolean p1, int x, int y) {
//
//        }
//
//    }
//    public class BishopArea implements MarkArea {
//        public void setMark(Boolean player1, int y, int x) {
//            if (player1) { //area e player 1
//                MainActivity.markCheck[y][x] = true;
//            } else {
//                MainActivity.markCheck2[y][x] = true;
//            }
//
//        }
//
//        @Override
//        public void Mark(Boolean player1, int x, int y) {
//            Boolean kananbawah = true; //x+ y+
//            Boolean kiribawah = true; // x- y+
//            Boolean kananatas = true; /// x+ y-
//            Boolean kiriatas = true; // x- y-
//
//            for (int i = 1; i <= 7; i++) {
////                System.out.println(i + "Bishop");
//                if (kananbawah && y + i <= 7 && x + i <= 3) {
//                        setMark(player1, y + i, x + i);
//                        if (MainActivity.papan[y + i][x + i].getBidak() != null) {
//                            kananbawah = false;
//                            MainActivity.cekCheck(player1,x+i,y+i);
//
//                        }
//
//                }
//
//                if (kiribawah && y + i <= 7 && x - i >= 0) {
//                        setMark(player1, y + i, x - i);
//                        if (MainActivity.papan[y + i][x - i].getBidak() != null) {
//                            kiribawah = false;
//                            MainActivity.cekCheck(player1,x-i,y+i);
//
//                        }
//
//                }
//
////                System.out.println();
////                System.out.println(player1+"Bishop X:"+(x+i) +"  Y:"+(y-i));
//                if (kananatas &&  y - i >= 0 && x + i < 4) {
////                    System.out.println("masuk bis");
//                        setMark(player1, y - i, x + i);
//                        if (MainActivity.papan[y - i][x + i].getBidak() != null) {
//                            kananatas = false;
//                            MainActivity.cekCheck(player1,x+i,y-i);
//
//                        }
//
//                }
//
//                if (kiriatas && y - i >= 0 && x - i >= 0) {
//
//                        setMark(player1, y - i, x - i);
//                        if (MainActivity.papan[y - i][x - i].getBidak() != null) {
//                            kiriatas = false;
//                            MainActivity.cekCheck(player1,x-i,y-i);
//                        }
//                }
//            }
//        }
//
//        @Override
//        public void SimulationMark(Boolean p1, int x, int y) {
//
//        }
//
//    }
//    public class KingArea implements MarkArea{
//
//        @Override
//        public void Mark(Boolean p1, int x, int y) {
//            if(p1){
//                if (y - 1 >= 0) {
//                    MainActivity.markCheck[y - 1][x] = true;
//                    if (x - 1 >= 0 ) {
//                        MainActivity.markCheck[y][x - 1] = true;
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markCheck[y - 1][x + 1] = true;
//                    }
//                }
//                if (x - 1 >= 0 ) {
//                    MainActivity.markCheck[y][x - 1] = true;
//                }
//                if (x + 1 <= 3 ) {
//                    MainActivity.markCheck[y][x + 1] = true;
//                }
//
//                if(y+1 <8){
//                    MainActivity.markCheck[y + 1][x] = true;
//                    if (x - 1 >= 0 ) {
//                        MainActivity.markCheck[y + 1][x - 1] = true;
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markCheck[y + 1][x + 1] = true;
//                    }
//                }
//            }else{
//                if (y - 1 >= 0) {
//                    MainActivity.markCheck2[y - 1][x] = true;
//                    if (x - 1 >= 0 ) {
//                        MainActivity.markCheck2[y][x - 1] = true;
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markCheck2[y - 1][x + 1] = true;
//                    }
//                }
//                if (x - 1 >= 0 ) {
//                    MainActivity.markCheck2[y][x - 1] = true;
//                }
//                if (x + 1 <= 3 ) {
//                    MainActivity.markCheck2[y][x + 1] = true;
//                }
//
//                if(y+1 <8){
//                    MainActivity.markCheck2[y + 1][x] = true;
//                    if (x - 1 >= 0 ) {
//                        MainActivity.markCheck2[y + 1][x - 1] = true;
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markCheck2[y + 1][x + 1] = true;
//                    }
//                }
//            }
//
//
//        }
//
//        @Override
//        public void SimulationMark(Boolean p1, int x, int y) {
//
//        }
//    }
//    public class KnightArea implements  MarkArea{
//
//
//        @Override
//        public void Mark(Boolean p1, int x, int y) {
//            // kiri atas searah jarum jam
//            int[] movx = {-1,+1,+2,+2,+1,-1,-2,-2};
//            int[] movy = {-2,-2,-1,+1,+2,+2,+1,-1};
//            for(int i=0;i<8;i++){
//                if(x+movx[i]>=0 && x+movx[i]<4 && y+movy[i]>=0 && y+movy[i]<8 ){
//                    MainActivity.cekCheck(p1,x+i,y+i);
//                    if(p1){
//                        MainActivity.markCheck[y+movy[i]][x + movx[i]] = true;
//                    }else{
//                        MainActivity.markCheck2[y+movy[i]][x + movx[i]] = true;
//
//                    }
//                }
//            }
//
//        }
//
//        @Override
//        public void SimulationMark(Boolean p1, int x, int y) {
//
//        }
//    }

}