package project;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int[] contents;
    private boolean bufferEmpty = true;
    private boolean bufferFull = false;
    private final int size;
    private int front, back, counter = 0;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    //Constructor
    public Buffer(int s){
        this.size = s;
        contents = new int[size];
        for(int i=0; i<size; i++)
            contents[i] = 0;
        this.front = 0;
        this.back = size - 1;
    }

    //Put an item into buffer
    public void put(int data, String name){
        lock.lock();    //kleidwma gia na mpainei mono enas paragwgos
        try{
            while (bufferFull){     //oso o buffer einai gematos
                try{
                    notFull.await();    //perimene mexri na er8ei signal oti o buffer den einai pia gematos
                }catch (InterruptedException e) {}
            }
            back = (back +1) % size;
            contents[back] = data;
            counter++;
            System.out.println("Item " + data + " added in loc " + back + ". Count = " + counter + " by thread : "+ name);
            bufferEmpty = false;
            if(counter == size){
                bufferFull = true;
                System.out.println("The buffer is full.");
            }
            notEmpty.signal();  //dwse signal oti pleon yparxei toulaxiston ena antikeimeno mesa ston buffer
        }
        finally{
            lock.unlock();  //ksekleidwma wste na mpei allo thread
        }
    }

    //Get an item from buffer
    public int get(String name){
        int data = 0;
        lock.lock();    //kleidwma gia na mpei mono enas katanalwths
        try{
            while (bufferEmpty){    //oso o buffer einai adeios perimene
                try{
                    notEmpty.await();       //mexri na er8ei signal oti mporeis na mpeis
                }catch (InterruptedException e) {}
            }
            data = contents[front];
            System.out.println("Item " + data + " removed from loc " + front + ". Count = " + (counter -1) + " by thread : "+ name);
            front = (front + 1) % size;
            counter--;
            bufferFull = false;
            if(counter == 0){
                bufferEmpty = true;
                System.out.println("The buffer is empty.");
            }
            notFull.signal();   //steile signal oti den einai gematos o buffer se pi8anous paragwgous poy perimenoun
        }
        finally {
            lock.unlock();  //ksekleidwma gia na mpei allo thread katanalwth
        }
        return data;
    }
}
