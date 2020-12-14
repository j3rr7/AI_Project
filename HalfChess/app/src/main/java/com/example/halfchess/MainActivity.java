package com.example.halfchess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {


    public static ImageView[][] tiles = new ImageView[8][4];
    public static Boolean[][] markCheck = new Boolean[8][4];
    public static Boolean[][] markCheck2 = new Boolean[8][4];
    public static Papan[][] papan = new Papan[8][4];

    public static Boolean[][] markSimulation = new Boolean[8][4];
    public static Boolean[][] markSimulation2 = new Boolean[8][4];
    public static Papan[][] papanSimulation = new Papan[8][4];
    public static Bidak[] bidakP1Simulation = new Bidak[8]; //
    public static Bidak[] bidakP2Simulation = new Bidak[8]; //
    //lets pray for our simulation

//    public static Boolean[][] markedArea = new Boolean[8][4]; // semua jalan sg iso dilewati musuh
                                                            // king gabole lewat sini soal e skak
    public static Bidak[] bidakP1 = new Bidak[8]; //0 King , 1 Queen , 2 Bishop , 3 Kuda , sisa e pawn dari kiri
    public static Bidak[] bidakP2 = new Bidak[8]; // podo
    public static Player p1 ;
    public static Player p2 ;
    Boolean turnP1;
    Papan temp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImageView();
        setPapan();
        setBidak();
        resetMarkedArea();

        p1 = new Player(bidakP1);
        p2 = new Player(bidakP2);

        turnP1 = true;

    }


    public static void setSimulationArea(){
        MainActivity.markSimulation = new Boolean[8][4];
        MainActivity.markSimulation2 = new Boolean[8][4];
        MainActivity.papanSimulation = new Papan[8][4];
        MainActivity.bidakP1Simulation = new Bidak[8]; //
        MainActivity.bidakP2Simulation = new Bidak[8]; //


        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                markSimulation[i][j]=false;
                markSimulation2[i][j]=false;
            }
        }
//        MainActivity.markSimulation = MainActivity.markCheck;
//        MainActivity.markSimulation2 = MainActivity.markCheck2;
        MainActivity.papanSimulation = MainActivity.papan;
        MainActivity.bidakP1Simulation = MainActivity.bidakP1;
        MainActivity.bidakP2Simulation = MainActivity.bidakP2;
    }

    public static Boolean markSimulationArea(Boolean isTurnP1){

        for(int i=0;i<8;i++){
            if(isTurnP1){ // nde mark simulation cek daerah sendiri ae
//                System.out.println("Turn P1 Simulation Area");
                if(bidakP2Simulation[i] != null){
//                    Bidak temp = bidakP2Simulation[i];

                    Boolean check = bidakP2Simulation[i].markSimulation.Mark(bidakP2Simulation[i].isP1(),bidakP2Simulation[i].getX(),bidakP2Simulation[i].getY());
                    System.out.println(i+ " - Loop Bidak P2 Simulaation : "+check);
                    if (check){
                        System.out.println("P1 got check");
                        return check;
                    }
                }
            }else{
                if(bidakP1Simulation[i] != null){
                    Bidak temp = bidakP1Simulation[i];
                    Boolean check = bidakP1Simulation[i].markSimulation.Mark(temp.isP1(),temp.getX(),temp.getY());
                    if(check){
                        System.out.println("P2 got check");
                        return check;
                    }
                }
            }

        }

        return false;
    }


    public static void markArea(){
        for(int i=0;i<8;i++){
            if(bidakP1[i]!=null){
                bidakP1[i].mark.Mark(true,bidakP1[i].getX(),bidakP1[i].getY());
            }
            if(bidakP2[i]!=null){
                bidakP2[i].mark.Mark(false,bidakP2[i].getX(),bidakP2[i].getY());
            }
        }
    }

    public void resetMarkedArea(){
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
               markCheck[i][j]=false;
               markCheck2[i][j]=false;
            }
        }
    }
    public static Boolean cekCheckSimulation(Boolean p1, int x ,int y){

        if(y>=0 && x>=0 && y<8 && x<4){
            if(MainActivity.papanSimulation[y][x].getBidak() != null
                    && MainActivity.papanSimulation[y][x].getBidak().isP1()!=p1
                    && MainActivity.papanSimulation[y][x].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")){
                if(p1){
                    System.out.println("Check P2");
                    return true;
                }else{
                    System.out.println("Check P1");
                    return true;
                }
            }
        }
        return false;
    }


    public static Boolean cekCheck(Boolean p1, int x ,int y){

        if(y>=0 && x>=0 && y<8 && x<4){
            if(MainActivity.papan[y][x].getBidak() != null
                    && MainActivity.papan[y][x].getBidak().isP1()!=p1
                        && MainActivity.papan[y][x].getBidak().getClass().getSimpleName().equalsIgnoreCase("King")){
                if(p1){
                    System.out.println("Check P2");
                    return true;
                }else{
                    System.out.println("Check P1");
                    return true;
                }
            }
        }
        return false;
    }

    public void setBidak(){
        King k = new King(papan[7][0],true);
        King k2 = new King(papan[0][0],false);
        bidakP1[0] = k;
        bidakP2[0] = k2;
        papan[7][0].setBidak(bidakP1[0]);
        papan[0][0].setBidak(bidakP2[0]);

        Queen q = new Queen(papan[7][1],true);
        Queen q2 = new Queen(papan[0][1],false);
        bidakP1[1] = q;
        bidakP2[1] = q2;
        papan[7][1].setBidak(bidakP1[1]);
        papan[0][1].setBidak(bidakP2[1]);




//        Bishop b = new Bishop(papan[7][2],true);
//        Bishop b2 = new Bishop(papan[0][2],false);
//        bidakP1[2] = b;
//        bidakP2[2] = b2;
//        papan[7][2].setBidak(bidakP1[2]);
//        papan[0][2].setBidak(bidakP2[2]);
//
//
//        Knight kuda = new Knight(papan[7][2],true);
//        Knight kuda2 = new Knight(papan[0][2],false);
//        bidakP1[3] = kuda;
//        bidakP2[3] = kuda2;
//        papan[7][3].setBidak(bidakP1[3]);
//        papan[0][3].setBidak(bidakP2[3]);
//
//        //white move up
//        Pawn p1 = new Pawn(papan[1][0],false);
//        Pawn p2 = new Pawn(papan[1][1],false);
//        Pawn p3 = new Pawn(papan[1][2],false);
//        Pawn p4 = new Pawn(papan[1][3],false);
//        bidakP2[4] = p1;
//        bidakP2[5] = p2;
//        bidakP2[6] = p3;
//        bidakP2[7] = p4;
//        papan[1][0].setBidak(bidakP2[4]);
//        papan[1][1].setBidak(bidakP2[5]);
//        papan[1][2].setBidak(bidakP2[6]);
//        papan[1][3].setBidak(bidakP2[7]);
//
//
//         p1 = new Pawn(papan[6][0],true);
//         p2 = new Pawn(papan[6][1],true);
//         p3 = new Pawn(papan[6][2],true);
//         p4 = new Pawn(papan[6][3],true);
//        bidakP1[4] = p1;
//        bidakP1[5] = p2;
//        bidakP1[6] = p3;
//        bidakP1[7] = p4;
//
//        papan[6][0].setBidak(bidakP1[4]);
//        papan[6][1].setBidak(bidakP1[5]);
//        papan[6][2].setBidak(bidakP1[6]);
//        papan[6][3].setBidak(bidakP1[7]);


        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(papan[i][j].getBidak()!=null){
                    tiles[i][j].setImageResource(papan[i][j].getBidak().getImg());
                }
            }
        }
    }

    public void showBidak(){

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


        if(papan[y][x].getBidak()!=null && temp==null){ // ada bidak e
            Boolean isP1 = papan[y][x].getBidak().isP1();
            System.out.println(papan[y][x].getBidak().getClass().getSimpleName()+"");
//            Toast.makeText(this,  papan[y][x].getBidak().getClass().getSimpleName()+"", Toast.LENGTH_SHORT).show();
            // iki harus e ngefor semua bidak sg gak null ,just fine still testing
            resetMarkedArea();
            markArea();

            // untuk gerak checked e dikasih nde bahaviour
            if(isP1 && turnP1){ //player 1 turn
                System.out.println("Player1 turn");
                if(papan[y][x].getBidak().getMove().Pickup(isP1,x,y)){
                    temp = papan[y][x];
                }
            }else if(!isP1 && !turnP1){ // player  2 turn
                System.out.println("Player2 turn");
                if(papan[y][x].getBidak().getMove().Pickup(isP1,x,y)){
                    temp = papan[y][x];
                }
            }

        }else if (colorId == Color.GREEN){
            // pindah mbe ganti player e
            // makan bidak e lwan berarti ngurangi 1 di array e
            if(papan[y][x].getBidak()!=null){
                System.out.println("Makan");
                for (int i=0;i<8;i++){
                    if(turnP1){
                        if(bidakP2[i]!=null && bidakP2[i].getX() == x && bidakP2[i].getY() == y){ // thats the bidak  , u must chhange the location
                            System.out.println("makan turn p1");
                            bidakP2[i] = null;
                        }

                    }else{

                        if(bidakP1[i]!=null && bidakP1[i].getX() == x && bidakP1[i].getY() == y){ // thats the bidak  , u must chhange the location
                            System.out.println("makan turn p2");
                            bidakP1[i] = null;
                        }
                    }
                }
            }

            // set bidak di papan buat tampilan
            System.out.println("Y : "+y);
            System.out.println("X : "+x);
            System.out.println("TempY : "+temp.getY());
            System.out.println("TempX : "+temp.getX());


            papan[y][x].setBidak(temp.getBidak());
            tiles[y][x].setImageResource(papan[y][x].getBidak().getImg());

//            papan[temp.getY()][temp.getX()].setBidak(null);
            papan[temp.getY()][temp.getX()] = new Papan(temp.getX(),temp.getY(),tiles[temp.getY()][temp.getX()].getId());
            tiles[temp.getY()][temp.getX()].setImageResource(0);



            // pindah lokasi e bidak sg di array , soal e kalo ga dipindah error nde pengecekan error nanti
            for (int i=0;i<8;i++){
                if(turnP1){
                    if(bidakP1[i]!=null && bidakP1[i].getX() == temp.getX() && bidakP1[i].getY() ==temp
                    .getY()){ // thats the bidak  , u must chhange the location
                        System.out.println("set bidak p1");
                        bidakP1[i].setX(x);
                        bidakP1[i].setY(y);
                        System.out.println("X:"+bidakP1[i].getX()+"|Y:"+bidakP1[i].getY());
                    }

                }else{
                    if(bidakP2[i]!=null && bidakP2[i].getX() == temp.getX() && bidakP2[i].getY() ==temp
                            .getY()){ // thats the bidak  , u must chhange the location
                        System.out.println("set bidak p2");
                        bidakP2[i].setX(x);
                        bidakP2[i].setY(y);
                        System.out.println("X:"+bidakP2[i].getX()+"|Y:"+bidakP2[i].getY());
                    }
                }
            }




            temp = null;
            turnP1 = !turnP1; // buat ganti player sg maenno
            //abis aku pindah apakah king itu terancam ??
            // di class e bidak kasih void isCheck() ???

            resetMarkedArea();
            markArea();
            resetMap();

        }else if(temp!=null && colorId!=Color.GREEN){ // batal
            temp = null;
            resetMap();
            resetMarkedArea();
        }else{
            temp = null;
            resetMap();
            resetMarkedArea();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_reset:
                turnP1 = true;
                for(int i = 0 ; i < 8 ; i++ ) {
                    for(int j = 0 ; j < 4 ; j++) {
                        tiles[i][j].setImageResource(0);
                    }
                }
                resetMap();
                setPapan();
                setBidak();
                break;

            case R.id.markArea:
//                Toast.makeText(this, "Mark", Toast.LENGTH_SHORT).show();
//                resetMarkedArea();
//                bidakP1[1].mark.Mark(true,bidakP1[1].getX(),bidakP1[1].getY());
//                bidakP2[1].mark.Mark(false,bidakP2[1].getX(),bidakP2[1].getY());
                System.out.println("Marked");
                for(int i=0;i<8;i++){
                    for(int j=0;j<4;j++){
                        if(markCheck[i][j]==false){
                            System.out.print("[ ]");
                        }else{
                            System.out.print("[O]");
                        }
                    }
                    System.out.println("");
                }

                System.out.println("Marked2");
                for(int i=0;i<8;i++){
                    for(int j=0;j<4;j++){
                        if(markCheck2[i][j]==false){
                            System.out.print("[ ]");
                        }else{
                            System.out.print("[O]");
                        }
                    }
                    System.out.println("");
                }
                resetMarkedArea();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Boolean checkSimulation(Boolean p1,int x,int y,int newX,int newY){
        Boolean check=false;
//        Papan[][] tempPapan = new Papan[8][4];
        Bidak[] tempBidak1 =  new Bidak[8];
        Bidak[] tempBidak2 =  new Bidak[8];
//        tempPapan = papan;
        tempBidak1 = bidakP1;
        tempBidak2 = bidakP2;

        int index =0;
        for(int i=0;i<8;i++){
            if (p1){
                if(tempBidak1[i]!=null && tempBidak1[i].getX() ==x  && tempBidak1[i].getY() == y){
                    index = i;
            }else{
                }if(tempBidak2[i]!=null && tempBidak2[i].getX() == x  && tempBidak2[i].getY() == y){
                    index = i;
                }
            }
        }

        //mindah bidak e
        if (p1){
            tempBidak1[index].setX(newX);
            tempBidak1[index].setY(newY);
        }else{
            tempBidak2[index].setX(newX);
            tempBidak2[index].setY(newY);
        }

        //do marks


        return  check;
    }
}