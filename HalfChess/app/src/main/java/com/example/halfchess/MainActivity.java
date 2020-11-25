package com.example.halfchess;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static ImageView[][] tiles = new ImageView[8][4];
    public static Papan[][] papan = new Papan[8][4];
    public static Boolean[][] markedArea = new Boolean[8][4]; // semua jalan sg iso dilewati musuh
                                                            // king gabole lewat sini soal e skak

    public static Bidak[] bidakP1 = new Bidak[8]; //0 King , 1 Queen , 2 Bishop , 3 Kuda , sisa e pawn dari kiri
    public static Bidak[] bidakP2 = new Bidak[8]; // podo
    Boolean turnP1;
    Papan temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //iki ojok diutak atik
        setImageView();
        setPapan();
        setBidak();
        setMarkedArea();

        turnP1 = true;


        // kalo move , Papan di class e bidak sg diganti
        // Bidak sg nde papan diganti atau dihapus kalo ada
        // terus di gambar ulang

        //kalo ada bidak e , bikin temp papan;
        // nde tiles e diwarnai sg bisa dijalan in
        // kalo di klik itu ada warna e , bidak nde temp papan dipindah nde papan sg di klik
        // terus papan lama diilangi bidak e;

    }
    public void setMarkedArea(){
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
               markedArea[i][j]=false;
            }
        }
    }
    public Boolean cekCheck(){
        for(int i=4;i<=7;i++){
            bidakP1[i].mark.Mark(bidakP1[i].isP1(),bidakP1[i].getX(),bidakP1[i].getY());
            bidakP2[i].mark.Mark(bidakP2[i].isP1(),bidakP2[i].getX(),bidakP2[i].getY());
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(markedArea[i][j] == true){
                    System.out.println("J: "+j+"- I: "+i);
                }
            }

        }
        return false;
    }

    public void setBidak(){
        //white move up
        Pawn p1 = new Pawn(papan[1][0],false);
        Pawn p2 = new Pawn(papan[1][1],false);
        Pawn p3 = new Pawn(papan[1][2],false);
        Pawn p4 = new Pawn(papan[1][3],false);
        papan[1][0].setBidak(p1);
        papan[1][1].setBidak(p2);
        papan[1][2].setBidak(p3);
        papan[1][3].setBidak(p4);

        // isi ke array
        bidakP2[4] = p1;
        bidakP2[5] = p2;
        bidakP2[6] = p3;
        bidakP2[7] = p4;


         p1 = new Pawn(papan[6][0],true);
         p2 = new Pawn(papan[6][1],true);
         p3 = new Pawn(papan[6][2],true);
         p4 = new Pawn(papan[6][3],true);

        papan[6][0].setBidak(p1);
        papan[6][1].setBidak(p2);
        papan[6][2].setBidak(p3);
        papan[6][3].setBidak(p4);

        bidakP1[4] = p1;
        bidakP1[5] = p2;
        bidakP1[6] = p3;
        bidakP1[7] = p4;


        King k = new King(papan[7][0],true);
        King k2 = new King(papan[0][0],false);
        papan[7][0].setBidak(k);
        papan[0][0].setBidak(k2);

        bidakP1[0] = k;
        bidakP2[0] = k2;

        Queen q = new Queen(papan[7][1],true);
        Queen q2 = new Queen(papan[0][1],false);
        papan[7][1].setBidak(q);
        papan[0][1].setBidak(q2);

        bidakP1[1] = q;
        bidakP2[1] = q2;



        Bishop b = new Bishop(papan[7][3],true);
        Bishop b2 = new Bishop(papan[0][3],false);
        papan[7][2].setBidak(b);
        papan[0][2].setBidak(b2);

        Knight kuda = new Knight(papan[7][2],true);
        Knight kuda2 = new Knight(papan[0][2],false);
        papan[7][3].setBidak(kuda);
        papan[0][3].setBidak(kuda2);

        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(papan[i][j].getBidak()!=null){
                    tiles[i][j].setImageResource(papan[i][j].getBidak().getImg());
                }
            }
        }
    }
    public void resetMap(){ //gambar Ulang papan mbe bidak e
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){

               if((i+j) % 2  == 0){
                   tiles[i][j].setBackgroundColor(Color.WHITE);
               }else{
                   tiles[i][j].setBackgroundColor(Color.BLACK);
               }
            }
        }
    }
    public void clickImg(View v){

        ImageView view = (ImageView) v;
        ColorDrawable viewColor = (ColorDrawable) view.getBackground();
        int colorId = viewColor.getColor();

        int x=0,y=0;
        for(int i=0 ;i<8;i++){
            for(int j=0;j<4;j++){
                if(v.getId() == papan[i][j].getIdPapan()){
                    x=j;
                    y=i;
                }
            }
        }
        //kalo ada bidak e , bikin temp papan;
        // nde tiles e diwarnai sg bisa dijalan in
        // kalo di klik itu ada warna e , bidak nde temp papan dipindah nde papan sg di klik
        // terus papan lama diilangi bidak e;

        // klik cancel diklik ngawur selain ijo

        if(papan[y][x].getBidak()!=null && temp==null){ // ada bidak e
            Boolean isP1 = papan[y][x].getBidak().isP1();
            if(isP1 && turnP1){ //player 1 turn
                Toast.makeText(this, isP1+"", Toast.LENGTH_SHORT).show();
                if(papan[y][x].getBidak().getMove().Pickup(isP1,x,y)){
                    temp = papan[y][x];
                }

            }else if(!isP1 && !turnP1){ // player  2 turn
                Toast.makeText(this, isP1+"", Toast.LENGTH_SHORT).show();
                if(papan[y][x].getBidak().getMove().Pickup(isP1,x,y)){
                    temp = papan[y][x];
                }
            }
            // gerak e dee harus ngecek apa king e iki terancam
            // oh shit


           // ini buat ngewarnai papan e ben oleh di klik
            // pas nde kene artie ws dicek kabeh papan e lek dee iku ga bakal skak color e ijo

        }else if (colorId == Color.GREEN){
            // pindah mbe ganti player e
            papan[y][x].setBidak(temp.getBidak());
            tiles[y][x].setImageResource(temp.getBidak().getImg());

            papan[temp.getY()][temp.getX()].setBidak(null);
            tiles[temp.getY()][temp.getX()].setImageResource(0);
            temp = null;
            turnP1 = !turnP1; // buat ganti player sg maenno
            //abis aku pindah apakah king itu terancam ??
            // di class e bidak kasih void isCheck() ???
            resetMap();

        }else if(temp!=null && colorId!=Color.GREEN){ // batal
            temp = null;
            resetMap();
        }




    }

    public void setPapan(){
        for(int i=0 ;i<8;i++){
            for(int j=0;j<4;j++){
                papan[i][j] = new Papan(j,i,tiles[i][j].getId());
            }
        }
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

}