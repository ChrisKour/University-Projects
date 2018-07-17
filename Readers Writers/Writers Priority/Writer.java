package project;

public class Writer extends Thread {
    private int scale;
    private Buffer buff;

    public Writer(Buffer buff, int s){  //constructor
        scale = s;                      //arxikopoihsh metablhtwn
        this.buff = buff;
    }

    public void run(){
        while (true){                                               //atermwn brogxos
            try {
                sleep((int)(Math.random()*scale));                  //perimene
                buff.startWrite(Thread.currentThread().getName());  //ksekina na grafeis
                sleep((int)(Math.random()*scale));                  //perimene
                buff.endWrite(Thread.currentThread().getName());    //stamata na grafeis
            }catch (InterruptedException e) {}
        }
    }
}
