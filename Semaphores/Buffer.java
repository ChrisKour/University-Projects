package project;

import java.util.concurrent.Semaphore;

public class Buffer {
    private int[] contents;
    private boolean bufferEmpty = true;
    private boolean bufferFull = false;
    private final int size;
    private int front, back, counter = 0;
    Semaphore semCon = new Semaphore(0,true);    // to semaphore twn katanalwtwn einai 0 (adeies) wste na treksoun prwta oi paragwgoi
    Semaphore semProd = new Semaphore(1,true);   // ka8e fora mporei na mpei mesa mono enas paragwgos

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
        try {       //ta semaphore locks mpainoun edw gia na prostatepsoun ton counter kai to location(back)
            semProd.acquire(); // o paragwgos pairnei thn adeia efoson thn exei kanei release o katanalwths
        } catch (InterruptedException e) { }
        if(!bufferFull){
            back = (back +1) % size;
            contents[back] = data;
            counter++;
            System.out.println("Item " + data + " added in loc " + back + ". Count = " + counter + " by thread : "+ name);
            bufferEmpty = false;
            if(counter == size){
                bufferFull = true;
                System.out.println("The buffer is full.");
            }
        }
        semCon.release(); // o paragwgos kanei release mia adeia wste opoios katanalwths prolabei na parei to proion pou mpike sto buffer
    }

    //Get an item from buffer
    public int get(String name){
        try{
            semCon.acquire(); // o katanalwths pairnei thn adeia poy exei anoiksei enas paragwgos gia na parei to proion pou molis mpike
        }catch (InterruptedException e){}
        int data = 0;
        if (!bufferEmpty) {
            data = contents[front];
            System.out.println("Item " + data + " removed from loc " + front + ". Count = " + (counter -1) + " by thread : "+ name);
            front = (front + 1) % size;
            counter--;
            bufferFull = false;
            if(counter == 0){
                bufferEmpty = true;
                System.out.println("The buffer is empty.");
            }
        }
        semProd.release(); // efoson pire to proion dinei adeia se ena paragwgo na mpei na paraksei kainourgio proion
        return data;
    }
}
