package project;

public class Writer extends Thread {
    private int scale;
    private Buffer buff;
    private long stamp;         //metablhth gia ta stamps pou paragoun ta locks

    public Writer(Buffer buff, int s){  //constructor
        scale = s;                      //arxikopoihsh metablhtwn
        this.buff = buff;
    }

    public void run(){
        while (true){                                                       //atermwn brogxos
            try {
                sleep((int)(Math.random()*scale));                          //perimene
                stamp = buff.startWrite(Thread.currentThread().getName());  //ksekina na grafeis
                sleep((int)(Math.random()*scale));                          //perimene
                buff.stopWrite(Thread.currentThread().getName(), stamp);    //stamata na grafeis
            }catch (InterruptedException e) {}
        }
    }
}
