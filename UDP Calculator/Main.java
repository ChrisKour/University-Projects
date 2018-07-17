package project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {

    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        try{
            int serverPort = 7896;                      //to port tou server
            aSocket = new DatagramSocket(serverPort);   //dhmiourgia udp socket tou server
            byte[] buffer = new byte[1000];             //dhmiourgia antikeimenou poy 8a krathsei to mhnyma
            System.out.println("Server started.");
            System.out.println("Awaiting connections.");
            while (true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length); //dhmiourgia paketou gia apodoxh
                aSocket.receive(request);       //apodoxh paketou
                Connection c = new Connection(request,aSocket); //dhmiourgia thread gia ypologismo
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}