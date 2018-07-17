package project;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private int identity;           //ari8mos toy pirouniou
    private Lock lock = new ReentrantLock();    //dhmiourgia tou lock pou 8a xrhsimopoih8ei

    Fork(int id){                   //constructor
        identity = id;              //arxikopoihsh tou ari8mou tou
    }

    public int getIdentity() {      //getter gia na kseroume poios einai o ari8mos tou
        return identity;            //apo tous filosofous
    }

    public boolean get() throws InterruptedException{   //opoios 8elei pirouni
        if (lock.tryLock(1, TimeUnit.SECONDS)) {    //prospa8ise na pareis to pirouni mesa se 1 deuterolepto
            return true;    //an mporeseis gyrise true
        }
        return false;       // an oxi gyrise false
    }

    public void put(){
        lock.unlock();  //otan teleiwneis afhneis to pirouni
    }
}