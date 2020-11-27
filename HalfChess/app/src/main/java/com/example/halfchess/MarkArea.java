package com.example.halfchess;

import android.graphics.Color;
import android.widget.Toast;

public interface MarkArea { // panggil pas sebelum dan sesudah gerak

    public void Mark(Boolean p1, int x, int y);

    public class PawnArea implements MarkArea {

        @Override
        public void Mark(Boolean p1, int x, int y) {
            if (p1) { // move to up
                if (y - 1 >= 0) {
                    if (MainActivity.papan[y - 1][x].getBidak() == null) {
                        MainActivity.markCheck[y - 1][x] = true;
                    }
                    if (x - 1 >= 0 && MainActivity.papan[y - 1][x - 1].getBidak() == null) {
                        MainActivity.markCheck[y - 1][x - 1] = true;
                    }
                    if (x + 1 <= 3 && MainActivity.papan[y - 1][x + 1].getBidak() == null) {
                        MainActivity.markCheck[y - 1][x + 1] = true;
                    }
                }

            } else { // black moves down
                if (y + 1 <= 7) {
                    if (MainActivity.papan[y + 1][x].getBidak() == null) {
                        MainActivity.markCheck[y + 1][x] = true;
                    }
                    if (x - 1 >= 0 && MainActivity.papan[y + 1][x - 1].getBidak() == null) {
                        MainActivity.markCheck[y + 1][x - 1] = true;
                    }
                    if (x + 1 <= 3 && MainActivity.papan[y + 1][x + 1].getBidak() == null) {
                        MainActivity.markCheck[y + 1][x + 1] = true;
                    }
                }
            }
        }
    }
    public class QueenArea implements MarkArea {

        public void setMark(Boolean player1,int y,int x){
            if(player1){ //area e player 1
                MainActivity.markCheck[y][x] = true;
            }else{
                MainActivity.markCheck2[y][x] = true;
            }

        }
        @Override
        public void Mark(Boolean player1, int x, int y) { // x dan y dari posisi bidak
            Boolean kiri = true;
            Boolean kanan = true;
            Boolean atas = true;
            Boolean bawah = true;

            Boolean kananbawah = true; //x+ y+
            Boolean kiribawah = true; // x- y+
            Boolean kananatas = true; /// x+ y-
            Boolean kiriatas = true; // x- y-

                        System.out.println("X = "+x +" || Y = "+y +"||"+player1);
            for (int i = 1; i <= 7; i++) {
                if (x - i >= 0 && kiri) { // harus e kan berdasar dee dewe , yaampun tolol e cu
                    if (MainActivity.papan[y][x - i].getBidak() == null || MainActivity.papan[y][x - i].getBidak().isP1() != player1) {
                        System.out.println("tes kiri");
                        setMark(player1,y,x-i);
                        if (MainActivity.papan[y][x - i].getBidak() != null) {
                            kiri = false;
                            if (MainActivity.papan[y][x - i].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p2.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y][x - i].getBidak().isP1() == player1) {
                        kiri = false;
                    }
                }

                if (x + i <= 3 && kanan) {
                    if (MainActivity.papan[y][x + i].getBidak() == null || MainActivity.papan[y][x + i].getBidak().isP1() != player1) {
                        System.out.println("tes kanan");
                        setMark(player1,y,x+i);
                        if (MainActivity.papan[y][x + i].getBidak() != null) {
                                kanan = false;
                            if (MainActivity.papan[y][x + i].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p1.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y][x + i].getBidak().isP1() == player1) {
                        kanan = false;
                    }
                }

                if(atas && y - i >=0){
                    if (MainActivity.papan[y-i][x].getBidak() == null || MainActivity.papan[y-i][x].getBidak().isP1() != player1) {
                        System.out.println("tes atas");
                        setMark(player1,y-i,x);
                        if (MainActivity.papan[y-i][x].getBidak() != null) {
                            atas = false;
                            if (MainActivity.papan[y-i][x].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p1.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y-i][x].getBidak().isP1() == player1) {
                        atas = false;
                    }
                }

                if(bawah && y + i <=7){
                    if (MainActivity.papan[y+i][x].getBidak() == null || MainActivity.papan[y+i][x].getBidak().isP1() != player1) {
                        System.out.println("tes bawah");
                        setMark(player1,y+i,x);
                        if (MainActivity.papan[y+i][x].getBidak() != null) {
                            bawah = false;
                            if (MainActivity.papan[y+i][x].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p1.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y+i][x].getBidak().isP1() == player1) {
                        bawah = false;
                    }
                }

                if(kananbawah && y + i <=7 && x+i<=3){
                    if (MainActivity.papan[y+i][x+i].getBidak() == null || MainActivity.papan[y+i][x+i].getBidak().isP1() != player1) {
                        System.out.println("tes kanan bawah");
                        setMark(player1,y+i,x+i);
                        if (MainActivity.papan[y+i][x+i].getBidak() != null) {
                            kananbawah = false;
                            if (MainActivity.papan[y+i][x+i].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p1.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y+i][x+i].getBidak().isP1() == player1) {
                        kananbawah = false;
                    }
                }

                if(kiribawah && y + i <=7 && x-i>=0){
                    if (MainActivity.papan[y+i][x-i].getBidak() == null || MainActivity.papan[y+i][x-i].getBidak().isP1() != player1) {
                        System.out.println("tes kiri bawah");
                        setMark(player1,y+i,x-i);
                        if (MainActivity.papan[y+i][x-i].getBidak() != null) {
                            kiribawah = false;
                            if (MainActivity.papan[y+i][x-i].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p1.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y+i][x-i].getBidak().isP1() == player1) {
                        kiribawah
                                = false;
                    }
                }

                if(kananatas && y - i >=0 && x+i<=3){
                    if (MainActivity.papan[y-i][x+i].getBidak() == null || MainActivity.papan[y-i][x+i].getBidak().isP1() != player1) {
                        System.out.println("tes kanan atas");
                        setMark(player1,y-i,x+i);
                        if (MainActivity.papan[y-i][x+i].getBidak() != null) {
                            kananatas= false;
                            if (MainActivity.papan[y-i][x+i].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p1.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y-i][x+i].getBidak().isP1() == player1) {
                        kananatas = false;
                    }
                }

                if(kiriatas && y - i >=0 && x-i>=0){
                    if (MainActivity.papan[y-i][-+i].getBidak() == null || MainActivity.papan[y-i][x-i].getBidak().isP1() != player1) {
                        System.out.println("tes kiri atasa");
                        setMark(player1,y-i,x-i);
                        if (MainActivity.papan[y-i][x-i].getBidak() != null) {
                            kiriatas= false;
                            if (MainActivity.papan[y-i][x-i].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")) {
                                if (player1) {
                                    MainActivity.p2.setChecked(true);
                                } else {
                                    MainActivity.p1.setChecked(true);
                                }
                                System.out.println("checkkkkk");
                            }
                        }
                    } else if (MainActivity.papan[y-i][x-i].getBidak().isP1() == player1) {
                        kiriatas = false;
                    }
                }



            }
        }

    }

}
