package com.example.halfchess;

public class Queen extends Bidak {
    public Queen(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.QueenMovement();
        mark = new MarkArea.QueenArea();
        if(p1){
            img = R.mipmap.queeng;
        }else{
            img = R.mipmap.queen1;
        }

        // Value bidak
        super.setValue(9);
    }
}
