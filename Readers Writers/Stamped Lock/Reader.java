package project;

public class Reader extends Thread {
    private int scale;
    private Buffer buff;
    private long stamp;         //metablhth gia ta stamps pou paragoun ta locks

    public Reader(Buffer buff, int s){          //constructor
        scale = s;                              //arxikopoihsh metablhtwn
        this.buff = buff;
    }

    public void run(){
        while (true){                                                       //atermwn brogxos
            try {
                sleep((int)(Math.random()*scale));                          //perimene
                stamp = buff.startRead(Thread.currentThread().getName());   //ksekina na diabazeis
                sleep((int)(Math.random()*scale));                          //perimene
                buff.stopRead(Thread.currentThread().getName(),stamp);     //stamata na diabazeis
            }catch (InterruptedException e) {}
        }
    }
}