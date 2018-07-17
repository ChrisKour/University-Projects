package project;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread{
    private int reps;
    private int scale;
    private BlockingQueue<Message> queue;

    //Constructor
    public Producer(BlockingQueue<Message> queue, int r, int s){
        this.queue = queue;
        this.reps = r;
        this.scale = s;
    }

    //Producer runs for reps times with random (scale) intervals
    @Override
    public void run(){ //den xreiazomaste synchronized ka8ws ta blocking queues einai thread safe
        for(int i=0; i<reps; i++){
            Message msg = new Message("" + i + ","+ Thread.currentThread().getId()); //dhmiourgia antikeimenou typou message
            try{
                queue.put(msg); //bazoyme to antikeimeno mesa sth lista
                System.out.println("Adding. Item: " + msg.getMsg());
                sleep((int) (Math.random() * scale));
            }catch (InterruptedException e){}
        }
    }
}
