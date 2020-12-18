package com.example.halfchess;

import android.graphics.Color;
import android.widget.ImageView;

public class Papan {
    // 1 : Pawn
    // 2 : Bishop
    // 3 : Knight
    // 4 : Queen
    // 5 : King
    private Bidak bidak;
    ImageView letak;
    private int status; // 0 1 // ben tau lek dee dipencet
    boolean untouched;
    boolean enPassant;
    private boolean pressed;
    private String color;

    public Papan( Bidak bidak,ImageView letak, String color) {
        this.bidak = bidak;
        this.letak = letak;
        pressed = false;
        status = 0;
        enPassant = false;
        untouched = true;
        this.color = color;
        letak.setBackgroundColor(Color.parseColor(this.color));
        updatePapan();
    }

    public Bidak getBidak() {
        return bidak;
    }

    public void setBidak(Bidak bidak) {
        this.bidak = bidak;
        this.status = 0;
        this.pressed = false;
        updatePapan();
    }

    public void updatePapan(){
        // 1 : Pawn
        // 2 : Bishop
        // 3 : Knight
        // 4 : Queen
        // 5 : King

        if(bidak.isWhite()){
            if(bidak.getValue() == 1){
                letak.setImageResource(R.mipmap.pawn_newg);
            }else if(bidak.getValue() == 2){
                letak.setImageResource(R.mipmap.bishop_new_2g);
            }else if(bidak.getValue() == 3){
                letak.setImageResource(R.mipmap.knight_1g);
            }else if(bidak.getValue() == 4){
                letak.setImageResource(R.mipmap.queeng);
            }else if(bidak.getValue() == 5){
                letak.setImageResource(R.mipmap.king_1g);
            }else{
                letak.setImageResource(0);
            }
        }else{
            if(bidak.getValue() == 1){
                letak.setImageResource(R.mipmap.pawn_new);
            }else if(bidak.getValue() == 2){
                letak.setImageResource(R.mipmap.bishop_new_2);
            }else if(bidak.getValue() == 3){
                letak.setImageResource(R.mipmap.knight_2);
            }else if(bidak.getValue() == 4){
                letak.setImageResource(R.mipmap.queen1);
            }else if(bidak.getValue() == 5){
                letak.setImageResource(R.mipmap.king_1);
            }else{
                letak.setImageResource(0);
            }
        }


//        papan[0][0] = new Papan(new Bidak(5,false),tiles[0][0], "#FFFFFF");
//        papan[0][1] = new Papan(new Bidak(4,false),tiles[0][1], "#D9E1F6");


        if(status != 0){
            if(color.equals("#D9E1F6"))
                letak.setBackgroundColor(Color.parseColor("#5ca08e"));
            else
                letak.setBackgroundColor(Color.parseColor("#8abaae"));
        }else{
            letak.setBackgroundColor(Color.parseColor(color));
        }

    }

    public void pressed(){
        if(!pressed) pressed = true;
        else pressed = false;
        updatePapan();
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
        updatePapan();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int Paramstatus) {
        this.status = Paramstatus;
//        if(Paramstatus==1){
//            letak.setBackgroundColor(Color.GREEN);
//        }
        updatePapan();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
