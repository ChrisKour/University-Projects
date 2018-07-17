package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try{
            int serverPort = 7896;              //ari8mos port tou server
            ServerSocket s = new ServerSocket(serverPort);  //anoigma serverSocket
            System.out.println("Server started.");
            System.out.println("Awaiting connections.");
            while (true){
                Socket clientSocket = s.accept();   //perimene client
                Connection c = new Connection(clientSocket);    //ftiakse thread me ton ka8e client
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
