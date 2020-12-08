package com.example.halfchess;

public class King extends Bidak {
    public King(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.KingMovement();
        if(p1){
            img = R.mipmap.king_1g;
        }else{
            img = R.mipmap.king_1;
        }

        // Value bidak
        super.setValue(99);
    }
}
