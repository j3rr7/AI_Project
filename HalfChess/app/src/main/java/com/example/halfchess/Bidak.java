package com.example.halfchess;

public abstract class Bidak {

    MovementBehavior move;
    MarkArea mark;
    MarkSimulation markSimulation;
    Papan papan;
    boolean p1; // 1 punya e p1 0 p2
    int img;

    // PS (JERE) : setiap jenis bidak diberi value ToDo Add value to every bidak
    int value;

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


    private Bidak findBidakSimulation(){

        // ngereset dulu semua papan bidak dll simulation ngerefrence ke yang asli
        // soal e abis dipindah anggepan e kudu dibalekno awal maneh
        MainActivity.setSimulationArea();

        int index =0 ;
        for(int i=0;i<8;i++){
            if(MainActivity.bidakP1Simulation[i]!=null){
                if(MainActivity.bidakP1Simulation[i].getX() == this.getX() && MainActivity.bidakP1Simulation[i].getY() ==this.getY()){
                    index = i;
                }
                if(MainActivity.bidakP2Simulation[i].getX() == this.getX() && MainActivity.bidakP2Simulation[i].getY() ==this.getY()){
                    index = i;
                }
            }
        }

        if(this.p1){
            return MainActivity.bidakP1Simulation[index];
        }else{
            return MainActivity.bidakP2Simulation[index];
        }
    }

    private int findBidakSimulationIndex(){
        int index =0 ;
        for(int i=0;i<8;i++){
            if(MainActivity.bidakP1Simulation[i]!=null){
                if(MainActivity.bidakP1Simulation[i].getX() == this.getX() && MainActivity.bidakP1Simulation[i].getY() ==this.getY()){
                    index = i;
                }
                if(MainActivity.bidakP2Simulation[i].getX() == this.getX() && MainActivity.bidakP2Simulation[i].getY() ==this.getY()){
                    index = i;
                }
            }
        }


        return  index;
    }
    public Boolean MoveSimulation(int x,int y){

        // tujuan e simulation iki buat   bidak e seolah olah pindah posisi
//        System.out.println("Simulation X :"+x +" - Y:"+y); // ini x y tujuan

        Bidak temp = findBidakSimulation();
        int indexBidakSimulation = findBidakSimulationIndex();
        System.out.println("Posisi Awal Bidak Simulation X : "+temp.getX() +" - Y:"+temp.getY());
        //  Gae makan
        if( MainActivity.papanSimulation[y][x].getBidak()!=null){
            System.out.println("Makan Simulation");
            for (int i=0;i<8;i++){
                if(this.p1 == true){
                    // makan bidak lawan nde simulation
                    if(MainActivity.bidakP2Simulation[i]!=null && MainActivity.bidakP2Simulation[i].getX() == x && MainActivity.bidakP2Simulation[i].getY() == y){ // thats the bidak  , u must chhange the location
                        System.out.println("makan turn p1");
                        MainActivity.bidakP2Simulation[i] = null;
                    }
                }else{

                    if(MainActivity.bidakP1Simulation[i]!=null && MainActivity.bidakP1Simulation[i].getX() == x && MainActivity.bidakP2Simulation[i].getY() == y){ // thats the bidak  , u must chhange the location
                        System.out.println("makan turn p2 Simulation");
                        MainActivity.bidakP1Simulation[i] = null;
                    }


                }
            }
        }
        // end of makan


        // Pindah Posisi , papan yang diisi
        if(this.p1 == true){
            System.out.println("Set Papan simulation bidak 1");
            MainActivity.papanSimulation[y][x].setBidak(MainActivity.bidakP1Simulation[indexBidakSimulation]);
        }else{
            System.out.println("Set Papan simulation bidak 2");
            MainActivity.papanSimulation[y][x].setBidak(MainActivity.bidakP2Simulation[indexBidakSimulation]);
        }

        MainActivity.papanSimulation[this.getY()][this.getX()] = new Papan(this.getX(),this.getY(),MainActivity.tiles[this.getY()][this.getX()].getId());
        if(MainActivity.papanSimulation[this.getY()][this.getX()].getBidak() == null){
            System.out.println("Set Papan lokasi awal bidak jadi null");
        }
        //        // pindah lokasi e bidak sg di array , soal e kalo ga dipindah error nde pengecekan error nanti

        if(this.p1 == true){
            MainActivity.bidakP1Simulation[indexBidakSimulation].setY(y);
            MainActivity.bidakP1Simulation[indexBidakSimulation].setY(x);
            System.out.println("Set Posisi Simulasi Bidak ke X + "+x+" - Y :"+y );
        }else{
            MainActivity.bidakP2Simulation[indexBidakSimulation].setY(y);
            MainActivity.bidakP2Simulation[indexBidakSimulation].setY(x);
            System.out.println("Set Posisi Simulasi Bidak ke X + "+x+" - Y :"+y );
        }

        //end of pindah posisi
        return MainActivity.markSimulationArea(this.p1);

    }


}
