import java.net.*;
import java.io.*;

public class CalculationClient {
	private static final int PORT = 1234;
	public static void main(String args[]) throws IOException
	{
		Socket dataSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try{
			dataSocket = new Socket(InetAddress.getLocalHost(),PORT); //Dhmioyrgia ypodoxhs reymatos kai syndesi me to diakomisth sthn thyra PORT
			out = new PrintWriter(dataSocket.getOutputStream(),true); //Dhmioyrgia reymatos exerxomenon dedomenon ths ypodoxhs dataSocket	
			in = new BufferedReader(new InputStreamReader(dataSocket.getInputStream())); //Dhmioyrgia reymatos eiserxomenon dedomenon ths ypodoxhs dataSocket
		}
		catch (UnknownHostException e){
			System.err.println("Don't know about host: local host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: local host");
            System.exit(1);
        }
		
		System.out.println("Connection established");
		// Dhmioyrgia antikeimenou eiserxomenon dedomenon gia to pliktrologio
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		
		// Anagnosi mhnymatos apo to pliktrologio
		System.out.print("Enter message:");
		String msg = user.readLine();
		while(!msg.equals("CLOSE")) {
			String msg_parts[] = msg.split(","); //diaxwrismos mhnymatos toy xrhsth se pinaka
			//Elegxos gia ton arithmo twn arguments, an exoume swsth praksh kai swstous arithmous
			if( msg_parts.length==3 && elegxosPrakshs(msg_parts[0]) && elegxosArithmitikou(msg_parts[1]) && elegxosArithmitikou(msg_parts[2])){
				// Bhma 3o: Eggrafh mhnymatos sto diakomisth 
				out.println(msg);
				// Bhma 3o: Anagnosi mhnymatos apo to diakomisth
				System.out.println("Received message from server: " + in.readLine());
				// Bhma 3o: Anagnosi mhnymatos apo to diakomisth
				System.out.println("Received message from server: " + in.readLine());
				// Anagnosi epomenoy mhnymatos apo to pliktrologio
				System.out.print("Enter message:");
				msg = user.readLine();
			}
			else{
				System.out.println("There was an error. The correct input is: +-*/,number,number.");
				System.out.print("Please try again");
				msg = user.readLine();
			}
		}	
		out.println(msg);
		// Bhma 4o: Kleisimo ypodoxhs
		out.close();
		in.close();
		user.close();
		dataSocket.close();
		System.out.println("Data Socket closed");
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