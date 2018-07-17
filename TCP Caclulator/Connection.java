package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread{
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection (Socket aClientSocket){   //construcotr
        try{
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());    //eiserxomenh roh dedomenwn
            out = new DataOutputStream(clientSocket.getOutputStream()); //ekserxomenh roh dedomenwn
            this.start();
        }catch (IOException e){
            System.out.println("Connection: " + e.getMessage());
        }
    }

    public void run(){
        try{
            System.out.println("Connection Established.");
            String data = in.readUTF();     //diabase ta dedomena
            while (!data.equalsIgnoreCase("close")){    //mexri na er8ei close
                String result = calculation(data);      //ypologise apotelesma
                out.writeUTF(result);                   //steilto ston client
                data = in.readUTF();                    //perimene apanthsh
            }
        }catch (EOFException e ){
            System.out.println("EOF: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }finally {
            try{
                clientSocket.close();
            }catch (IOException e){}
        }
        System.out.println("Socket Closed.");
    }

    private String calculation(String data) {       //ypologismos apotelesmatos
        String[] parts = data.split(" ");
        Double num1, num2, result;
        num1 = Double.parseDouble(parts[1]);
        num2 = Double.parseDouble(parts[2]);
        if (parts[0].equals("+"))
            result = num1 + num2;
        else if (parts[0].equals("-"))
            result = num1 - num2;
        else if (parts[0].equals("*"))
            result = num1 * num2;
        else
            result = num1 / num2;
        return result.toString();
    }
}
