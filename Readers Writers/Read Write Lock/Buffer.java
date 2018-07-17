package project;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Buffer {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true); //dhmiouriga read write lock me dikaiosynh
    private final Lock readLock = readWriteLock.readLock();     //dhmiourgia read lock
    private final Lock writeLock = readWriteLock.writeLock();   //dhmiourgia write lock


    public void startRead(String s){
        readLock.lock();                                            //pare to read lock
        System.out.println("Reader: " + s + " started reading.");   //ektypwse oti ksekinhses na diabazeis
    }

    public void endRead(String s){
        System.out.println("Reader: " + s + " stopped reading.");   //ektypwse oti stamathses na diabazeis
        readLock.unlock();                                          //ase to read lock
    }

    public void startWrite(String s){
        writeLock.lock();                                           //pare to write lock
        System.out.println("Writer: " + s + " started writing.");   //ektypwse oti ksekinhses na grafeis
    }

    public void endWrite(String s){
        System.out.println("Writer: " + s + " stopped writing.");   //ektypwse oti stamathses na grafeis
        writeLock.unlock();                                         //ase to write lock
    }
}
