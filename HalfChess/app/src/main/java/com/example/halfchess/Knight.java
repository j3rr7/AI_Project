package com.example.halfchess;

public class Knight extends Bidak {
    public Knight(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.HorseMovement();
        mark =  new MarkArea.KnightArea();
        markSimulation = new MarkSimulation.KnightSimulation();


        if(p1){
            img = R.mipmap.knight_1g;
        }else{
            img = R.mipmap.knight_2;
        }
    }
}
