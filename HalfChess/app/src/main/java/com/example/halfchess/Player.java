package com.example.halfchess;

public class Player {

    Bidak[] bidaks;
    Boolean isChecked;

    public Bidak[] getBidaks() {
        return bidaks;
    }

    public void setBidaks(Bidak[] bidaks) {
        this.bidaks = bidaks;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Player(Bidak[] bidaks) {
        this.bidaks = bidaks;
        isChecked = false;
    }


}
