package project;

public class Main {

    public static void main(String[] args) {
        int noWriters = 2;          //ari8mos syggrafewn
        int noReaders = 2;          //ari8mos anagnwstwn
        int delay = 500;           //mege8os delay

        Writer writers[] = new Writer[noWriters];   //pinakas syggrafewn
        Reader readers[] = new Reader[noReaders];   //pinakas anagnwstwn

        Buffer buff = new Buffer();                 // o koinos buffer

        for(int i = 0; i<noWriters; i++){           //dhmiourgia syggrafewn
            writers[i] = new Writer(buff, delay);
            writers[i].start();
        }

        for(int i = 0; i<noReaders; i++){           //dhmiourgia anagnwstwn
            readers[i] = new Reader(buff, delay);
            readers[i].start();
        }
    }
}
