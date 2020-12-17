package com.example.halfchess;

public interface MarkSimulation {
//    public Boolean Mark(Boolean p1, int x, int y);
//
//    public class PawnSimulation implements MarkSimulation {
//        @Override
//        public Boolean Mark(Boolean p1, int x, int y) { // x y posisi dari bidak sekarang
//            System.out.println("Pawn Mark Simulation");
//            Boolean check = false;
//            if (p1) { // move to up
//                if (y - 1 >= 0) {
////                    if (MainActivity.papanSimulation[y - 1][x].getBidak() == null) {
////                        MainActivity.markCheck[y - 1][x] = true;
////                    }
//                    if (x - 1 >= 0 ) { // kan dee isa njaga sg ngiri atao kanan
//                        MainActivity.markSimulation[y - 1][x - 1] = true;
//                        check = MainActivity.cekCheckSimulation(p1,x-1,y-1);
//                        if (check) return check;
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markSimulation[y - 1][x + 1] = true;
//                        check = MainActivity.cekCheckSimulation(p1,x+1,y-1);
//                        if (check ) return check;
//                    }
//                }
//
//            } else { // black moves down
//                if (y + 1 <= 7) {
//                    if (x - 1 >= 0 ) {
//                        MainActivity.markCheck2[y + 1][x - 1] = true;
//                        check = MainActivity.cekCheckSimulation(p1,x-1,y+1);
//                        if (check) return check;
//                    }
//                    if (x + 1 <= 3 ) {
//                        MainActivity.markCheck2[y + 1][x + 1] = true;
//                        check = MainActivity.cekCheckSimulation(p1,x+1,y+1);
//                        if(check) return  check;
//                    }
//                }
//            }
//            return check;
//        }
//    }
//    public class BishopSimulation implements MarkSimulation {
//        public void setMark(Boolean player1, int y, int x) {
//            if (player1) { //area e player 1
//                MainActivity.markSimulation[y][x] = true;
//            } else {
//                MainActivity.markSimulation2[y][x] = true;
//            }
//
//        }
//
//
//        @Override
//        public Boolean Mark(Boolean player1, int x, int y) {
//
//            Boolean kananbawah = true; //x+ y+
//            Boolean kiribawah = true; // x- y+
//            Boolean kananatas = true; /// x+ y-
//            Boolean kiriatas = true; // x- y-
//            Boolean check = false;
//
//            for (int i = 1; i <= 7; i++) {
////                System.out.println(i + "Bishop");
//                if (kananbawah && y + i <= 7 && x + i <= 3) {
//                    setMark(player1, y + i, x + i);
//                    if (MainActivity.papanSimulation[y + i][x + i].getBidak() != null) {
//                        kananbawah = false;
//                        check = MainActivity.cekCheckSimulation(player1, x + i, y + i);
//                        if (check){
//                            return check;
//                        }
//
//                    }
//
//                }
//
//                if (kiribawah && y + i <= 7 && x - i >= 0) {
//                    setMark(player1, y + i, x - i);
//                    if (MainActivity.papanSimulation[y + i][x - i].getBidak() != null) {
//                        kiribawah = false;
//                        check = MainActivity.cekCheckSimulation(player1, x - i, y + i);
//                        if(check) return  check;
//                    }
//
//                }
//
////                System.out.println();
////                System.out.println(player1+"Bishop X:"+(x+i) +"  Y:"+(y-i));
//                if (kananatas && y - i >= 0 && x + i < 4) {
////                    System.out.println("masuk bis");
//                    setMark(player1, y - i, x + i);
//                    if (MainActivity.papanSimulation[y - i][x + i].getBidak() != null) {
//                        kananatas = false;
//                        check = MainActivity.cekCheckSimulation(player1, x + i, y - i);
//                        if(check) return check;
//                    }
//
//                }
//
//                if (kiriatas && y - i >= 0 && x - i >= 0) {
//
//                    setMark(player1, y - i, x - i);
//                    if (MainActivity.papanSimulation[y - i][x - i].getBidak() != null) {
//                        kiriatas = false;
//                        check = MainActivity.cekCheckSimulation(player1, x - i, y - i);
//                        if (check) return check;
//                    }
//                }
//            }
//            return false;
//        }
//    }
//    public class QueenSimulation implements MarkSimulation {
//
//        public void setMark(Boolean player1, int y, int x) {
//            if (player1) { //area e player 1
//                MainActivity.markSimulation[y][x] = true;
//            } else {
//                MainActivity.markSimulation2[y][x] = true;
//            }
//
//        }
//
//        @Override
//        public Boolean Mark(Boolean player1, int x, int y) { // x dan y dari posisi bidak
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
//            System.out.println(MainActivity.papan[y][x].getBidak().getClass().getSimpleName());
//
//            for (int i = 1; i <= 7; i++) {
//                System.out.println("Simulation Mark QUeen");
//                if (x - i >= 0 && kiri) { // harus e kan berdasar dee dewe , yaampun tolol e cu
//                    if (MainActivity.papanSimulation[y][x - i].getBidak() == null || MainActivity.papanSimulation[y][x - i].getBidak().isP1() != player1) {
//                        System.out.println("tes kiri SImulation");
//                        setMark(player1, y, x - i);
//                        if (MainActivity.papanSimulation[y][x - i].getBidak() != null) {
//                            kiri = false;
//                            if(MainActivity.cekCheckSimulation(player1,x-i,y)) return true;
//                        }
//                    } else if (MainActivity.papanSimulation[y][x - i].getBidak().isP1() == player1) {
//                        setMark(player1, y, x - i);
//                        kiri = false;
//                    }
//                }
//
//                if (x + i <= 3 && kanan) {
//                    if (MainActivity.papanSimulation[y][x + i].getBidak() == null || MainActivity.papanSimulation[y][x + i].getBidak().isP1() != player1) {
////                        System.out.println("tes kanan");
//                        setMark(player1, y, x + i);
//                        if (MainActivity.papanSimulation[y][x + i].getBidak() != null) {
//                            kanan = false;
//                            if(MainActivity.cekCheckSimulation(player1,x+i,y)) return true;
//
//                        }
//                    } else if (MainActivity.papanSimulation[y][x + i].getBidak().isP1() == player1) {
//                        setMark(player1, y, x + i);
//                        kanan = false;
//                    }
//                }
//
//                if (atas && y - i >= 0) {
//                    if (MainActivity.papanSimulation[y - i][x].getBidak() == null || MainActivity.papanSimulation[y - i][x].getBidak().isP1() != player1) {
////                        System.out.println("tes atas");
//                        setMark(player1, y - i, x);
//                        if (MainActivity.papanSimulation[y - i][x].getBidak() != null) {
//                            atas = false;
//                            if(MainActivity.cekCheckSimulation(player1,x,y-i)) return true;
//
//                        }
//                    } else if (MainActivity.papanSimulation[y - i][x].getBidak().isP1() == player1) {
//                        setMark(player1, y - i, x);
//                        atas = false;
//                    }
//                }
//
//                if (bawah && y + i <= 7) {
//                    if (MainActivity.papanSimulation[y + i][x].getBidak() == null || MainActivity.papanSimulation[y + i][x].getBidak().isP1() != player1) {
////                        System.out.println("tes bawah");
//                        setMark(player1, y + i, x);
//                        if (MainActivity.papanSimulation[y + i][x].getBidak() != null) {
//                            bawah = false;
//                            if(MainActivity.cekCheckSimulation(player1,x,y+i)) return true;
//                        }
//                    } else if (MainActivity.papanSimulation[y + i][x].getBidak().isP1() == player1) {
//                        setMark(player1, y + i, x);
//                        bawah = false;
//                    }
//                }
//
//                if (kananbawah && y + i <= 7 && x + i <= 3) {
//                    if (MainActivity.papanSimulation[y + i][x + i].getBidak() == null || MainActivity.papanSimulation[y + i][x + i].getBidak().isP1() != player1) {
////                        System.out.println("tes kanan bawah");
//                        setMark(player1, y + i, x + i);
//                        if (MainActivity.papanSimulation[y + i][x + i].getBidak() != null) {
//                            kananbawah = false;
//                            if(MainActivity.cekCheckSimulation(player1,x+i,y+i)) return true;
//
//                        }
//                    } else if (MainActivity.papanSimulation[y + i][x + i].getBidak().isP1() == player1) {
//                        setMark(player1, y + i, x + i);
//                        kananbawah = false;
//                    }
//                }
//
//                if (kiribawah && y + i <= 7 && x - i >= 0) {
//                    if (MainActivity.papanSimulation[y + i][x - i].getBidak() == null || MainActivity.papanSimulation[y + i][x - i].getBidak().isP1() != player1) {
////                        System.out.println("tes kiri bawah");
//                        setMark(player1, y + i, x - i);
//                        if (MainActivity.papanSimulation[y + i][x - i].getBidak() != null) {
//                            kiribawah = false;
//                             if(MainActivity.cekCheckSimulation(player1,x-i,y+i)) return true;
//
//                        }
//                    } else if (MainActivity.papanSimulation[y + i][x - i].getBidak().isP1() == player1) {
//                        setMark(player1, y + i, x - i);
//                        kiribawah = false;
//                    }
//                }
//
//                if (kananatas && y - i >= 0 && x + i <= 3) {
//                    if (MainActivity.papanSimulation[y - i][x + i].getBidak() == null || MainActivity.papanSimulation[y - i][x + i].getBidak().isP1() != player1) {
////                        System.out.println("tes kanan atas");
//                        setMark(player1, y - i, x + i);
//                        if (MainActivity.papanSimulation[y - i][x + i].getBidak() != null) {
//                            kananatas = false;
//                            if(MainActivity.cekCheckSimulation(player1,x+i,y-i)) return true;
//
//                        }
//                    } else if (MainActivity.papanSimulation[y - i][x + i].getBidak().isP1() == player1) {
//                        setMark(player1, y - i, x + i);
//                        kananatas = false;
//                    }
//                }
//
//                if (kiriatas && y - i >= 0 && x - i >= 0) {
//                    if (MainActivity.papanSimulation[y - i][x - i].getBidak() == null || MainActivity.papanSimulation[y - i][x - i].getBidak().isP1() != player1) {
////                        System.out.println("tes kiri atasa");
//                        setMark(player1, y - i, x - i);
//                        if (MainActivity.papanSimulation[y - i][x - i].getBidak() != null) {
//                            kiriatas = false;
//                            if(MainActivity.cekCheckSimulation(player1,x-i,y-i)) return true;
//
//                        }
//                    } else if (MainActivity.papanSimulation[y - i][x - i].getBidak().isP1() == player1) {
//                        setMark(player1, y - i, x - i);
//                        kiriatas = false;
//                    }
//                }
//
//
//            }
//            System.out.println(MainActivity.papan[y][x].getBidak().getClass().getSimpleName());
//            return false;
//        }
//    }
//    public class KingSimulation implements MarkSimulation {
//
//        @Override
//        public Boolean Mark(Boolean p1, int x, int y) { // gamungkin se raja ngeskak raja
//            // nde main mark iku raja gabakal iso ndeketin raja e lwan
//            System.out.println("King Simulation : "+p1);
//            return false;
//        }
//
//    }
//    public class KnightSimulation implements  MarkSimulation{
//
//
//        @Override
//        public Boolean Mark(Boolean p1, int x, int y) {
//            Boolean check = false;
//            int[] movx = {-1,+1,+2,+2,+1,-1,-2,-2};
//            int[] movy = {-2,-2,-1,+1,+2,+2,+1,-1};
//            for(int i=0;i<8;i++){
//                if(x+movx[i]>=0 && x+movx[i]<4 && y+movy[i]>=0 && y+movy[i]<8 ){
//                    if(p1){
//                        MainActivity.markSimulation[y+movy[i]][x + movx[i]] = true;
//                    }else{
//                        MainActivity.markSimulation2[y+movy[i]][x + movx[i]] = true;
//
//                    }
//                    check = MainActivity.cekCheckSimulation(p1,x+i,y+i);
//                    if(check) return check;
//                }
//            }
//            return check;
//        }
//    }
}
