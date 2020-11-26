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

        @Override
        public void Mark(Boolean player1, int x, int y) { // x dan y dari posisi bidak
            Boolean kiri = true;
            Boolean kanan = true;
            Boolean atas = true;
            Boolean bawah = true;
                        System.out.println("X = "+x +" || Y = "+y +"||"+player1);
            for (int i = 1; i <= 7; i++) {
                if (x - i >= 0 && kiri) { // harus e kan berdasar dee dewe , yaampun tolol e cu
                    if (MainActivity.papan[y][x - i].getBidak() == null || MainActivity.papan[y][x - i].getBidak().isP1() != player1) {
                        System.out.println("tes kiri");
                        MainActivity.markCheck[y][x - i] = true; // raja gaole gerak nde kene
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
                        MainActivity.markCheck[y][x + i] = true; // raja gaole gerak nde kene
                        if (MainActivity.papan[y][x + i].getBidak() != null) {
                            kiri = false;
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

            }
        }

    }
}
