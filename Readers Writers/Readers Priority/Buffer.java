package project;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Buffer {
    private boolean writing = false;            //arxika den grafei kaneis
    private int reading = 0;                    //arxika den diabazei kaneis
    private int readersWaiting = 0;             //den perimenoun anagnwstes
    final Lock lock = new ReentrantLock();      //lock gia prostasia twn metablhtwn
    final Condition writePermission = lock.newCondition();      //condition gia na enhmerwnontai ta katallhla group
    final Condition readPermission = lock.newCondition();

    public void startRead(String s){
        lock.lock();                            //o reader pairnei to lock
        if (writing){                           //an grafei kapoios
            readersWaiting++;                   //ayksise ton ari8mo poy perimenoun
            try{
                readPermission.await();         //perimene na er8ei synialo oti mporeis na diabaseis
            } catch (InterruptedException e) {}
            readersWaiting--;                   //meiwse
        }
        System.out.println("Reader: " + s + " started reading.");       //ektypwse oti diabazeis
        reading++;                              //auksise thn metablhth oti "diabazeis"
        lock.unlock();                          //ase to lock gia na mpei kainourios anagnwsths / syggrafeas
    }

    public void endRead(String s){
        lock.lock();                            //pare to lock
        reading--;                              //meiwse thn metablhth
        if (reading == 0)                       //an den diabazei kanenas
            writePermission.signal();           //dwse permission stous syggrafeis gia na mpoun
        System.out.println("Reader: " + s + " stopped reading.");   //ektypwse oti teleiwses
        lock.unlock();                          //ase to lock
    }

    public void startWrite(String s){
        lock.lock();                            //pare to lock (kai to krataei mexri na teleiwsei to grapsimo wste na mhn mpei KANEIS allos)
        while (writing || reading > 0 || readersWaiting > 0){       //oso grafei kapoios, diabazei kai perimenei na diabasei
            try{
                writePermission.await();                            //perimene (mexri na er8ei eidopoihsh)
            } catch (InterruptedException e) {}
        }
        writing = true;                                             //ksekina na grafeis
        System.out.println("Writer: " + s + " started writing.");   //ektypwse oti grafeis
    }

    public void endWrite(String s){
        writing = false;                                            //allakse timh sto writing
        if (readersWaiting > 0)                                     //an perimenei kapoios na diabasei
            readPermission.signalAll();                             //steile synialo na mpei
        else                                                        //diaforetika
            writePermission.signal();                               //steile synialo se kapoion allo syggrafea
        System.out.println("Writer: " + s + " stopped writing.");   //ektypwse oti teleiwses
        lock.unlock();                                              //ase to lock gia na mpei allos
    }
}
