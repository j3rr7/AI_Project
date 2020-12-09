package com.example.halfchess;

public class Papan {
    int x,y;
    int idPapan; //id dari imgView biar isa dipasno
    Bidak bidak;

    public Bidak getBidak() {
        return bidak;
    }

    public void setBidak(Bidak bidak) {
        this.bidak = bidak;
    }

    public void setBidakNull(){
        this.bidak = null;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIdPapan() {
        return idPapan;
    }

    public void setIdPapan(int idPapan) {
        this.idPapan = idPapan;
    }

    public Papan(int x, int y, int idPapan) {
        this.x = x;
        this.y = y;
        this.idPapan = idPapan;
    }
}
