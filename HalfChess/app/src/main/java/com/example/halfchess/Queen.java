package com.example.halfchess;

public class Queen extends Bidak {
    public Queen(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.QueenMovement();
        if(p1){
            img = R.mipmap.whitequeen;
        }else{
            img = R.mipmap.blackqueen;
        }
    }
}
