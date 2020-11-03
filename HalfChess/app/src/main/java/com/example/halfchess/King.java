package com.example.halfchess;

public class King extends Bidak {
    public King(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.KingMovement();
        if(p1){
            img = R.mipmap.whiteking;
        }else{
            img = R.mipmap.blackking;
        }
    }
}
