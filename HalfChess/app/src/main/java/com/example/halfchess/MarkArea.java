package com.example.halfchess;

public interface MarkArea {
    public void Mark(Boolean p1 ,int x,int y);

    public class PawnArea implements MarkArea{

        @Override
        public void Mark(Boolean p1, int x, int y) {
            if(p1){ // move to up
                if(y-1>=0){
                    if(MainActivity.papan[y-1][x].getBidak()==null){
                        MainActivity.markedArea[y-1][x]=true;
                    }
                    if(x-1>=0  && MainActivity.papan[y-1][x-1].getBidak()==null){
                        MainActivity.markedArea[y-1][x-1]=true;
                    }
                    if(x+1<=3 && MainActivity.papan[y-1][x+1].getBidak()==null){
                        MainActivity.markedArea[y-1][x+1]=true;
                    }
                }

            }else{ // black moves down
                if(y+1<=7){
                    if(MainActivity.papan[y+1][x].getBidak()==null){
                        MainActivity.markedArea[y+1][x]=true;
                    }
                    if(x-1>=0  && MainActivity.papan[y+1][x-1].getBidak()==null){
                        MainActivity.markedArea[y+1][x-1]=true;
                    }
                    if(x+1<=3 && MainActivity.papan[y+1][x+1].getBidak()==null){
                        MainActivity.markedArea[y+1][x+1]=true;
                    }
                }
            }
        }
    }
}
