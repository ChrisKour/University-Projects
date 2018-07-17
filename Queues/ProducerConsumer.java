package project;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) {
	    int noIterations = 10;  //fores pou prospa8ei na "paraksei" antikeimena o paragwgos
	    int producerDelay = 1;  //posh wra 8a argei o paragwgos prin "fitaksei" kainourio antikeimeno
	    int consumerDelay = 1;  //posh wra 8a argei o katanalwths prin "parei" ena antikeimeno
	    int noProds = 100;       //ari8mos paragwgwn
	    int noCons = 100;        //ari8mos katanalwtwn
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10, true);  //ftiaxnoyme mia lista mege8ous 10, typos antikeimenwn Message kai yparxei "dikaiosynh"

	    Producer prod[] = new Producer[noProds];
	    Consumer cons[] = new Consumer[noCons];

        //Dhmiourgia Paragwgwn
        for(int i = 0; i<noProds; i++){
            prod[i] = new Producer(queue, noIterations, producerDelay);
            prod[i].start();
        }

        //Dhmiourgia Katanalwtwn
        for(int i = 0; i<noCons; i++){
            cons[i] = new Consumer(queue, consumerDelay);
            cons[i].start();
        }
    }
}
