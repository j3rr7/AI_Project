package com.example.halfchess;

public class Bishop extends Bidak {
    public Bishop(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.PawnMovement();
        if(p1){
            img = R.mipmap.whitebishop;
        }else{
            img = R.mipmap.blackbishop;
        }
    }
}
