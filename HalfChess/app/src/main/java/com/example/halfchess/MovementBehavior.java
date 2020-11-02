package com.example.halfchess;

import android.graphics.Color;
import android.widget.Toast;

public interface MovementBehavior {
    public Boolean Pickup(Boolean player1,int x,int y);
    public void Replace(Boolean player1,int x,int y);

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
                if(x-1 >= 0 && MainActivity.papan[y-1][x-1].getBidak()!=null ){
                        MainActivity.tiles[y-1][x-1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
                if(x+1 <= 3 && MainActivity.papan[y-1][x+1].getBidak()!=null ){
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
                if(x-1 >= 0 && MainActivity.papan[y+1][x-1].getBidak()!=null ){
                    MainActivity.tiles[y+1][x-1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
                if(x+1 <= 3 && MainActivity.papan[y+1][x+1].getBidak()!=null ){
                    MainActivity.tiles[y+1][x+1].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }

            }
            return canMove;
        }

        @Override
        public void Replace(Boolean player1, int x, int y) {

        }
    }

//    public class QueenMovement implements  MovementBehavior{
//
//
//    }

//    public class KingMovement implements MovementBehavior{
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
