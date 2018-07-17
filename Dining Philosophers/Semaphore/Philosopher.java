package project;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private int identity;   //ari8mos filosofou
    private Fork left;      //aristero pirouni
    private Fork right;     //deksi pirouni
    private int scale;      //h timh gia na epireazetai to sleep
    Semaphore sem = new Semaphore(1,true);  //dhmiourgia semaphore (binary) opou enas filosofos 8a trwei ka8e fora

    Philosopher(int id, Fork l, Fork r, int s) {    //constructor
        identity = id;      //arxikopoihsh ari8mou tou filosofou
        left = l;           //arxikopoihsh pirouniwn
        right = r;
        scale = s;
    }

    public void run(){
        while (true){                               //atermwnos brogxos
            try{
                sleep((int)(Math.random()*scale));  //skeftetai
                sem.acquire();                      //opoios prolabei pairnei to lock kai trwei
                right.get();                        //pairnei to deksi pirouni
                sleep(500);                   //perimene
                left.get();                         //pairnei to aristero pirouni
                System.out.println("Philosopher: " + identity + " has the forks: " + right.getIdentity() + "," + left.getIdentity()); //ektypwse ta pirounia pou exeis
                sleep((int) (Math.random() * scale)); //perimene
                left.put();                         //afhse to aristero
                right.put();                        //afhse to deksi
                System.out.println("Philosopher: " + identity + " put down the forks: " + right.getIdentity() + "," + left.getIdentity());  //ektypwse oti ta afhses
                sem.release();                      //afou teleiwsei afhnei to lock
            }catch (InterruptedException e) {}
        }
    }
}