public class Fork {
    private boolean taken = false;  //xrhsimopoieitai h oxi to pirouni
    private int identity;           //ari8mos toy pirouniou

    Fork(int id){                   //constructor
        identity = id;              //arxikopoihsh tou ari8mou tou
    }

    public int getIdentity() {      //getter gia na kseroume poios einai o ari8mos tou
        return identity;            //apo tous filosofous
    }

    public synchronized void put(){ //opoios afisei pirouni
        taken = false;              //to eleu8erwnei
        notify();                   //kai eidopoiei tous ypoloipous
    }

    public synchronized void get() throws InterruptedException{ //opoios 8elei pirouni
        while (taken)               //oso to pirouni den einai dia8esimo
            wait();                 //perimene
        taken = true;               //otan eley8erw8ei parto
    }
}
