package project;

public class Philosopher extends Thread {
    private int identity;   //ari8mos filosofou
    private Fork left;      //aristero pirouni
    private Fork right;     //deksi pirouni
    private int scale;      //h timh gia na epireazetai to sleep

    Philosopher(int id, Fork l, Fork r, int s) {    //constructor
        identity = id;      //arxikopoihsh ari8mou tou filosofou
        left = l;           //arxikopoihsh pirouniwn
        right = r;
        scale = s;
    }

    public void run() {
        while (true) {                                          //atermwnos brogxos
            try {
                sleep((int) (Math.random() * scale));           //skeftetai
                if (right.get()){                               //prospa8ise na pareis to deksi pirouni
                    System.out.println("Philosopher: " + identity + " has the fork " + right.getIdentity());  //ektypwse to pirouni pou exeis
                    if (left.get()){                            //prospa8ise na pareis to aristero pirouni
                        System.out.println("Philosopher: " + identity + " has the forks " + right.getIdentity() + "," + left.getIdentity());  //ektypwse ta pirounia pou exeis
                        sleep((int) (Math.random() * scale));   //perimene
                        left.put();                             // afhse to aristero
                        System.out.println("Philosopher: " + identity + " put down the fork " + left.getIdentity());  //ektypwse to pirouni pou afhneis
                    }
                    right.put();                                //afhse to deksi eite exeis teleiwsei to fai eite an den kataferes na pareis to aristero
                    System.out.println("Philosopher: " + identity + " put down the fork " + right.getIdentity());   //ektypwse to pirouni pou afhneis
                }
            } catch (InterruptedException e) { }
        }
    }
}
