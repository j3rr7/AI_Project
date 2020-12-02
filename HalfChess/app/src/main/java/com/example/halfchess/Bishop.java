package com.example.halfchess;

public class Bishop extends Bidak {
    public Bishop(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.BishopMovement();
        mark = new MarkArea.BishopArea();
        if(p1){
            img = R.mipmap.bishop_new_2g;
        }else{
            img = R.mipmap.bishop_new_2;
        }
    }
}
