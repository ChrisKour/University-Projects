import java.net.*;
import java.io.*;

public class CalculationServer {
	
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {
		int connectionCount = 0; //arithmos syndesewn
		ServerSocket connectionSocket = null;
		try{
			connectionSocket = new ServerSocket(PORT); //dhmiourgia server socket sthn thyra PORT
			System.out.println("Server started");
		}
		catch (IOException e){
			System.err.println("Could not listen on port: " + PORT);
			System.exit(1);
		}
		while (true) { //Syndeontai synexws clients mexri na kleisoyme emeis ton server
			Socket dataSocket = null; 
			BufferedReader in = null;
			PrintWriter out = null;
			try{
				dataSocket = connectionSocket.accept();//apodoxh client
				connectionCount++; //aykshsh arithmoy syndesewn
				in = new BufferedReader(new InputStreamReader(dataSocket.getInputStream())); //dhmiourgia reymatos eiserxomenon dedomenwn gia to pliktrologio
				out = new PrintWriter(dataSocket.getOutputStream(),true);//Dhmioyrgia reymatos exerxomenon dedomenon ths ypodoxhs dataSocket
				System.out.println("Received " + connectionCount + " request from " + dataSocket.getInetAddress());
			}
			catch (IOException e){
				System.err.println("Accept Failed");
				System.exit(1);
			}
			int num_msgs = 0; //arithmos mynhmatwn
			String msg = in.readLine(); //apo8hkeysh toy mhnymatos toy xrhsth
			int first_number=0,second_number=0,result;
			while(!msg.equals("CLOSE")) {
				String msg_parts[] = msg.split(","); //diaxwrismos mhnymatos toy xrhsth se pinaka
				if( msg_parts.length==3 && elegxosPrakshs(msg_parts[0]) && elegxosArithmitikou(msg_parts[1]) && elegxosArithmitikou(msg_parts[2])){
					first_number = Integer.parseInt(msg_parts[1]);
					second_number = Integer.parseInt(msg_parts[2]);
					if(msg_parts[0].equals("+")) { // Apostoli prostheshs
						result = first_number + second_number;
						out.println("Result: " + result);
					}
					if(msg_parts[0].equals("-")) { // Apostoli afaireshs
						result = first_number - second_number;
						out.println("Result: " + result);
					}
					if(msg_parts[0].equals("*")) { // Apostoli pollaplasiasmou
						result = first_number * second_number;
						out.println("Result: " + result);
					}
					if(msg_parts[0].equals("/")) { // Apostoli diaireshs
						result = first_number / second_number;
						out.println("Result: " + result);
					}
					System.out.println("Received message from client: " + msg);
					num_msgs++;
					out.println("Successful Message " + num_msgs + " " + msg);
					msg = in.readLine();
					}
				else{
					out.println("There was an error. The correct input is: +-*/,number,number.");
					out.print("Please try again");
					msg = in.readLine();
				}
			}
			out.close();
			in.close();
			dataSocket.close();//Kleisimo data socket
			System.out.println("Data socket closed");
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