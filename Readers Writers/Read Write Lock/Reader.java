package project;

public class Reader extends Thread {
    private int scale;
    private Buffer buff;

    public Reader(Buffer buff, int s){          //constructor
        scale = s;                              //arxikopoihsh metablhtwn
        this.buff = buff;
    }

    public void run(){
        while (true){
            try {
                sleep((int)(Math.random()*scale));                  //perimene
                buff.startRead(Thread.currentThread().getName());   //ksekina na diabazeis
                sleep((int)(Math.random()*scale));                  //perimene
                buff.endRead(Thread.currentThread().getName());     //stamata na diabazeis
            }catch (InterruptedException e) {}
        }
    }
}