package project;

public class Main {

    public static void main(String[] args){
        int noPhil = 5; //ari8mos filosofwn
        int noFork = 5; //ari8mos pirouniwn
        int delay = 1;  //timh gia na epireazoume to sleep twn filosofwn

        Philosopher phil[] = new Philosopher[noPhil];   //dhmioyrgia pinaka filosofwn
        Fork fork[] = new Fork[noFork];                 //dhmiourgia pinaka pirouniwn

        for(int i = 0; i<noFork; i++){  //dhmiourgia pirouniwn me ari8mhsh
            fork[i] = new Fork(i);
        }

        for(int i = 0; i<noPhil; i++){  //dhmiourgia filosofwn
            if (i == (noPhil - 1))
                phil[i] = new Philosopher(i, fork[0], fork[i], delay);  //o teleutaios 8a parei sygkekrimena pirounia (0 gia aristero kai to teleutaio gia deksi)
            else
                phil[i] = new Philosopher(i, fork[i+1], fork[i],delay); //oloi oi ypoloipoi pairnoun to aristero kai to deksi analoga me ton ari8mo (i) toys
            phil[i].start();            //ksekinane
        }
    }
}