package com.example.halfchess;

import android.graphics.Color;
import android.widget.Toast;

public interface MovementBehavior {
    public Boolean Pickup(Boolean player1,int x,int y);

    public class PawnMovement implements MovementBehavior{


        @Override
        public Boolean Pickup(Boolean player1, int x, int y) {
            Boolean canMove=false;
            if(player1 && y-1>=0){ //white move up
                if(MainActivity.papan[y-1][x].getBidak()==null){
                    MainActivity.tiles[y-1][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
                //langka awal
                if(y==6 && MainActivity.papan[y-1][x].getBidak()==null){
                    MainActivity.tiles[y-1][x].setBackgroundColor(Color.GREEN);
                    MainActivity.tiles[y-2][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }


                //Makan
                if(x-1 >= 0 && MainActivity.papan[y-1][x-1].getBidak()!=null && !MainActivity.papan[y-1][x-1].getBidak().isP1()){
                    MainActivity.tiles[y-1][x-1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
                if(x+1 <= 3 && MainActivity.papan[y-1][x+1].getBidak()!=null && !MainActivity.papan[y-1][x+1].getBidak().isP1() ){
                    MainActivity.tiles[y-1][x+1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }



            }
            if(!player1 && y+1<=7){ // black move down
                if(MainActivity.papan[y+1][x].getBidak() == null){
                    MainActivity.tiles[y+1][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }

                if(y==1 && MainActivity.papan[y+1][x].getBidak()==null){
                    MainActivity.tiles[y+1][x].setBackgroundColor(Color.GREEN);
                    MainActivity.tiles[y+2][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }

                //Makan
                if(x-1 >= 0 && MainActivity.papan[y+1][x-1].getBidak()!=null && MainActivity.papan[y+1][x-1].getBidak().isP1() ){
                    MainActivity.tiles[y+1][x-1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
                if(x+1 <= 3 && MainActivity.papan[y+1][x+1].getBidak()!=null && MainActivity.papan[y+1][x+1].getBidak().isP1() ){
                    MainActivity.tiles[y+1][x+1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }

            }
            return canMove;
        }

    }
    public class KingMovement implements MovementBehavior{
        @Override
        public Boolean Pickup(Boolean player1, int x, int y) {
            Boolean canMove = false;
            //gerak ke horizontal e tak gabung ambe gerak serong
            // cuma kopas dari koding sg gerak vertikal tak gabung nde if e horizontal


            if(x-1>=0){
                if(MainActivity.papan[y][x-1].getBidak() == null || MainActivity.papan[y][x-1].getBidak().isP1() != player1 ){
                    MainActivity.tiles[y][x-1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }

                //serong
                if(y+1 <=7){
                    if(MainActivity.papan[y+1][x-1].getBidak() == null || MainActivity.papan[y+1][x-1].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y+1][x-1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }
                if(y-1>=0){
                    if(MainActivity.papan[y-1][x-1].getBidak() == null || MainActivity.papan[y-1][x-1].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y-1][x-1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }
                //end serong
            }

            if(x+1 <=3){
                if(MainActivity.papan[y][x+1].getBidak() == null || MainActivity.papan[y][x+1].getBidak().isP1() != player1 ){
                    MainActivity.tiles[y][x+1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }

                //serong
                if(y+1 <=7){
                    if(MainActivity.papan[y+1][x+1].getBidak() == null || MainActivity.papan[y+1][x+1].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y+1][x+1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }
                if(y-1>=0){
                    if(MainActivity.papan[y-1][x+1].getBidak() == null || MainActivity.papan[y-1][x+1].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y-1][x+1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }
                //end serong
            }

            // vertikal
            if(y+1 <=7){
                if(MainActivity.papan[y+1][x].getBidak() == null || MainActivity.papan[y+1][x].getBidak().isP1() != player1 ){
                    MainActivity.tiles[y+1][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
            }

            if(y-1>=0){
                if(MainActivity.papan[y-1][x].getBidak() == null || MainActivity.papan[y-1][x].getBidak().isP1() != player1 ){
                    MainActivity.tiles[y-1][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
            }

            return canMove;
        }
    }

    public class BishopMovement implements MovementBehavior{

        @Override
        public Boolean Pickup(Boolean player1, int x, int y) {
            Boolean canMove = false;
             int tx = x;
             int ty = y;

                Boolean kiriAtas = true;
                Boolean kananAtas = true;
                Boolean kiriBawah = true;
                Boolean kananBawah = true;
                //buat ngecek dee sudah nambrak 1 apa belom

            for(int i=1;i<=4;i++){
                //serong kiri atas
                if(x - i >=0 && y - i >=0 && kiriAtas){
                    if(MainActivity.papan[y-i][x-i].getBidak() == null || MainActivity.papan[y-i][x-i].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y-i][x-i].setBackgroundColor(Color.GREEN);
                        canMove = true;
                        if(MainActivity.papan[y-i][x-i].getBidak() != null){
                            kiriAtas = false;
                            // ben kalo nabrak dee berhenti
                        }
                    }else if(MainActivity.papan[y-i][x-i].getBidak().isP1() == player1 ){
                            kiriAtas = false;
                    }
                }

                //serong kanan bawah
                if(x + i <=3 && y + i <=7 && kananBawah){
                    if(MainActivity.papan[y+i][x+i].getBidak() == null || MainActivity.papan[y+i][x+i].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y+i][x+i].setBackgroundColor(Color.GREEN);
                        canMove = true;
                        if(MainActivity.papan[y+i][x+i].getBidak() != null){
                            kananBawah = false;
                        }
                    }else if(MainActivity.papan[y+i][x+i].getBidak().isP1() == player1){
                        kananBawah = false;
                    }
                }

                //serong Kiri Bawh
                if(x - i >=0 && y + i <=7 && kiriBawah){
                    if(MainActivity.papan[y+i][x-i].getBidak() == null || MainActivity.papan[y+i][x-i].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y+i][x-i].setBackgroundColor(Color.GREEN);
                        canMove = true;
                        if(MainActivity.papan[y+i][x-i].getBidak() != null){
                            kiriBawah = false;
                        }
                    }else if(MainActivity.papan[y+i][x-i].getBidak().isP1() == player1 ){
                            kiriBawah = false;
                    }
                }

                // kanan atas
                if(x + i <=3 && y - i >=0 && kananAtas){
                    if(MainActivity.papan[y-i][x+i].getBidak() == null || MainActivity.papan[y-i][x+i].getBidak().isP1() != player1 ){
                        MainActivity.tiles[y-i][x+i].setBackgroundColor(Color.GREEN);
                        canMove = true;
                        if(MainActivity.papan[y-i][x+i].getBidak() != null){
                            kananAtas = false;
                        }
                    }else if(MainActivity.papan[y-i][x+i].getBidak().isP1() == player1){
                            kananAtas = false;
                    }
                }




            }



            return  canMove;
        }
    }

//    public class QueenMovement implements  MovementBehavior{
//
//
//    }

//
//
//    public class HorseMovement implements MovementBehavior{
//
//
//    }

}
