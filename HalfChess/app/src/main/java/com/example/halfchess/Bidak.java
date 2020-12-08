package com.example.halfchess;

public abstract class Bidak {

    MovementBehavior move;
    MarkArea mark;
    Papan papan;
    boolean p1; // 1 punya e p1 0 p2
    int img;

    // PS (JERE) : setiap jenis bidak diberi value ToDo Add value to every bidak
    int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    // ==========================================================================

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
    public void setX(int x){
        papan.setX(x);
    }
    public void setY(int y){
        papan.setY(y);
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
