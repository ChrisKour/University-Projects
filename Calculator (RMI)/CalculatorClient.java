import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.*;

public class CalculatorClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args){
		try{
			System.setSecurityManager(new RMISecurityManager());
			Registry registry = LocateRegistry.getRegistry("localhost", PORT);
			CalculatorInterface calc = (CalculatorInterface)registry.lookup("Calculator");
			Socket dataSocket = new Socket(InetAddress.getLocalHost(),PORT);
			BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Enter message:");
			String msg = user.readLine();
			int result=0;
			while(!msg.equals("CLOSE")) {
				String msg_parts[] = msg.split(","); //diaxwrismos mhnymatos toy xrhsth se pinaka
				//Elegxos gia ton arithmo twn arguments, an exoume swsth praksh kai swstous arithmous
				if( msg_parts.length==3 && elegxosPrakshs(msg_parts[0]) && elegxosArithmitikou(msg_parts[1]) && elegxosArithmitikou(msg_parts[2])){
					System.out.println(calc.calculation(msg));
					System.out.print("Enter message:");
					msg = user.readLine();
				}
				else{
					System.out.println("There was an error. The correct input is: +-*/,number,number.");
					System.out.print("Please try again");
					msg = user.readLine();
				}
			}
			
		}
		catch(Exception e){
			System.out.println("Client error: "+e);
		}
	}
	
	public static boolean elegxosArithmitikou(String message) {
		try {//elegxos arithmitikou an mporei na ginei to parse int tote return true
			Integer.parseInt(message);
		    return true;
		}
		catch (NumberFormatException e) {//diaforetika false
			return false;
		}
	}
	
	public static boolean elegxosPrakshs(String message) {//elegxos an yparxei ena apo ta 4 symbola twn praksewn
		  if(message.equals("+") || message.equals("-") || message.equals("*") || message.equals("/"))
			  return true;
		  else
			  return false;
		}
	
}
