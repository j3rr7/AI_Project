package com.example.halfchess;

public class Pawn extends Bidak {

    public Pawn(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.PawnMovement();
        mark = new MarkArea.PawnArea();
        if(p1){
            img = R.mipmap.whitepawn;
        }else{
            img = R.mipmap.blackpawn;
        }
    }


}
