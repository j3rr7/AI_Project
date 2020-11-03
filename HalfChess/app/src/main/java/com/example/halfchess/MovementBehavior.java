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

//    public class QueenMovement implements  MovementBehavior{
//
//
//    }

//
//    public class BishopMovement implements MovementBehavior{
//
//    }
//
//    public class HorseMovement implements MovementBehavior{
//
//
//    }

}
