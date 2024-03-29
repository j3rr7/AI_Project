package com.example.halfchess;

public abstract class Bidak {

    MovementBehavior move;
    Papan papan;
    boolean p1; // 1 punya e p1 0 p2
    int img;

    public MovementBehavior getMove() {
        return move;
    }

    public void setMove(MovementBehavior move) {
        this.move = move;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Bidak(Papan papan, boolean p1) {
        this.papan = papan;
        this.p1 = p1;
    }

    public int getIdPapan(){ // nyari bidak mana sg diklik
        return papan.getIdPapan();
    }

    public int getX(){
        return  papan.getX();
    }
    public int getY(){
        return papan.getY();
    }

    public Papan getPapan() {
        return papan;
    }

    public void setPapan(Papan papan) {
        this.papan = papan;
    }

    public boolean isP1() {
        return p1;
    }

    public void setP1(boolean p1) {
        this.p1 = p1;
    }
}
