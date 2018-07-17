package project;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread{
    private int scale;
    private BlockingQueue<Message> queue;

    //Constructor
    public Consumer(BlockingQueue<Message> queue, int s){
        this.queue = queue;
        this.scale = s;
    }

    //Consumer runs for ever
    public void run(){ //den xreiazomaste synchronized ka8ws ta Blocking Queues einai thread safe
        try{
            Message msg;    //dhmiourgia antikeimenoy typou message
            while(true){    //o katanalwths prospa8ei diarkws na parei antikeimena
                sleep((int)(Math.random()*scale));
                msg = queue.take(); //pairnoume ena antikeimeno message apo th lista
                System.out.println("Removing. Item: " + msg.getMsg() + " by: " + Thread.currentThread().getName() + " remaining items: "+ queue.size());
            }
        }catch (InterruptedException e){}
    }
}
