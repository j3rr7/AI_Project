package com.example.halfchess;

public class Pawn extends Bidak {

    public Pawn(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.PawnMovement();
        mark = new MarkArea.PawnArea();
        markSimulation = new MarkSimulation.PawnSimulation();
        if(p1){
            img = R.mipmap.pawn_newg;
        }else{
            img = R.mipmap.pawn_new;
        }
    }


}
