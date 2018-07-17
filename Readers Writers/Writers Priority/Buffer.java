package project;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private boolean writing = false;            //arxika den grafei kaneis
    private int reading = 0;                    //arxika den diabazei kaneis
    private int writersWaiting = 0;             //den perimenoun anagnwstes
    final Lock lock = new ReentrantLock();      //lock gia prostasia twn metablhtwn
    final Condition writePermission = lock.newCondition();      //condition gia na enhmerwnontai ta katallhla group
    final Condition readPermission = lock.newCondition();

    public void startRead(String s){
        lock.lock();                            //pare to lock
        while (writing || writersWaiting > 0){  //oso grafei kapoios h perimenei syggrafeas
            try{
                readPermission.await();         //perimene na se fwnaksoun
            } catch (InterruptedException e) {}
        }
        System.out.println("Reader: " + s + " started reading.");   //ektypwse oti ksekinhses na diabazeis
        reading++;                              //auksise thn metablhth reading
        lock.unlock();                          //ase to lock
    }

    public void endRead(String s){
        lock.lock();                            //pare to lock
        reading--;                              //meiwse to reading
        if (reading == 0)                       //an den diabazei kanenas
            writePermission.signal();           //enhmerwse kapoion syggrafea
        System.out.println("Reader: " + s + " stopped reading.");   //ektypwse oti stamatises na diabazeis
        lock.unlock();                          //ase to lock
    }

    public void startWrite(String s){
        lock.lock();                            //pare to lock
        if (writing || reading > 0 || writersWaiting > 0){  //an grafei kapoios h diabazei h perimenei syggrafeas
            writersWaiting++;                   //aykshse ton ari8mo syggrafewn pou perimenoun
            try{
                writePermission.await();        //perimene na sou steiloun synialo
            } catch (InterruptedException e) {}
            writersWaiting--;                   //meiwse ton ari8mo
        }
        writing = true;                         //ksekina na grafeis
        System.out.println("Writer: " + s + " started writing.");   //ektypwse oti ksekinhses na grafeis
    }

    public void endWrite(String s){
        writing = false;                        //teleiwse to grapsimo
        if (writersWaiting > 0)                 //an perimenoun syggrafeis
            writePermission.signal();           //steile synialo se enan
        else                                    //diaforetika
            readPermission.signalAll();         //pes stous anagnwstes na mpoun
        System.out.println("Writer: " + s + " stopped writing.");   //ektypwse oti stamathses na grafeis
        lock.unlock();                          //ase to lock
    }
}
