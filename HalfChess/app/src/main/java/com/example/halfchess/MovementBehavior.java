package com.example.halfchess;

import android.graphics.Color;
import android.hardware.camera2.params.MandatoryStreamCombination;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface MovementBehavior {
    public Boolean Pickup(Boolean player1,int x,int y);
    public ArrayList<Papan[][]> getAllPossibleMove(Bidak currentBidak, boolean isBot);

    public class PawnMovement implements MovementBehavior{
        @Override
        public Boolean Pickup(Boolean player1, int x, int y) {
            Boolean canMove=false;
            if(player1 && y-1>=0){ //white move up
                if(MainActivity.papan[y-1][x].getBidak()==null){
                    if (MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-1)){
                        MainActivity.tiles[y-1][x].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }

                }
                //langka awal
                if(y==6 && MainActivity.papan[y-1][x].getBidak()==null){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-1)){
                        MainActivity.tiles[y-1][x].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                    if(MainActivity.papan[y-2][x].getBidak()==null){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-2)){
                            MainActivity.tiles[y-2][x].setBackgroundColor(Color.GREEN);
                            canMove = true;

                        }
                    }
                }
                //Makan
                if(x-1 >= 0 && MainActivity.papan[y-1][x-1].getBidak()!=null && !MainActivity.papan[y-1][x-1].getBidak().isP1()){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-1,y-1)){
                        MainActivity.tiles[y-1][x-1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }
                if(x+1 <= 3 && MainActivity.papan[y-1][x+1].getBidak()!=null && !MainActivity.papan[y-1][x+1].getBidak().isP1() ){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+1,y-1)){
                        MainActivity.tiles[y-1][x+1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }

            }
            if(!player1 && y+1<=7){ // black move down
                if(MainActivity.papan[y+1][x].getBidak() == null){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+1)){
                        MainActivity.tiles[y+1][x].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }

                if(y==1 && MainActivity.papan[y+1][x].getBidak()==null){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+1)){
                        MainActivity.tiles[y+1][x].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                    if(MainActivity.papan[y+2][x].getBidak()==null){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+1)){
                            MainActivity.tiles[y+2][x].setBackgroundColor(Color.GREEN);
                            canMove = true;
                        }
                    }
                }

                //Makan
                if(x-1 >= 0 && MainActivity.papan[y+1][x-1].getBidak()!=null && MainActivity.papan[y+1][x-1].getBidak().isP1() ){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-1,y+1)){
                        MainActivity.tiles[y+1][x-1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }
                if(x+1 <= 3 && MainActivity.papan[y+1][x+1].getBidak()!=null && MainActivity.papan[y+1][x+1].getBidak().isP1() ){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+1,y+1)){
                        MainActivity.tiles[y+1][x+1].setBackgroundColor(Color.GREEN);
                        canMove = true;
                    }
                }
            }
            return canMove;
        }

        @Override
        public ArrayList<Papan[][]> getAllPossibleMove(Bidak current, boolean isBot) {
            ArrayList<Papan[][]> all_moves = new ArrayList<>();
            int[][] temp = new int[8][4];
            for(int i=0;i<8;i++) { for(int j=0;j<4;j++) { temp[i][j] = 0; } }

            int x = current.getX(), y = current.getY();

            if(isBot && y-1>=0){ //white move up
                if(MainActivity.papan[y-1][x].getBidak()==null){
                    if (MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-1)){
                        MainActivity.tiles[y-1][x].setBackgroundColor(Color.YELLOW);
                        temp[y-1][x] = 1;
                    }

                }
                //langka awal
                if(y==6 && MainActivity.papan[y-1][x].getBidak()==null){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-1)){
                        MainActivity.tiles[y-1][x].setBackgroundColor(Color.YELLOW);
                        temp[y-1][x] = 1;
                    }
                    if(MainActivity.papan[y-2][x].getBidak()==null){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-2)){
                            MainActivity.tiles[y-2][x].setBackgroundColor(Color.YELLOW);
                            temp[y-2][x] = 1;
                        }
                    }
                }
                //Makan
                if(x-1 >= 0 && MainActivity.papan[y-1][x-1].getBidak()!=null && !MainActivity.papan[y-1][x-1].getBidak().isP1()){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-1,y-1)){
                        MainActivity.tiles[y-1][x-1].setBackgroundColor(Color.YELLOW);
                        temp[y-1][x-1] = 1;
                    }
                }
                if(x+1 <= 3 && MainActivity.papan[y-1][x+1].getBidak()!=null && !MainActivity.papan[y-1][x+1].getBidak().isP1() ){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+1,y-1)){
                        MainActivity.tiles[y-1][x+1].setBackgroundColor(Color.YELLOW);
                        temp[y-1][x+1] = 1;
                    }
                }

            }
            if(!isBot && y+1<=7){ // black move down
                if(MainActivity.papan[y+1][x].getBidak() == null){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+1)){
                        MainActivity.tiles[y+1][x].setBackgroundColor(Color.YELLOW);
                        temp[y+1][x] = 1;
                    }
                }
                if(y==1 && MainActivity.papan[y+1][x].getBidak()==null){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+1)){
                        MainActivity.tiles[y+1][x].setBackgroundColor(Color.YELLOW);
                        temp[y+1][x] = 1;
                    }
                    if(MainActivity.papan[y+2][x].getBidak()==null){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+1)){
                            MainActivity.tiles[y+2][x].setBackgroundColor(Color.YELLOW);
                            temp[y+2][x] = 1;
                        }
                    }
                }
                //Makan
                if(x-1 >= 0 && MainActivity.papan[y+1][x-1].getBidak()!=null && MainActivity.papan[y+1][x-1].getBidak().isP1() ){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-1,y+1)){
                        MainActivity.tiles[y+1][x-1].setBackgroundColor(Color.YELLOW);
                        temp[y+1][x-1] = 1;
                    }
                }
                if(x+1 <= 3 && MainActivity.papan[y+1][x+1].getBidak()!=null && MainActivity.papan[y+1][x+1].getBidak().isP1() ){
                    if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+1,y+1)){
                        MainActivity.tiles[y+1][x+1].setBackgroundColor(Color.YELLOW);
                        temp[y+1][x+1] = 1;
                    }
                }
            }

            return all_moves;
        }
    }
    public class KingMovement implements MovementBehavior{
            Boolean canMove = false;

        private void movement(Boolean p1,int y,int x){
            MainActivity.markArea();
            if(p1){
                if(MainActivity.markCheck2[y][x] == false) {  // raja gaisa gerak ke kotak sg isa dijalanin lawan
                    MainActivity.tiles[y][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }
            }else{
                if(MainActivity.markCheck[y][x] == false){  // raja gaisa gerak ke kotak sg isa dijalanin lawan
                    MainActivity.tiles[y][x].setBackgroundColor(Color.GREEN);
                    canMove = true;
                }

            }
        }
        @Override
        public Boolean Pickup(Boolean player1, int x, int y) {
            //gerak ke horizontal e tak gabung ambe gerak serong
            // cuma kopas dari koding sg gerak vertikal tak gabung nde if e horizontal
            System.out.println("King Movement");
            if(x-1>=0){
                if(MainActivity.papan[y][x-1].getBidak() == null || MainActivity.papan[y][x-1].getBidak().isP1() != player1 ){
                    movement(player1,y,x-1);
                }
                //serong
                if(y+1 <=7){
                    if(MainActivity.papan[y+1][x-1].getBidak() == null || MainActivity.papan[y+1][x-1].getBidak().isP1() != player1 ){
                        movement(player1,y+1,x-1);
                    }
                }
                if(y-1>=0){
                    if(MainActivity.papan[y-1][x-1].getBidak() == null || MainActivity.papan[y-1][x-1].getBidak().isP1() != player1 ){
                        movement(player1,y-1,x-1);
                    }
                }
                //end serong
            }

            if(x+1 <=3){
                if(MainActivity.papan[y][x+1].getBidak() == null || MainActivity.papan[y][x+1].getBidak().isP1() != player1 ){
                    movement(player1,y,x+1);
                }

                //serong
                if(y+1 <=7){
                    if(MainActivity.papan[y+1][x+1].getBidak() == null || MainActivity.papan[y+1][x+1].getBidak().isP1() != player1 ){
                        movement(player1,y+1,x+1);
                    }
                }
                if(y-1>=0){
                    if(MainActivity.papan[y-1][x+1].getBidak() == null || MainActivity.papan[y-1][x+1].getBidak().isP1() != player1 ){
                        movement(player1,y-1,x+1);
                    }
                }
                //end serong
            }

            // vertikal
            if(y+1 <=7){
                if(MainActivity.papan[y+1][x].getBidak() == null || MainActivity.papan[y+1][x].getBidak().isP1() != player1 ){
                        movement(player1,y+1,x);
                }
            }

            if(y-1>=0){
                if(MainActivity.papan[y-1][x].getBidak() == null || MainActivity.papan[y-1][x].getBidak().isP1() != player1 ){
                        movement(player1,y-1,x);
                }
            }

            return canMove;
        }

        @Override
        public ArrayList<Papan[][]> getAllPossibleMove(Bidak current, boolean isBot) {
            ArrayList<Papan[][]> all_moves = new ArrayList<>();
            int[][] temp = new int[8][4];
            for(int i=0;i<8;i++) { for(int j=0;j<4;j++) { temp[i][j] = 0; } }

            int x = current.getX(), y = current.getY();
            if(x-1>=0){
                if(MainActivity.papan[y][x-1].getBidak() == null || MainActivity.papan[y][x-1].getBidak().isP1() != isBot ){
                    movement(isBot,y,x-1);
                    MainActivity.tiles[y][x-1].setBackgroundColor(Color.YELLOW);
                    temp[y][x-1] = 1;
                }
                if(y+1 <=7){
                    if(MainActivity.papan[y+1][x-1].getBidak() == null || MainActivity.papan[y+1][x-1].getBidak().isP1() != isBot ){
                        movement(isBot,y+1,x-1);
                        MainActivity.tiles[y+1][x-1].setBackgroundColor(Color.YELLOW);
                        temp[y+1][x-1] = 1;
                    }
                }
                if(y-1>=0){
                    if(MainActivity.papan[y-1][x-1].getBidak() == null || MainActivity.papan[y-1][x-1].getBidak().isP1() != isBot ){
                        movement(isBot,y-1,x-1);
                        MainActivity.tiles[y-1][x-1].setBackgroundColor(Color.YELLOW);
                        temp[y-1][x-1] = 1;
                    }
                }
            }
            if(x+1 <=3){
                if(MainActivity.papan[y][x+1].getBidak() == null || MainActivity.papan[y][x+1].getBidak().isP1() != isBot ){
                    movement(isBot,y,x+1);
                    MainActivity.tiles[y][x+1].setBackgroundColor(Color.YELLOW);
                    temp[y][x+1] = 1;
                }
                if(y+1 <=7){
                    if(MainActivity.papan[y+1][x+1].getBidak() == null || MainActivity.papan[y+1][x+1].getBidak().isP1() != isBot ){
                        movement(isBot,y+1,x+1);
                        MainActivity.tiles[y+1][x+1].setBackgroundColor(Color.YELLOW);
                        temp[y+1][x+1] = 1;
                    }
                }
                if(y-1>=0){
                    if(MainActivity.papan[y-1][x+1].getBidak() == null || MainActivity.papan[y-1][x+1].getBidak().isP1() != isBot ){
                        movement(isBot,y-1,x+1);
                        MainActivity.tiles[y-1][x+1].setBackgroundColor(Color.YELLOW);
                        temp[y-1][x+1] = 1;
                    }
                }
            }
            if(y+1 <=7){
                if(MainActivity.papan[y+1][x].getBidak() == null || MainActivity.papan[y+1][x].getBidak().isP1() != isBot ){
                    movement(isBot,y+1,x);
                    MainActivity.tiles[y+1][x].setBackgroundColor(Color.YELLOW);
                    temp[y+1][x] = 1;
                }
            }
            if(y-1>=0){
                if(MainActivity.papan[y-1][x].getBidak() == null || MainActivity.papan[y-1][x].getBidak().isP1() != isBot ){
                    movement(isBot,y-1,x);
                    MainActivity.tiles[y-1][x].setBackgroundColor(Color.YELLOW);
                    temp[y-1][x] = 1;
                }
            }
            return all_moves;
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
                        // dikasih checksimulation , sebelum ngijo no
                        // jadi misal dee ditaruh nde situ apakah skak
                        //if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y-i)){
                            MainActivity.tiles[y-i][x-i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y-i][x-i].getBidak() != null){
                                kiriAtas = false;
                                // ben kalo nabrak dee berhenti
                            }
                        //}

                    }else if(MainActivity.papan[y-i][x-i].getBidak().isP1() == player1 ){
                            kiriAtas = false;
                    }
                }

                //serong kanan bawah
                if(x + i <=3 && y + i <=7 && kananBawah){
                    if(MainActivity.papan[y+i][x+i].getBidak() == null || MainActivity.papan[y+i][x+i].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y+i)){
                            MainActivity.tiles[y+i][x+i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y+i][x+i].getBidak() != null){
                                kananBawah = false;
                            }

                        }
                    }else if(MainActivity.papan[y+i][x+i].getBidak().isP1() == player1){
                        kananBawah = false;
                    }
                }

                //serong Kiri Bawh
                if(x - i >=0 && y + i <=7 && kiriBawah){
                    if(MainActivity.papan[y+i][x-i].getBidak() == null || MainActivity.papan[y+i][x-i].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y+i)){
                            MainActivity.tiles[y+i][x-i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y+i][x-i].getBidak() != null){
                                kiriBawah = false;
                            }

                        }
                    }else if(MainActivity.papan[y+i][x-i].getBidak().isP1() == player1 ){
                            kiriBawah = false;
                    }
                }

                // kanan atas
                if(x + i <=3 && y - i >=0 && kananAtas){
                    if(MainActivity.papan[y-i][x+i].getBidak() == null || MainActivity.papan[y-i][x+i].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y-i)){
                            MainActivity.tiles[y-i][x+i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y-i][x+i].getBidak() != null){
                                kananAtas = false;
                            }
                        }
                    }else if(MainActivity.papan[y-i][x+i].getBidak().isP1() == player1){
                            kananAtas = false;
                    }
                }

            }
            return  canMove;
        }

        @Override
        public ArrayList<Papan[][]> getAllPossibleMove(Bidak current, boolean isBot) {
            ArrayList<Papan[][]> all_moves = new ArrayList<>();
            int[][] temp = new int[8][4];
            for(int i=0;i<8;i++) { for(int j=0;j<4;j++) { temp[i][j] = 0; } }

            int x = current.getX(), y = current.getY();
            int tx = x, ty = y;

            Boolean kiriAtas = true;
            Boolean kananAtas = true;
            Boolean kiriBawah = true;
            Boolean kananBawah = true;
            //buat ngecek dee sudah nambrak 1 apa belom

            for(int i=1;i<=4;i++){
                //serong kiri atas
                if(x - i >=0 && y - i >=0 && kiriAtas){
                    if(MainActivity.papan[y-i][x-i].getBidak() == null || MainActivity.papan[y-i][x-i].getBidak().isP1() != isBot ){
                        // dikasih checksimulation , sebelum ngijo no
                        // jadi misal dee ditaruh nde situ apakah skak
                        //if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y-i)){
                        MainActivity.tiles[y-i][x-i].setBackgroundColor(Color.YELLOW);
                        temp[y-i][x-i] = 1;
                        if(MainActivity.papan[y-i][x-i].getBidak() != null){
                            kiriAtas = false;
                            // ben kalo nabrak dee berhenti
                        }
                        //}

                    }else if(MainActivity.papan[y-i][x-i].getBidak().isP1() == isBot ){
                        kiriAtas = false;
                    }
                }

                //serong kanan bawah
                if(x + i <=3 && y + i <=7 && kananBawah){
                    if(MainActivity.papan[y+i][x+i].getBidak() == null || MainActivity.papan[y+i][x+i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y+i)){
                            MainActivity.tiles[y+i][x+i].setBackgroundColor(Color.YELLOW);
                            temp[y+i][x+i] = 1;
                            if(MainActivity.papan[y+i][x+i].getBidak() != null){
                                kananBawah = false;
                            }

                        }
                    }else if(MainActivity.papan[y+i][x+i].getBidak().isP1() == isBot){
                        kananBawah = false;
                    }
                }

                //serong Kiri Bawh
                if(x - i >=0 && y + i <=7 && kiriBawah){
                    if(MainActivity.papan[y+i][x-i].getBidak() == null || MainActivity.papan[y+i][x-i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y+i)){
                            MainActivity.tiles[y+i][x-i].setBackgroundColor(Color.YELLOW);
                            temp[y+i][x-i] = 1;
                            if(MainActivity.papan[y+i][x-i].getBidak() != null){
                                kiriBawah = false;
                            }

                        }
                    }else if(MainActivity.papan[y+i][x-i].getBidak().isP1() == isBot ){
                        kiriBawah = false;
                    }
                }

                // kanan atas
                if(x + i <=3 && y - i >=0 && kananAtas){
                    if(MainActivity.papan[y-i][x+i].getBidak() == null || MainActivity.papan[y-i][x+i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y-i)){
                            MainActivity.tiles[y-i][x+i].setBackgroundColor(Color.YELLOW);
                            temp[y-i][x+i] = 1;
                            if(MainActivity.papan[y-i][x+i].getBidak() != null){
                                kananAtas = false;
                            }
                        }
                    }else if(MainActivity.papan[y-i][x+i].getBidak().isP1() == isBot){
                        kananAtas = false;
                    }
                }
            }

            Papan[][] tempBoard = new Papan[8][4];
            for(int i=0;i<8;i++){
                for(int j=0;j<4;j++){
                    tempBoard[i][j] = MainActivity.papan[i][j];
                }
            }
            return all_moves;

        }
    }
    public class QueenMovement implements  MovementBehavior{
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

            for(int i=1;i<=7;i++){
                //serong kiri atas

                if(MainActivity.papan[y][x].getBidak() == null){
                    System.out.println("Bidak is  Null");
                }else{
                    System.out.println("Bidak Masih Ada");
                }
                if(x - i >=0 && y - i >=0 && kiriAtas){
                    if(MainActivity.papan[y-i][x-i].getBidak() == null || MainActivity.papan[y-i][x-i].getBidak().isP1() != player1  ){
                        System.out.println("Kiri Atas" + MainActivity.papan[y][x].getBidak().getX() + MainActivity.papan[y][x].getBidak().getY());
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y-i)){
                            System.out.println("bisa ke kiri Atas");
                            MainActivity.tiles[y-i][x-i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y-i][x-i].getBidak() != null){
                                kiriAtas = false;
                                // ben kalo nabrak dee berhenti
                            }
                        }
                        if(MainActivity.papan[y][x].getBidak() == null){
                            System.out.println("Bidak is  Null");
                        }else{
                            System.out.println("Bidak Masih Ada");
                        }
                    }else if(MainActivity.papan[y-i][x-i].getBidak().isP1() == player1 ){
                        kiriAtas = false;
                    }
                }

                if(MainActivity.papan[y][x].getBidak() == null){
                    System.out.println("Bidak is  Null");
                }else{
                    System.out.println("Bidak Masih Ada");
                }

                //serong kanan bawah
                if(x + i <=3 && y + i <=7 && kananBawah){
                    if(MainActivity.papan[y+i][x+i].getBidak() == null || MainActivity.papan[y+i][x+i].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y+i)){
                            MainActivity.tiles[y+i][x+i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y+i][x+i].getBidak() != null){
                                kananBawah = false;
                            }
                        }
                    }else if(MainActivity.papan[y+i][x+i].getBidak().isP1() == player1){
                        kananBawah = false;
                    }
                }

                //serong Kiri Bawh
                if(x - i >=0 && y + i <=7 && kiriBawah){
                    if(MainActivity.papan[y+i][x-i].getBidak() == null || MainActivity.papan[y+i][x-i].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y+i)){
                            MainActivity.tiles[y+i][x-i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y+i][x-i].getBidak() != null){
                                kiriBawah = false;
                            }
                        }
                    }else if(MainActivity.papan[y+i][x-i].getBidak().isP1() == player1 ){
                        kiriBawah = false;
                    }
                }

                // kanan atas
                if(x + i <=3 && y - i >=0 && kananAtas){
                    if(MainActivity.papan[y-i][x+i].getBidak() == null || MainActivity.papan[y-i][x+i].getBidak().isP1() != player1 ){

                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y-i)){
                            MainActivity.tiles[y-i][x+i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y-i][x+i].getBidak() != null){
                                kananAtas = false;
                            }
                        }
                    }else if(MainActivity.papan[y-i][x+i].getBidak().isP1() == player1){
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
                if(x-i >= 0 && kiri){
                    if(MainActivity.papan[y][x-i].getBidak() == null || MainActivity.papan[y][x-i].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y)){
                            MainActivity.tiles[y][x-i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y][x-i].getBidak() != null){
                                kiri = false;
                            }
                        }
                    }else if(MainActivity.papan[y][x-i].getBidak().isP1() == player1){
                        kiri = false;
                    }
                }

                if(x+i <= 3 && kanan){
                    if(MainActivity.papan[y][x+i].getBidak() == null || MainActivity.papan[y][x+i].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y)){
                            MainActivity.tiles[y][x+i].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y][x+i].getBidak() != null){
                                kanan = false;
                            }
                        }
                    }else if(MainActivity.papan[y][x+i].getBidak().isP1() == player1){
                        kanan = false;
                    }
                }

                if(y-i >= 0 && atas){
                    if(MainActivity.papan[y-i][x].getBidak() == null || MainActivity.papan[y-i][x].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-i)){
                            MainActivity.tiles[y-i][x].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y-i][x].getBidak() != null){
                                atas = false;
                            }
                        }
                    }else if(MainActivity.papan[y-i][x].getBidak().isP1() == player1){
                        atas = false;
                    }
                }

                if(y+i <= 7 && bawah){
                    if(MainActivity.papan[y+i][x].getBidak() == null || MainActivity.papan[y+i][x].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+i)){
                            MainActivity.tiles[y+i][x].setBackgroundColor(Color.GREEN);
                            canMove = true;
                            if(MainActivity.papan[y+i][x].getBidak() != null){
                                bawah = false;
                            }
                        }
                    }else if(MainActivity.papan[y+i][x].getBidak().isP1() == player1){
                        bawah = false;
                    }
                }

            }
            return  canMove;
        }

        @Override
        public ArrayList<Papan[][]> getAllPossibleMove(Bidak current, boolean isBot) {
            ArrayList<Papan[][]> all_moves = new ArrayList<>();
            int[][] temp = new int[8][4];
            for(int i=0;i<8;i++) { for(int j=0;j<4;j++) { temp[i][j] = 0; } }

            int x = current.getX(), y = current.getY();
            int tx = x, ty = y;
            boolean kiriAtas = true, kananAtas = true, kiriBawah = true, kananBawah = true;

            for(int i=1;i<=7;i++){
                if(x - i >=0 && y - i >=0 && kiriAtas){
                    if(MainActivity.papan[y-i][x-i].getBidak() == null || MainActivity.papan[y-i][x-i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y-i)){
                            MainActivity.tiles[y-i][x-i].setBackgroundColor(Color.YELLOW);
                            temp[y-i][x-i] = 1;
                            if(MainActivity.papan[y-i][x-i].getBidak() != null){
                                kiriAtas = false;
                            }
                        }
                    }else if(MainActivity.papan[y-i][x-i].getBidak().isP1() == isBot ){
                        kiriAtas = false;
                    }
                }
                if(x + i <=3 && y + i <=7 && kananBawah){
                    if(MainActivity.papan[y+i][x+i].getBidak() == null || MainActivity.papan[y+i][x+i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y+i)){
                            MainActivity.tiles[y+i][x+i].setBackgroundColor(Color.YELLOW);
                            temp[y+i][x+i] = 1;
                            if(MainActivity.papan[y+i][x+i].getBidak() != null){
                                kananBawah = false;
                            }
                        }
                    }else if(MainActivity.papan[y+i][x+i].getBidak().isP1() == isBot){
                        kananBawah = false;
                    }
                }
                //serong Kiri Bawh
                if(x - i >=0 && y + i <=7 && kiriBawah){
                    if(MainActivity.papan[y+i][x-i].getBidak() == null || MainActivity.papan[y+i][x-i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y+i)){
                            MainActivity.tiles[y+i][x-i].setBackgroundColor(Color.YELLOW);
                            temp[y+i][x-i] = 1;
                            if(MainActivity.papan[y+i][x-i].getBidak() != null){
                                kiriBawah = false;
                            }
                        }
                    }else if(MainActivity.papan[y+i][x-i].getBidak().isP1() == isBot ){
                        kiriBawah = false;
                    }
                }
                // kanan atas
                if(x + i <=3 && y - i >=0 && kananAtas){
                    if(MainActivity.papan[y-i][x+i].getBidak() == null || MainActivity.papan[y-i][x+i].getBidak().isP1() != isBot ){

                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y-i)){
                            MainActivity.tiles[y-i][x+i].setBackgroundColor(Color.YELLOW);
                            temp[y-i][x+i] = 1;
                            if(MainActivity.papan[y-i][x+i].getBidak() != null){
                                kananAtas = false;
                            }
                        }
                    }else if(MainActivity.papan[y-i][x+i].getBidak().isP1() == isBot){
                        kananAtas = false;
                    }
                }

            }

            boolean kiri = true, kanan = true, atas = true, bawah = true;
            // vertikal horizontal
            for (int i=1;i<=7;i++){
                if(x-i >= 0 && kiri){
                    if(MainActivity.papan[y][x-i].getBidak() == null || MainActivity.papan[y][x-i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x-i,y)){
                            MainActivity.tiles[y][x-i].setBackgroundColor(Color.YELLOW);
                            temp[y][x-i] = 1;
                            if(MainActivity.papan[y][x-i].getBidak() != null){
                                kiri = false;
                            }
                        }
                    }else if(MainActivity.papan[y][x-i].getBidak().isP1() == isBot){
                        kiri = false;
                    }
                }
                if(x+i <= 3 && kanan){
                    if(MainActivity.papan[y][x+i].getBidak() == null || MainActivity.papan[y][x+i].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+i,y)){
                            MainActivity.tiles[y][x+i].setBackgroundColor(Color.YELLOW);
                            temp[y][x+i] = 1;
                            if(MainActivity.papan[y][x+i].getBidak() != null){
                                kanan = false;
                            }
                        }
                    }else if(MainActivity.papan[y][x+i].getBidak().isP1() == isBot){
                        kanan = false;
                    }
                }
                if(y-i >= 0 && atas){
                    if(MainActivity.papan[y-i][x].getBidak() == null || MainActivity.papan[y-i][x].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y-i)){
                            MainActivity.tiles[y-i][x].setBackgroundColor(Color.YELLOW);
                            temp[y-i][x] = 1;
                            if(MainActivity.papan[y-i][x].getBidak() != null){
                                atas = false;
                            }
                        }
                    }else if(MainActivity.papan[y-i][x].getBidak().isP1() == isBot){
                        atas = false;
                    }
                }
                if(y+i <= 7 && bawah){
                    if(MainActivity.papan[y+i][x].getBidak() == null || MainActivity.papan[y+i][x].getBidak().isP1() != isBot ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x,y+i)){
                            MainActivity.tiles[y+i][x].setBackgroundColor(Color.YELLOW);
                            temp[y+i][x] = 1;
                            if(MainActivity.papan[y+i][x].getBidak() != null){
                                bawah = false;
                            }
                        }
                    }else if(MainActivity.papan[y+i][x].getBidak().isP1() == isBot){
                        bawah = false;
                    }
                }
            }
            return all_moves;
        }
    }
    public class HorseMovement implements MovementBehavior{

        @Override
        public Boolean Pickup(Boolean player1, int x, int y) {
            Boolean canMove=false;
            int[] moveX = new int[]{-1,1,2,2,1,-1,-2,-2};
            int[] moveY = new int[]{-2,-2,-1,1,2,2,1,-1};
            for(int i=0;i<8;i++){
                if(x+moveX[i]>=0 && y+moveY[i]>=0 && x+moveX[i]<=3 && y+moveY[i]<=7){
                    if(MainActivity.papan[y+moveY[i]][x+moveX[i]].getBidak() == null || MainActivity.papan[y+moveY[i]][x+moveX[i]].getBidak().isP1() != player1 ){
                        if(MainActivity.papan[y][x].getBidak().MoveSimulation(x+moveX[i],y+moveY[i])){
                            MainActivity.tiles[y+moveY[i]][x+moveX[i]].setBackgroundColor(Color.GREEN);
                            canMove = true;
                        }
                    }
                }
            }
            return canMove;
        }

        @Override
        public ArrayList<Papan[][]> getAllPossibleMove(Bidak currentBidak, boolean isBot) {
            ArrayList<Papan[][]> all_moves = new ArrayList<>();
            int[][] temp = new int[8][4];
            for(int i=0;i<8;i++) { for(int j=0;j<4;j++) { temp[i][j] = 0; } }

            // check move
            int x=currentBidak.getX()+1, y=currentBidak.getY();

            int[] moveX = new int[]{-1,1,2,2,1,-1,-2,-2};
            int[] moveY = new int[]{-2,-2,-1,1,2,2,1,-1};
            for(int i=0;i<8;i++){
                if(x+moveX[i]>=0 && y+moveY[i]>=0 && x+moveX[i]<=3 && y+moveY[i]<=7){
                    if(MainActivity.papan[y+moveY[i]][x+moveX[i]].getBidak() == null || MainActivity.papan[y+moveY[i]][x+moveX[i]].getBidak().isP1() != isBot ){
                        MainActivity.tiles[y+moveY[i]][x+moveX[i]].setBackgroundColor(Color.YELLOW);
                        temp[y+moveY[i]][x+moveX[i]] = 1;
                    }
                }
            }

            Papan[][] tempBoard = new Papan[8][4];
            for(int i=0;i<8;i++){
                for(int j=0;j<4;j++){
                    tempBoard[i][j] = new Papan(i,j,MainActivity.papan[i][j].getIdPapan());
                    if (!(i == y && j == x))
                    {
                        if (temp[i][j] == 0)
                        {
                            tempBoard[i][j].setBidak( MainActivity.papan[i][j].getBidak() );
                        }
                    }
                }
            }
            for(int i=0; i<8; i++) {
                for (int j=0; j<4; j++) {
                    if (temp[i][j] == 1)
                    {
                        tempBoard[i][j].setBidak( currentBidak );
                        all_moves.add(tempBoard);
                        tempBoard[i][j].setBidak( null );
                    }
                }
            }
            return all_moves;
        }
    }
}
