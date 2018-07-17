package project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Connection extends Thread{
    DatagramPacket packet;
    DatagramSocket socket;

    public Connection (DatagramPacket apacket, DatagramSocket aSocket){ //constructor
        packet = apacket;
        socket = aSocket;
        this.start();
    }

    public void run(){
        System.out.println("Connection Established.");
        String str = new String(packet.getData(), StandardCharsets.UTF_8); //dhmiourgia string apo byte[]
        String result = calculation(str);   //ypologise to apotelesma
        DatagramPacket reply = new DatagramPacket(result.getBytes(), result.length(), packet.getAddress(), packet.getPort());   //dhmiourgise paketo apanthshs
        try{
            socket.send(reply);     //steilto pisw
        }catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

    }

    private String calculation(String data) {   //Ypologismos apotelesmatos
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
