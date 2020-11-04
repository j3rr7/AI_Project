package com.example.halfchess;

public class Knight extends Bidak {
    public Knight(Papan papan, boolean p1) {
        super(papan, p1);
        move = new MovementBehavior.HorseMovement();
        if(p1){
            img = R.mipmap.whitekniht;
        }else{
            img = R.mipmap.blackkinght;
        }
    }
}
