package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        Socket s = null;
        int serverPort = 7896;                      //to port tou server
        Scanner user = new Scanner(System.in);      //scanner gia to input tou xrhsth
        try{
            s = new Socket(InetAddress.getLocalHost(), serverPort);  //dhmiourgia socket ston localhost
            DataInputStream in = new DataInputStream(s.getInputStream());   //eiserxomena dedomena
            DataOutputStream out = new DataOutputStream(s.getOutputStream());   //ekserxomena dedomena

            System.out.println("Enter the calculation: (Close == Exit) ");
            String input = user.nextLine();                                 //pare input
            while (!input.equalsIgnoreCase("CLOSE")){          //an den zhthsa na termatisei
                String[] parts = input.split(" ");                   //kopse to input
                if (parts.length == 3 && sign(parts[0]) && numbers(parts[1], parts[2])) {   //kane elegxous tou mhnymatos
                    out.writeUTF(input);                                    //steile to mhnyma
                    String data = in.readUTF();                             //pare thn apanthsh
                    System.out.println("Result: " + data);                  //ektypwse thn
                }
                else
                    System.out.println("The correct input is +-*/ number number."); //an exei ginei la8os ston tropo poy egrapse o xrhsths to mhnyma
                System.out.println("Enter the calculation: (Close == Exit) ");
                input = user.nextLine();                                    //pare ksana input
            }
            user.close();                                                   //kleise to scanner
            out.writeUTF("close");                                  //apostolh mhnymatos ston server oti teleiwsame
        }catch(UnknownHostException e){
            System.out.println("Sock: " + e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }finally {
            if (s!=null)
                try {
                    s.close();
                }catch (IOException e){}
        }
    }

    private static boolean sign(String sign){           //elegxos prosimou
        if (sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/"))
            return true;
        else
            return false;
    }

    private static boolean numbers(String num1, String num2){       //elegxos ari8mwn
        try {
            Double.parseDouble(num1);
            Double.parseDouble(num2);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
