package project;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private boolean writing = false;            //arxika den grafei kaneis
    private int reading = 0;                    //arxika den diabazei kaneis
    private int readersWaiting = 0;             //den perimenoun anagnwstes
    private int writersWaiting = 0;             //den perimenoun syggrafeis
    final Lock lock = new ReentrantLock();      //lock gia prostasia twn metablhtwn
    final Condition writePermission = lock.newCondition();      //condition gia na enhmerwnontai ta katallhla group
    final Condition readPermission = lock.newCondition();

    public void startRead(String s){
        lock.lock();                            //pare to lock
        if (writing || writersWaiting > 0){     //an grafei kapoios h perimenei syggrafeas
            readersWaiting++;                   //ayksise ton ari8mo twn anagnwstwn pou perimenoun
            try{
                readPermission.await();         //perimene
            } catch (InterruptedException e) {}
            readersWaiting--;                   //meiwse ton ari8mo twn anagnwstwn pou perimenoun
        }
        System.out.println("Reader: " + s + " started reading.");   //ektypwse oti ksekinas na diabazeis
        reading++;                              //ayksise ton ari8mo twn anagnwstwn pou diabazoun
        lock.unlock();
    }

    public void endRead(String s){
        lock.lock();                            //pare to lock
        reading--;                              //meiwse ton ari8mo twn anagnwstwn pou diabazoun
        if (reading == 0)                       //an den diabazei kanenas
            writePermission.signal();           //enhmerwse kapoion syggrafea
        System.out.println("Reader: " + s + " stopped reading.");   //ektypwse oti teleiwses to diabasma
        lock.unlock();                          //ase to lock
    }

    public void startWrite(String s){
        lock.lock();                            //pare to lock
        if (writing || reading > 0 || readersWaiting > 0 || writersWaiting > 0){    //an grafei kapoios h diabazei h perimenei na diebasei h na grapsei
            writersWaiting++;                   //aykshse ton ari8mo twn syggrafewn pou perimenoun
            try{
                writePermission.await();        //perimene mexri na er8ei sinialo
            } catch (InterruptedException e) {}
            writersWaiting--;                   //meiwse ton ari8mo
        }
        writing = true;                         //ksekina na grafeis
        System.out.println("Writer: " + s + " started writing.");   //ektypwse oti ksekinas na grafeis
    }

    public void endWrite(String s){
        writing = false;                        //teleiwse to grapsimo
        if (readersWaiting > 0)                 //an perimenoun anagnwstes
            readPermission.signalAll();         //steiltous sinialo
        else                                    //diaforetika
            writePermission.signal();           //steile sinialo se kapoion syggrafea
        System.out.println("Writer: " + s + " stopped writing.");   //ektypwse oti stamathses na grafeis
        lock.unlock();                          //ase to lock
    }
}
