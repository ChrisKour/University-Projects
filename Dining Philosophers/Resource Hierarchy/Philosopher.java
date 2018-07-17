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

    public void run(){
        try{
            while (true){                               //atermwnos brogxos
                sleep((int)(Math.random()*scale));      //skeftetai
                if ((identity % 2 == 0)){               //an eisai zygos (0,2,4) pare prwta deksi meta aristero pirouni
                    right.get();                        //prospa8ise na pareis to deksi pirouni
                    System.out.println("Philosopher: " + identity + " has the fork: " + right.getIdentity());   //ektypwse to pirouni pou exeis
                    sleep(500);                   //perimene
                    left.get();                         //prospa8ise na pareis kai to aristero
                    System.out.println("Philosopher: " + identity + " has the forks: " + right.getIdentity() + "," + left.getIdentity()); //ektypwse ta pirounia pou exeis
                    sleep((int) (Math.random() * scale)); //perimene
                    left.put();                         //afhse to aristero
                    right.put();                        //afhse to deksi
                    System.out.println("Philosopher: " + identity + " put down the forks: " + right.getIdentity() + "," + left.getIdentity()); //ektypwse oti ta afhses
                }
                else { //an eisai monos (1,3,5) pare prwta aristero meta deski pirouni
                    left.get();                         //prospa8ise na pareis to aristero pirouni
                    System.out.println("Philosopher: " + identity + " has the fork: " + left.getIdentity());    //ektypwse to pirouni pou exeis
                    sleep(500);                   //perimene
                    right.get();                        //prospa8ise na pareis kai to deksi
                    System.out.println("Philosopher: " + identity + " has the forks: " + right.getIdentity() + "," + left.getIdentity());   //ektypwse ta pirounia pou exeis
                    sleep((int) (Math.random() * scale)); //eating
                    right.put();                        //afhse to deksi
                    left.put();                         //afhse to aristero
                    System.out.println("Philosopher: " + identity + " put down the forks: " + right.getIdentity() + "," + left.getIdentity());  //ektypwse oti ta afhses
                }
            }
        }catch (InterruptedException e) {}
    }
}