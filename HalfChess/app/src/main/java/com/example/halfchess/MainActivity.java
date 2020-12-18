package com.example.halfchess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ImageView[][] tiles = new ImageView[8][4];

    private Papan[][] papan = new Papan[8][4];
    private boolean makan=false;
    private int status = -1;//-1 still play, draw = 0
    // , white win = 1, black win = 2
    private ImageView virtualIV ;
    int selectY=-1,selectX=-1; //  buat nentukan posisi papan yang diselect


    private int[][] repetition = new int[4][4];
    private boolean[] check = new boolean[2];
    TextView whiteTurn,blackTurn;

    Boolean turnP1;
    boolean vsAI = false;
    TextView tvTurn;
    Chronometer cmTimer2;
    Chronometer cmTimer;
    long elapsedTime2;
    long elapsedTime;
    int ctr = 0;
    int canMoveCounter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTurn = findViewById(R.id.tvTurn);
        cmTimer = (Chronometer) findViewById(R.id.cmTimer);
        cmTimer = (Chronometer) findViewById(R.id.cmTimer);
        cmTimer2 = (Chronometer) findViewById(R.id.cmTimer2);
        virtualIV = findViewById(R.id.hiddenImg);
        AI.AIBehaviour.debug = virtualIV;
        setImageView();
        setPapan();

        if( getIntent().hasExtra("COM"))
        {
            if (getIntent().getIntExtra("COM", 0) == 1)
            {
                vsAI = true;
            }
        }

        cmTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer arg0) {
                long minutes = ((SystemClock.elapsedRealtime() - cmTimer.getBase())/1000) / 60;
                long seconds = ((SystemClock.elapsedRealtime() - cmTimer.getBase())/1000) % 60;
                elapsedTime = elapsedTime+1000;
            }
        });
        cmTimer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer arg0) {
                long minutes = ((SystemClock.elapsedRealtime() - cmTimer2.getBase())/1000) / 60;
                long seconds = ((SystemClock.elapsedRealtime() - cmTimer2.getBase())/1000) % 60;
                elapsedTime2 = elapsedTime2+1000;
            }
        });
        cmTimer.setBase(SystemClock.elapsedRealtime());
        cmTimer.start();
        turnP1 = true;
    }

    public void setImageView(){
        tiles[0][0] = findViewById(R.id.imageView2);
        tiles[0][1] = findViewById(R.id.imageView3);
        tiles[0][2] = findViewById(R.id.imageView4);
        tiles[0][3] = findViewById(R.id.imageView5);

        tiles[1][0] = findViewById(R.id.imageView6);
        tiles[1][1] = findViewById(R.id.imageView7);
        tiles[1][2] = findViewById(R.id.imageView8);
        tiles[1][3] = findViewById(R.id.imageView9);

        tiles[2][0] = findViewById(R.id.imageView14);
        tiles[2][1] = findViewById(R.id.imageView15);
        tiles[2][2] = findViewById(R.id.imageView16);
        tiles[2][3] = findViewById(R.id.imageView17);

        tiles[3][0] = findViewById(R.id.imageView18);
        tiles[3][1] = findViewById(R.id.imageView19);
        tiles[3][2] = findViewById(R.id.imageView20);
        tiles[3][3] = findViewById(R.id.imageView21);

        tiles[4][0] = findViewById(R.id.imageView22);
        tiles[4][1] = findViewById(R.id.imageView23);
        tiles[4][2] = findViewById(R.id.imageView24);
        tiles[4][3] = findViewById(R.id.imageView25);

        tiles[5][0] = findViewById(R.id.imageView26);
        tiles[5][1] = findViewById(R.id.imageView27);
        tiles[5][2] = findViewById(R.id.imageView28);
        tiles[5][3] = findViewById(R.id.imageView29);

        tiles[6][0] = findViewById(R.id.imageView30);
        tiles[6][1] = findViewById(R.id.imageView31);
        tiles[6][2] = findViewById(R.id.imageView32);
        tiles[6][3] = findViewById(R.id.imageView33);

        tiles[7][0] = findViewById(R.id.imageView34);
        tiles[7][1] = findViewById(R.id.imageView35);
        tiles[7][2] = findViewById(R.id.imageView36);
        tiles[7][3] = findViewById(R.id.imageView37);

    }
    public void setPapan(){
        papan[0][0] = new Papan(new Bidak(5,false),tiles[0][0], "#FFFFFF");
        papan[0][1] = new Papan(new Bidak(4,false),tiles[0][1], "#D9E1F6");
        papan[0][2] = new Papan(new Bidak(3,false),tiles[0][2], "#FFFFFF");
        papan[0][3] = new Papan(new Bidak(2,false),tiles[0][3], "#D9E1F6");

        papan[1][0] = new Papan(new Bidak(1,false),tiles[1][0], "#D9E1F6");
        papan[1][1] = new Papan(new Bidak(1,false),tiles[1][1], "#FFFFFF");
        papan[1][2] = new Papan(new Bidak(1,false),tiles[1][2], "#D9E1F6");
        papan[1][3] = new Papan(new Bidak(1,false),tiles[1][3], "#FFFFFF");

        papan[2][0] = new Papan(new Bidak(0,false),tiles[2][0], "#FFFFFF");
        papan[2][1] = new Papan(new Bidak(0,false),tiles[2][1], "#D9E1F6");
        papan[2][2] = new Papan(new Bidak(0,false),tiles[2][2], "#FFFFFF");
        papan[2][3] = new Papan(new Bidak(0,false),tiles[2][3], "#D9E1F6");

        papan[3][0] = new Papan(new Bidak(0,false),tiles[3][0], "#D9E1F6");
        papan[3][1] = new Papan(new Bidak(0,false),tiles[3][1], "#FFFFFF");
        papan[3][2] = new Papan(new Bidak(0,false),tiles[3][2], "#D9E1F6");
        papan[3][3] = new Papan(new Bidak(0,false),tiles[3][3], "#FFFFFF");

        papan[4][0] = new Papan(new Bidak(0,false),tiles[4][0], "#FFFFFF");
        papan[4][1] = new Papan(new Bidak(0,false),tiles[4][1], "#D9E1F6");
        papan[4][2] = new Papan(new Bidak(0,false),tiles[4][2], "#FFFFFF");
        papan[4][3] = new Papan(new Bidak(0,false),tiles[4][3], "#D9E1F6");

        papan[5][0] = new Papan(new Bidak(0,false),tiles[5][0], "#D9E1F6");
        papan[5][1] = new Papan(new Bidak(0,false),tiles[5][1], "#FFFFFF");
        papan[5][2] = new Papan(new Bidak(0,false),tiles[5][2], "#D9E1F6");
        papan[5][3] = new Papan(new Bidak(0,false),tiles[5][3], "#FFFFFF");

        papan[6][0] = new Papan(new Bidak(1,true),tiles[6][0], "#FFFFFF");
        papan[6][1] = new Papan(new Bidak(1,true),tiles[6][1], "#D9E1F6");
        papan[6][2] = new Papan(new Bidak(1,true),tiles[6][2], "#FFFFFF");
        papan[6][3] = new Papan(new Bidak(1,true),tiles[6][3], "#D9E1F6");

        papan[7][0] = new Papan(new Bidak(5,true),tiles[7][0], "#D9E1F6");
        papan[7][1] = new Papan(new Bidak(4,true),tiles[7][1], "#FFFFFF");
        papan[7][2] = new Papan(new Bidak(3,true),tiles[7][2], "#D9E1F6");
        papan[7][3] = new Papan(new Bidak(2,true),tiles[7][3], "#FFFFFF");
    }

    boolean isSave(int baris, int kolom,Papan[][] papan){
        if(turnP1){
            //pawn

            if(baris - 1 >= 0 && kolom-1 >= 0 && papan[baris-1][kolom-1].getBidak().getValue() == 1 && papan[baris-1][kolom-1].getBidak().isWhite() != turnP1){
                return false;
            }
            if(baris - 1 >= 0 && kolom+1 <= 3 && papan[baris-1][kolom+1].getBidak().getValue() == 1 && papan[baris-1][kolom+1].getBidak().isWhite() != turnP1){
                return false;
            }
        }else{
            if(baris + 1 <= 7 && kolom-1 >= 0 && papan[baris + 1][kolom - 1].getBidak().getValue() == 1 && papan[baris+1][kolom-1].getBidak().isWhite() != turnP1){
                return false;
            }
            if(baris + 1 <= 7 && kolom+1 <= 3 && papan[baris + 1][kolom + 1].getBidak().getValue() == 1 && papan[baris+1][kolom+1].getBidak().isWhite() != turnP1){
                return false;
            }
        }//bishop & queen(diagonal)
        //kanan bawah
        for (int i = baris + 1,j = kolom + 1; i < 8 && j < 4; i++, j++) {
            if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
                return false;
            }else if(papan[i][j].getBidak().getValue() != 0){
                if(papan[i][j].getBidak().getValue() != 5)break;
            }
        }
        //kanan atas
        for (int i = baris - 1,j = kolom + 1; i >= 0 && j < 4; i--, j++) {
            if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
                return false;
            }else if(papan[i][j].getBidak().getValue() != 0){
                if(papan[i][j].getBidak().getValue() != 5)break;
            }
        }
        //kiri atas
        for (int i = baris - 1,j = kolom - 1; i >= 0 && j >= 0; i--, j--) {
            if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
                return false;
            }else if(papan[i][j].getBidak().getValue() != 0) {
                if(papan[i][j].getBidak().getValue() != 5)break;
            }
        }
        //kiri bawah
        for (int i = baris + 1,j = kolom - 1; i < 8 && j >= 0; i++, j--) {
            if((papan[i][j].getBidak().getValue() == 2 || papan[i][j].getBidak().getValue() == 4) && papan[i][j].getBidak().isWhite() != turnP1){
                return false;
            }else if(papan[i][j].getBidak().getValue() != 0) {
                if(papan[i][j].getBidak().getValue() != 5)break;
            }
        }
        //knight
        if(baris - 2 >= 0 && kolom - 1  >= 0){
            if(papan[baris - 2][kolom - 1].getBidak().getValue() == 3 && papan[baris - 2][kolom - 1].getBidak().isWhite() != turnP1){
                return false;
            }
        }if(baris - 2 >= 0 && kolom + 1  <= 3){
            if(papan[baris - 2][kolom + 1].getBidak().getValue() == 3 && papan[baris - 2][kolom + 1].getBidak().isWhite() != turnP1){
                return false;
            }
        }if(baris + 2 <= 7 && kolom - 1  >= 0){
            if(papan[baris + 2][kolom - 1].getBidak().getValue() == 3 && papan[baris + 2][kolom - 1].getBidak().isWhite() != turnP1){
                return false;
            }
        }if(baris + 2 <= 7 && kolom + 1  <= 3){
            if(papan[baris + 2][kolom + 1].getBidak().getValue() == 3 && papan[baris + 2][kolom + 1].getBidak().isWhite() != turnP1){
                return false;
            }
        }if(baris - 1 >= 0 && kolom + 2  <= 3){
            if(papan[baris - 1][kolom + 2].getBidak().getValue() == 3 && papan[baris - 1][kolom + 2].getBidak().isWhite() != turnP1){
                return false;
            }
        }if(baris - 1 >= 0 && kolom - 2  >= 0){
            if(papan[baris - 1][kolom - 2].getBidak().getValue() == 3 && papan[baris - 1][kolom - 2].getBidak().isWhite() != turnP1){
                return false;
            }
        }if(baris + 1 <= 7 && kolom - 2  >= 0){
            if(papan[baris + 1][kolom - 2].getBidak().getValue() == 3 && papan[baris + 1][kolom - 2].getBidak().isWhite() != turnP1){
                return false;
            }
        }if(baris + 1 <= 7 && kolom + 2  <= 3){
            if(papan[baris + 1][kolom + 2].getBidak().getValue() == 3 && papan[baris + 1][kolom + 2].getBidak().isWhite() != turnP1){
                return false;
            }
        }
        //queen
        for (int i = baris; i < 8; i++) {
            if(papan[i][kolom].getBidak().getValue() == 4 && papan[i][kolom].getBidak().isWhite() !=  papan[baris][kolom].getBidak().isWhite()){
                return false;
            }
            else if(papan[i][kolom].getBidak().getValue() != 0) {
                if(papan[i][kolom].getBidak().getValue() != 5) break;
            }
        }
        for (int i = baris; i >= 0; i--) {
            if(papan[i][kolom].getBidak().getValue() == 4 && papan[i][kolom].getBidak().isWhite() !=  papan[baris][kolom].getBidak().isWhite()){
                return false;
            }
            else if(papan[i][kolom].getBidak().getValue() != 0 ) {
                if(papan[i][kolom].getBidak().getValue() != 5) break;
            }
        }
        for (int i = kolom; i < 4; i++) {
            if(papan[baris][i].getBidak().getValue() == 4 && papan[baris][i].getBidak().isWhite() != papan[baris][kolom].getBidak().isWhite()){
                return false;
            }
            else if(papan[baris][i].getBidak().getValue() != 0) {
                if(papan[baris][i].getBidak().getValue() != 5) break;
            }
        }

        for (int i = kolom; i >= 0; i--) {
            if(papan[baris][i].getBidak().getValue() == 4 && papan[baris][i].getBidak().isWhite() !=  papan[baris][kolom].getBidak().isWhite()){
                return false;
            }
            else if(papan[baris][i].getBidak().getValue() != 0) {
                if(papan[baris][i].getBidak().getValue() != 5) break;
            }
        }

        //king
        for (int i = baris-1; i < baris+2; i++) {
            for (int j = kolom-1; j < kolom+2; j++) {
                if(i <= 7 && i >= 0 && j <= 3 && j >= 0 && (baris != i || kolom != j)){
                    if(papan[i][j].getBidak().getValue() == 5 && papan[i][j].getBidak().isWhite() != turnP1){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public Boolean isValid(int i, int j,Papan[][] papan, int baris, int kolom){
        Papan[][] tempPapan = new Papan[8][4];
        // Mbuat papan bersih baru
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 4; l++) {
                tempPapan[k][l] = new Papan(new Bidak(papan[k][l].getBidak().getValue(),papan[k][l].getBidak().isWhite()),virtualIV,"#FFFFFF");
            }
        }

        tempPapan[i][j].enPassant = tempPapan[baris][kolom].enPassant;
        tempPapan[i][j].setBidak(new Bidak(tempPapan[baris][kolom].getBidak().getValue(),tempPapan[baris][kolom].getBidak().isWhite()));
        if(tempPapan[i][j].getBidak().getValue() == 1 && Math.abs(baris-i) == 2){
            tempPapan[i][j].enPassant = true;
        }
        else {
            tempPapan[i][j].enPassant = false;
            for (int k = 0; k < 8; k++) {
                for (int l = 0; l < 4; l++) {
                    tempPapan[k][l].enPassant = false;
                }
            }
        }
        tempPapan[baris][kolom].setBidak(new Bidak(0,false));
        tempPapan[i][j].untouched = false;
        tempPapan[baris][kolom].untouched = false;
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 4; l++) {
                if(tempPapan[k][l].getBidak().getValue() == 5 && tempPapan[k][l].getBidak().isWhite() == turnP1){
                    if(!isSave(k,l,tempPapan)){
//                        Toast.makeText(this, "Invalid Move", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    break;
                }
            }
        }
        return true;

    }

    // 1 : Pawn
    // 2 : Bishop
    // 3 : Knight
    // 4 : Queen
    // 5 : King
    public void canMove(int x, int y){
//        System.out.println("Can Move" + x +" - "+y );
        int baris = y;
        int kolom =x;
        //king
        if(papan[baris][kolom].getBidak().getValue() == 5){
            for (int i = baris-1; i < baris+2; i++) {
                for (int j = kolom-1; j < kolom+2; j++) {
                    if(i <= 7 && i >= 0 && j <= 3 && j >= 0 && (baris != i || kolom != j)){
                        if(papan[i][j].getBidak().getValue() == 0){
                            if(isValid(i,j,papan,baris,kolom)){
                                papan[i][j].setStatus(1);
                                canMoveCounter++;

                            }
                        }else if((!turnP1 && papan[i][j].getBidak().isWhite()) || (turnP1 && !papan[i][j].getBidak().isWhite())){
                            if(isValid(i,j,papan,baris,kolom)){
                                papan[i][j].setStatus(1);
                                canMoveCounter++;

                            }
                        }
                    }
                }
            }
        } // King
        else if(papan[baris][kolom].getBidak().getValue() == 4){
            Boolean kiriAtas = true;
            Boolean kananAtas = true;
            Boolean kiriBawah = true;
            Boolean kananBawah = true;
            //buat ngecek dee sudah nambrak 1 apa belom

            for(int i=1;i<=7;i++){
                //serong kiri atas
                // i pergerakan y  -> baris +- i
                // j pergerakan x  -> kolom +- i
                if(kolom - i >=0 && baris - i >=0 && kiriAtas){
                    if(papan[baris-i][kolom-i].getBidak().getValue() == 0
                    || papan[baris-i][kolom-i].getBidak().isWhite() != turnP1  ){
                      
                        if(isValid(baris-i,kolom-i,papan,baris,kolom)){
                            papan[baris-i][kolom-i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris-i][kolom-i].getBidak().getValue() != 0){
                                kiriAtas = false;
                                // ben kalo nabrak dee berhenti
                            }
                        }
                        
                    }else if(papan[baris-i][kolom-i].getBidak().isWhite() == turnP1 ){
                        kiriAtas = false;
                    }
                }

                //serong kanan bawah
                if(kolom + i <=3 && baris + i <=7 && kananBawah){
                    if(papan[baris+i][kolom+i].getBidak().getValue() == 0
                    || papan[baris+i][kolom+i].getBidak().isWhite() != turnP1 ){
                        if(isValid(baris+i,kolom+i,papan,baris,kolom)){
                            papan[baris+i][kolom+i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris+i][kolom+i].getBidak().getValue() != 0){
                                kananBawah = false;
                            }
                        }
                    }else if(papan[baris+i][kolom+i].getBidak().isWhite() == turnP1){
                        kananBawah = false;
                    }
                }

                //serong Kiri Bawh
                if(kolom - i >=0 && baris + i <=7 && kiriBawah){
                    if(papan[baris+i][kolom-i].getBidak().getValue() == 0
                    || papan[baris+i][kolom-i].getBidak().isWhite() != turnP1 ){
                        if(isValid(baris+i,kolom-i,papan,baris,kolom)){
                            papan[baris+i][kolom-i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris+i][kolom-i].getBidak().getValue() != 0){
                                kiriBawah = false;
                            }
                        }
                    }else if(papan[baris+i][kolom-i].getBidak().isWhite() == turnP1 ){
                        kiriBawah = false;
                    }
                }

                // kanan atas
                if(kolom + i <=3 && baris - i >=0 && kananAtas){
                    if(papan[baris-i][kolom+i].getBidak().getValue() == 0
                    || papan[baris-i][kolom+i].getBidak().isWhite() != turnP1 ){

                        if(isValid(baris-i,kolom+i,papan,baris,kolom)){
                            papan[baris-i][kolom+i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris-i][kolom+i].getBidak().getValue() != 0){
                                kananAtas = false;
                            }
                        }
                    }else if(papan[baris-i][kolom+i].getBidak().isWhite() == turnP1){
                        kananAtas = false;
                    }
                }
            }

            Boolean kiri = true;
            Boolean kanan = true;
            Boolean atas = true;
            Boolean bawah = true;
            // vertikal horizontal
            for (int i=1;i<=7;i++){
                if(kolom-i >= 0 && kiri){
                    if(papan[baris][kolom-i].getBidak().getValue() == 0 || papan[baris][kolom-i].getBidak().isWhite() != turnP1 ){
                        if(isValid(baris,kolom-i,papan,baris,kolom)){
                            papan[baris][kolom-i].setStatus(1);
                            canMoveCounter++;


                            if(papan[baris][kolom-i].getBidak().getValue() != 0){
                                kiri = false;
                            }
                        }
                    }else if(papan[baris][kolom-i].getBidak().isWhite() ==turnP1){
                        kiri = false;
                    }
                }

                if(kolom+i <= 3 && kanan){
                    if(papan[baris][kolom+i].getBidak().getValue() == 0 || papan[baris][kolom+i].getBidak().isWhite() != turnP1 ){
                        if(isValid(baris,kolom+i,papan,baris,kolom)){
                            papan[baris][kolom+i].setStatus(1);
                            canMoveCounter++;


                            if(papan[baris][kolom+i].getBidak().getValue() != 0){
                                kanan = false;
                            }
                        }
                    }else if(papan[baris][kolom+i].getBidak().isWhite() == turnP1){
                        kanan = false;
                    }
                }

                if(baris-i >= 0 && atas){
                    if(papan[baris-i][kolom].getBidak().getValue() == 0 || papan[baris-i][kolom].getBidak().isWhite() !=turnP1 ){
                        if(isValid(baris-i,kolom,papan,baris,kolom)){
                            papan[baris-i][kolom].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris-i][kolom].getBidak().getValue() != 0){
                                atas = false;
                            }
                        }
                    }else if(papan[baris-i][kolom].getBidak().isWhite() == turnP1){
                        atas = false;
                    }
                }

                if(baris+i <= 7 && bawah){
                    if(papan[baris+i][kolom].getBidak().getValue() == 0 || papan[baris+i][kolom].getBidak().isWhite() != turnP1 ){
                        if(isValid(baris+i,kolom,papan,baris,kolom)){
                            papan[baris+i][kolom].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris+i][kolom].getBidak().getValue() != 0){
                                bawah = false;
                            }
                        }
                    }else if(papan[baris+i][kolom].getBidak().isWhite() == turnP1){
                        bawah = false;
                    }
                }

            }
        } //Queen
        else if(papan[baris][kolom].getBidak().getValue() == 3){
            //knight
            int[] moveX = new int[]{-1,1,2,2,1,-1,-2,-2};
            int[] moveY = new int[]{-2,-2,-1,1,2,2,1,-1};
            for(int i=0;i<8;i++){
                if(x+moveX[i]>=0 && y+moveY[i]>=0 && x+moveX[i]<=3 && y+moveY[i]<=7){
                    if(papan[y+moveY[i]][x+moveX[i]].getBidak().getValue()==0 || papan[y+moveY[i]][x+moveX[i]].getBidak().isWhite() != turnP1 ){
                        if(isValid(y+moveY[i],x+moveX[i],papan,baris,kolom)){
                            papan[y+moveY[i]][x+moveX[i]].setStatus(1);
                            canMoveCounter++;

                        }
                    }
                }
            }
        } //Knight
        else if(papan[baris][kolom].getBidak().getValue() == 2){
            //bishop
            Boolean kiriAtas = true;
            Boolean kananAtas = true;
            Boolean kiriBawah = true;
            Boolean kananBawah = true;
            //buat ngecek dee sudah nambrak 1 apa belom

            for(int i=1;i<=7;i++){
                //serong kiri atas
                // i pergerakan y  -> baris +- i
                // j pergerakan x  -> kolom +- i
                if(kolom - i >=0 && baris - i >=0 && kiriAtas){
                    if(papan[baris-i][kolom-i].getBidak().getValue() == 0
                            || papan[baris-i][kolom-i].getBidak().isWhite() != turnP1  ){

                        if(isValid(baris-i,kolom-i,papan,baris,kolom)){
                            papan[baris-i][kolom-i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris-i][kolom-i].getBidak().getValue() != 0){
                                kiriAtas = false;
                                // ben kalo nabrak dee berhenti
                            }
                        }

                    }else if(papan[baris-i][kolom-i].getBidak().isWhite() == turnP1 ){
                        kiriAtas = false;
                    }
                }

                //serong kanan bawah
                if(kolom + i <=3 && baris + i <=7 && kananBawah){
                    if(papan[baris+i][kolom+i].getBidak().getValue() == 0
                            || papan[baris+i][kolom+i].getBidak().isWhite() != turnP1 ){
                        if(isValid(baris+i,kolom+i,papan,baris,kolom)){
                            papan[baris+i][kolom+i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris+i][kolom+i].getBidak().getValue() != 0){
                                kananBawah = false;
                            }
                        }
                    }else if(papan[baris+i][kolom+i].getBidak().isWhite() == turnP1){
                        kananBawah = false;
                    }
                }

                //serong Kiri Bawh
                if(kolom - i >=0 && baris + i <=7 && kiriBawah){
                    if(papan[baris+i][kolom-i].getBidak().getValue() == 0
                            || papan[baris+i][kolom-i].getBidak().isWhite() != turnP1 ){
                        if(isValid(baris+i,kolom-i,papan,baris,kolom)){
                            papan[baris+i][kolom-i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris+i][kolom-i].getBidak().getValue() != 0){
                                kiriBawah = false;
                            }
                        }
                    }else if(papan[baris+i][kolom-i].getBidak().isWhite() == turnP1 ){
                        kiriBawah = false;
                    }
                }

                // kanan atas
                if(kolom + i <=3 && baris - i >=0 && kananAtas){
                    if(papan[baris-i][kolom+i].getBidak().getValue() == 0
                            || papan[baris-i][kolom+i].getBidak().isWhite() != turnP1 ){

                        if(isValid(baris-i,kolom+i,papan,baris,kolom)){
                            papan[baris-i][kolom+i].setStatus(1);
                            canMoveCounter++;

                            if(papan[baris-i][kolom+i].getBidak().getValue() != 0){
                                kananAtas = false;
                            }
                        }
                    }else if(papan[baris-i][kolom+i].getBidak().isWhite() == turnP1){
                        kananAtas = false;
                    }
                }
            }

        } //Bishop
        else if(papan[baris][kolom].getBidak().getValue() == 1){
//            Toast.makeText(this, "Pawn", Toast.LENGTH_SHORT).show();
            //pawn
            if(turnP1 && y-1>=0){ //white move up
                if(papan[y-1][x].getBidak().getValue()==0){
//                    isValid(baris-1,kolom,papan,baris,kolom)
                    if (isValid(y-1,kolom,papan,baris,kolom)){
                        papan[y-1][x].setStatus(1);
                        canMoveCounter++;

                        //;
                    }

                }
                //langka awal
                if(y==6 && papan[y-1][x].getBidak().getValue()==0){
//                    isValid(y-1,kolom,papan,baris,kolom)
                    if(isValid(y-1,x,papan,baris,kolom)){
                        papan[y-1][x].setStatus(1);
                        canMoveCounter++;

                        //;
                    }
                    if(papan[y-2][x].getBidak().getValue()==0){
                        if(isValid(y-2,x,papan,baris,kolom)){
                            papan[y-2][x].setStatus(1);
                            canMoveCounter++;

                            //;

                        }
                    }
                }
                //Makan
                if(x-1 >= 0 && papan[y-1][x-1].getBidak().getValue()!=0 && !papan[y-1][x-1].getBidak().isWhite()){
                    if(isValid(y-1,x-1,papan,baris,kolom)){
                        papan[y-1][x-1].setStatus(1);
                        canMoveCounter++;

                        //;
                    }
                }
                if(x+1 <= 3 && papan[y-1][x+1].getBidak().getValue()!=0 && !papan[y-1][x+1].getBidak().isWhite() ){
                    if(isValid(y-1,x+1,papan,baris,kolom)){
                        papan[y-1][x+1].setStatus(1);
                        canMoveCounter++;

                        //;
                    }
                }

            }
            if(!turnP1 && y+1<=7){ // black move down
                if(papan[y+1][x].getBidak().getValue() == 0){
                    if(isValid(y+1,x,papan,baris,kolom)){
                        papan[y+1][x].setStatus(1);
                        canMoveCounter++;

                        //;
                    }
                }

                if(y==1 && papan[y+1][x].getBidak().getValue()==0){
                    if(isValid(y+1,x,papan,baris,kolom)){
                        papan[y+1][x].setStatus(1);
                        canMoveCounter++;

                        //;
                    }
                    if(papan[y+2][x].getBidak().getValue()==0){
                        if(isValid(y+2,x,papan,baris,kolom)){
                            papan[y+2][x].setStatus(1);
                            canMoveCounter++;

                            //;
                        }
                    }
                }

                //Makan
                if(x-1 >= 0 && papan[y+1][x-1].getBidak().getValue()!=0 && papan[y+1][x-1].getBidak().isWhite() ){
                    if(isValid(y+1,x-1,papan,baris,kolom)){
                        papan[y+1][x-1].setStatus(1);
                        canMoveCounter++;

                        //;
                    }
                }
                if(x+1 <= 3 && papan[y+1][x+1].getBidak().getValue()!=0 && papan[y+1][x+1].getBidak().isWhite() ){
                    if(isValid(y+1,x+1,papan,baris,kolom)){
                        papan[y+1][x+1].setStatus(1);
                        canMoveCounter++;

                        //;
                    }
                }
            }
        } // Pawn
    }

    // abis ganti player langsung cekWin
    // abis p2 turn cekWin kalo P1 move e 0 dee kalah
    public boolean cekWin(){
        boolean win=false;
        boolean check = false;
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(papan[i][j].getBidak().getValue()!=0 && papan[i][j].getBidak().isWhite() == turnP1){
                    canMove(j,i);
                    if (papan[i][j].getBidak().getValue()==5 && !isSave(i,j,papan)){
                        check = true;
                        if(turnP1){
                            tvTurn.setText("Check , Player 1 Turn");
                        }else{
                            tvTurn.setText("Check , Player 2 Turn");
                        }
                    }
                }
            }
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
               papan[i][j].setStatus(0);
            }
        }
        if(canMoveCounter == 0 && !check){ //draw
            status = 0;
            tvTurn.setText("Draw StaleMate");
        }else if(canMoveCounter==0){
            win=true;
            if(turnP1){
                status = 2; // BlackWIn
                tvTurn.setText("Black Win");

            }else{
                status = 1; // whiteWin
                tvTurn.setText("White Win");
            }
        }
        canMoveCounter=0;
        return win;
    }
    void AiDoMove(int x, int y, int i, int j)
    {


         if (j == 7 && papan[y][x].getBidak().getValue() == 1 && !papan[y][x].getBidak().isWhite()) {
            papan[j][i].setBidak(new Bidak(4, papan[y][x].getBidak().isWhite()));
            papan[y][x].setBidak(new Bidak(0, false));
        } else {
            papan[j][i].setBidak(new Bidak(papan[y][x].getBidak().getValue(), papan[y][x].getBidak().isWhite()));
            papan[y][x].setBidak(new Bidak(0, false));
        }

    }
    public void clickImg(View v) {
        int y = 0, x = 0;
        if (status == -1) { // masih main
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    if (v.getId() == papan[i][j].letak.getId()) {
                        y = i;
                        x = j;
                        break;
                    }
                }
            }

            if (selectY == -1 && selectX == -1) {
                if (papan[y][x].getBidak().getValue() != 0) { // nde papan e ono bidak e
                    if (papan[y][x].getBidak().isWhite() == turnP1) { // bidak e punya e dee
                        selectX = x;
                        selectY = y;
                        canMove(x, y); // y , x
                    } else {
                        Toast.makeText(this, "Not Your Turn ", Toast.LENGTH_SHORT).show();
                    }
                }
            } else { // player lagi nyelek papan
                if (papan[y][x].getStatus() == 1) { //Move
                    if (y == 0 && papan[selectY][selectX].getBidak().getValue() == 1 && papan[selectY][selectX].getBidak().isWhite()) {
                        papan[y][x].setBidak(new Bidak(4, papan[selectY][selectX].getBidak().isWhite()));
                        papan[selectY][selectX].setBidak(new Bidak(0, false));
                    } else if (y == 7 && papan[selectY][selectX].getBidak().getValue() == 1 && !papan[selectY][selectX].getBidak().isWhite()) {
                        papan[y][x].setBidak(new Bidak(4, papan[selectY][selectX].getBidak().isWhite()));
                        papan[selectY][selectX].setBidak(new Bidak(0, false));
                    } else {
                        papan[y][x].setBidak(new Bidak(papan[selectY][selectX].getBidak().getValue(), papan[selectY][selectX].getBidak().isWhite()));
                        papan[selectY][selectX].setBidak(new Bidak(0, false));
                    }
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 4; j++) {
                            papan[i][j].setStatus(0);
                        }
                    }
                    canMoveCounter = 0;
                    turnP1 = !turnP1;

                        if (turnP1) {
                            tvTurn.setText("Player 1 Turn");
                        } else {
                            tvTurn.setText("Player 2 Turn");
                        }
                        cekWin();
                        // check win


                    } else { // Salah Klik
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 4; j++) {
                                papan[i][j].setStatus(0);
                            }
                        }
                        canMoveCounter = 0;
                        selectX = -1;
                        selectY = -1;
                    }

                if (vsAI) {
                    if (!turnP1) {
                        AI.Move move = AI.AIBehaviour.minimaxRoot(papan, 3, false);
                        if (move.getSrcy() != -1 || move.getSrcx() != -1 || move.getDestx() != -1 || move.getDesty() != -1) {
                            AiDoMove(move.getSrcx(), move.getSrcy(), move.getDestx(), move.getDesty());
                            System.out.println("Move AI");
                        }
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 4; j++) {
                                papan[i][j].setStatus(0);
                            }
                        }
                        canMoveCounter = 0;
                        selectX = -1;
                        selectY = -1;
                        turnP1 = !turnP1;
                        if (turnP1) {
                            tvTurn.setText("Player 1 Turn");
                        } else {
                            tvTurn.setText("Player 2 Turn");
                        }
                        cekWin();
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_test:
                int depth = 1;
                AI.AIBehaviour.debug = virtualIV;
//                ArrayList<AI.Move> listMove = AI.AIBehaviour.getAllPossibleMove(papan, turnP1);
//                papan[y][x].setBidak(new Bidak(papan[selectY][selectX].getBidak().getValue(), papan[selectY][selectX].getBidak().isWhite()));
//                papan[selectY][selectX].setBidak(new Bidak(0, false));
//                int debug = 2;
//                papan[listMove.get(debug).getDesty()][listMove.get(debug).getDestx()].setBidak(new Bidak(papan[listMove.get(debug).getSrcy()][listMove.get(debug).getSrcx()].getBidak().getValue() ,papan[listMove.get(debug).getSrcy()][listMove.get(debug).getSrcx()].getBidak().isWhite() ));
//                papan[listMove.get(debug).getSrcy()][listMove.get(debug).getSrcx()].setBidak(new Bidak(0, false));
                AI.Move move = AI.AIBehaviour.minimaxRoot(papan, depth, false);
                if (move.getSrcy() != -1 || move.getSrcx() != -1 || move.getDestx() != -1 || move.getDesty() != -1)
                {
                    AiDoMove(move.getSrcx(), move.getSrcy(), move.getDestx(), move.getDesty());
                }
                for(int i=0;i<8;i++)
                {
                    for (int j=0;j<4;j++)
                    {
                        papan[i][j].setStatus(0);
                    }
                }
                turnP1 = !turnP1;
//                System.out.println();
                break;
            case R.id.item_reset:
                turnP1 = true;
                for(int i = 0 ; i < 8 ; i++ ) {
                    for(int j = 0 ; j < 4 ; j++) {
                        tiles[i][j].setImageResource(0);
                    }
                }
//                resetMap();
                setPapan();
                status=-1;
//                setBidak();
                cmTimer.setBase(SystemClock.elapsedRealtime());
                cmTimer.stop();
                cmTimer2.setBase(SystemClock.elapsedRealtime());
                cmTimer2.stop();
                if(turnP1){
                    cmTimer.setBase(SystemClock.elapsedRealtime());
                    cmTimer.start();
                }
                else{
                    cmTimer2.setBase(SystemClock.elapsedRealtime());
                    cmTimer2.start();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//
}