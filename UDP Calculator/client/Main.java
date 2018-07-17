package project;

import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        int serverPort = 7896;                      //to port tou server
        Scanner user = new Scanner(System.in);      //scanner gia to input tou xrhsth
        try{
            aSocket = new DatagramSocket();         //dhmiourgia socket gia udp
            System.out.println("Enter the calculation: (Close == Exit) ");
            String input = user.nextLine();                                 //pare input
            while (!input.equalsIgnoreCase("CLOSE")){          //an den zhthsa na termatisei
                String[] parts = input.split(" ");                   //kopse to input
                if (parts.length == 3 && sign(parts[0]) && numbers(parts[1], parts[2])) {   //kane tous elegxous
                    byte[] b = input.getBytes();                            //input se pinaka byte
                    DatagramPacket request = new DatagramPacket(b, b.length, InetAddress.getLocalHost(), serverPort);   //dhmiourgia paketou pros apostolh
                    aSocket.send(request);                                  //apostolh paketou
                    byte[] buffer = new byte[1000];
                    DatagramPacket result = new DatagramPacket(buffer, buffer.length);  //proetoimasia antikeimenou gia apanthsh
                    aSocket.setSoTimeout(30000);                        //dhmiourgia timeout 30 deyteroleptwn
                    while (true){
                        try{                                            //prospa8ise
                            aSocket.receive(result);                    //na paralabeis to paketo
                            break;                                      //break
                        }catch (SocketTimeoutException ste){            //an den mporeseis
                            System.out.println("Time out.");
                            aSocket.send(request);                      //apostolh paketou pali
                        }
                    }
                    String str = new String(result.getData(), StandardCharsets.UTF_8);
                    System.out.println("Result: " + str);               //Ektypwsh apotelesmatos
                }
                else
                    System.out.println("The correct input is +-*/ number number.");
                System.out.println("Enter the calculation: (Close == Exit) ");
                input = user.nextLine();
            }
        }catch(UnknownHostException e){
            System.out.println("Sock: " + e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }finally {
            if (aSocket!=null)
                aSocket.close();
        }
    }

    private static boolean sign(String sign){       //Elegxos prosimou
        if (sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/"))
            return true;
        else
            return false;
    }

    private static boolean numbers(String num1, String num2){   //Elegxos ari8mwn
        try {
            Double.parseDouble(num1);
            Double.parseDouble(num2);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}