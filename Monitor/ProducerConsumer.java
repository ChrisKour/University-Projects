package project;

public class ProducerConsumer {

    public static void main(String[] args) {
	    int bufferSize = 10;    //mege8os buffer
	    int noIterations = 10;  //fores pou prospa8ei na "paraksei" antikeimena o paragwgos
	    int producerDelay = 1;  //posh wra 8a mpainoun se sleep oi paragwgoi
	    int consumerDelay = 1;  //kai oi katanalwtes
	    int noProds = 100;       //ari8mos paragwgwn
	    int noCons = 100;        //ari8mos katanalwtwn

	    Producer prod[] = new Producer[noProds];
	    Consumer cons[] = new Consumer[noCons];

	    //Bounded Buffer
        Buffer buff = new Buffer(bufferSize);

        //Producer Threads
        for(int i = 0; i<noProds; i++){
            prod[i] = new Producer(buff, noIterations, producerDelay);
            prod[i].start();
        }

        //Consumer Threads
        for(int i = 0; i<noCons; i++){
            cons[i] = new Consumer(buff, consumerDelay);
            cons[i].start();
        }
    }
}
