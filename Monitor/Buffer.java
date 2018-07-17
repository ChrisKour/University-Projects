package project;

public class Buffer {
    private int[] contents;
    private boolean bufferEmpty = true;
    private boolean bufferFull = false;
    private final int size;
    private int front, back, counter = 0;

    //Constructor
    public Buffer(int s) {
        this.size = s;
        contents = new int[size];
        for (int i = 0; i < size; i++)
            contents[i] = 0;
        this.front = 0;
        this.back = size - 1;
    }

    //Put an item into buffer
    public synchronized void put(int data, String name) { //exoume synchronized gia thread safety dld mono ena thread mporei na mpei edw ana pasa stigmh
        while (bufferFull) try {    // oso einai gematos o buffer
            wait();                 //perimene mexri na se kanoun notify oti mporeis na mpeis
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        back = (back + 1) % size;
        contents[back] = data;
        counter++;
        System.out.println("Item " + data + " added in loc " + back + ". Count = " + counter + " by thread : " + name);
        bufferEmpty = false;
        if (counter == size) {
            bufferFull = true;
            System.out.println("The buffer is full.");
        }
        notifyAll();   //kane notify oti o buffer sigoura den einai adeios
    }

    //Get an item from buffer
    public synchronized int get(String name) { //exoume synchronized gia thread safety dld mono ena thread mporei na mpei edw ana pasa stigmh
        int data = 0;
        while (bufferEmpty) try {   //oso o buffer einai adeios
            wait();                 //perimene na se kanoyn notify oti mporeis na synexiseis ektelesh
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data = contents[front];
        System.out.println("Item " + data + " removed from loc " + front + ". Count = " + (counter - 1) + " by thread : " + name);
        front = (front + 1) % size;
        counter--;
        bufferFull = false;
        if (counter == 0) {
            bufferEmpty = true;
            System.out.println("The buffer is empty.");
        }
        notifyAll();       //kane notify oti den einai gematos o buffer
        return data;
    }
}
