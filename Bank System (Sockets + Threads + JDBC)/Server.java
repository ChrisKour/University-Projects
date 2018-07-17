import java.net.*;
import java.io.*;

public class Server {
	
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {	
		int connectionCount = 0;		//Arithmos syndesewn
		ServerSocket connectionSocket = null;
		try{
			connectionSocket = new ServerSocket(PORT); //Dhmioyrgia ypodoxhs reymatos ServerSocket sthn thyra PORT
			System.out.println("Server started");
		}
		catch (IOException e){
			System.err.println("Error: Could not listen on port" + PORT);
			System.exit(1);
		}
		while (true) { // Anamoni gia pelates syndeshs
			try{
				Socket dataSocket = connectionSocket.accept(); //Anamoni kai apodoxh aithshs syndeshs apo pelath 
				System.out.println("Received " + connectionCount + " request from " + dataSocket.getInetAddress());
				ServerThread sthread = new ServerThread(dataSocket); //an 8eloyme mporoume na dhmioyrgisoume tosa threads osoi kai oi client pou syndeontai
				sthread.start();
				connectionCount++;
			}
			catch (IOException e){
				System.err.println("Accept Failed");
				System.exit(1);
			}
		}
	}
}