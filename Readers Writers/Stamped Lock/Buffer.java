package project;

import java.util.concurrent.locks.StampedLock;

public class Buffer {
    final StampedLock lock = new StampedLock();

    public long startRead(String s){
        long stamp = lock.tryOptimisticRead();        //prospa8ise optimistic lock
        if (!lock.validate(stamp))                    //an den petyxeis
            stamp = lock.readLock();                   //kane kanoniko
        System.out.println("Reader: " + s + " started reading.");
        return stamp;
    }

    public void stopRead(String s, long stamp){
        System.out.println("Reader: " + s + " stopped reading.");
        lock.unlockRead(stamp);                         // kane unlock to Read
    }

    public long startWrite(String s){
        long stamp = lock.writeLock();                  //pare to write lock
        System.out.println("Writer: " + s + " started writing.");
        return stamp;
    }

    public void stopWrite(String s, long stamp){
        System.out.println("Writer: " + s + " stopped writing.");
        lock.unlockWrite(stamp);                        //kane unlock to write lock
    }
}
