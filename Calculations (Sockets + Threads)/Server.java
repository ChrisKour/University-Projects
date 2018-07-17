import java.net.*;
import java.io.*;

public class Server {
	
	private static int totalRows = 12; //Arithmos seirwn tou pinaka
	private static int rowAssigned = -1; //Arithmos ths seiras me thn opoia doyleuei o ka8e client
	private static final int PORT = 1234; //Port
	
	public static void main(String args[]) throws IOException {
		
		String[][] pinakas = new String[][]{ //Arxikopoihsh pinaka sto styl ths 1hs askhshs
				{"+,1,2",""},				//Praksh,arithmos,arithmos kai meta to apotelesma
				{"-,4,3",""},
				{"*,5,4",""},
				{"/,8,2",""},
				{"+,1,2",""},
				{"-,4,3",""},
				{"*,5,4",""},
				{"/,8,2",""},
				{"+,1,2",""},
				{"-,4,3",""},
				{"*,5,4",""},
				{"/,8,2",""}
		};
		
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
			Thread[] sthread = new Thread[2];	//Dhmiourgia pinaka threads me ton megisto arithmo threads pou 8a dextei o server
			try{
				Socket dataSocket = connectionSocket.accept(); //Anamoni kai apodoxh aithshs syndeshs apo pelath 
				System.out.println("Received " + connectionCount + " request from " + dataSocket.getInetAddress());
				sthread[connectionCount] = new Thread(new ServerThread(dataSocket,pinakas)); // Dhmioyrgia nhmatos gia kathe syndesh pelath
				
				sthread[connectionCount].start(); //ekinhsh toy nhmatos
				connectionCount++;
				//sthread.join();
			}
			catch (IOException e){
				System.err.println("Accept Failed");
				System.exit(1);
			}
			/*catch (InterruptedException e){
				System.err.println("This should not happen");
			}*/
		}
	}
	
	private static synchronized int getRow(){
		if(++rowAssigned < totalRows)
			return rowAssigned;
		else
			return -1;
	}
	
	private static class ServerThread extends Thread
	{
		private Socket dataSocket;
	   	BufferedReader in,user;
	   	PrintWriter out;
	   	String[][] pinakas;
	   	public ServerThread(Socket socket,String[][] pinakas)
	   	{
	   		this.pinakas = pinakas;
	      	dataSocket = socket;
	      	try
	      	{
		 		in = new BufferedReader(new InputStreamReader(dataSocket.getInputStream())); //Dhmioyrgia reymatos eiserxomenon dedomenon ths ypodoxhs dataSocket
		 		out = new PrintWriter(dataSocket.getOutputStream(),true); //Dhmioyrgia reymatos exerxomenon dedomenon ths ypodoxhs dataSocket
		 		user = new BufferedReader(new InputStreamReader(System.in)); //pliktrologio
	      	}
	     	catch (IOException e)
	     	{		
		 		System.out.println("There was an error creating the input and output streams");
	      	}
	    }

		public void run()
		{
	   		try
	   		{
	   			int element;
	   			String msg = "Not over"; //Arxiko string poy 8a stelnetai oso den exoun teleiwsei oi prakseis
	   			String stoixeia,apotelesma;
	   			while((element = getRow()) >=  0){ //oso den exoun teleiwsei oi prakseis
	   				stoixeia = pinakas[element][0];
	   				out.println(stoixeia);
	   				out.println(msg);
	   				apotelesma = in.readLine(); //diabasma apotelesmatos apo ton client
	   				pinakas[element][1] = apotelesma; //apo8hkeysh
	   				System.out.println("Row: "+element+" Result: "+apotelesma);
	      		}
	   			out.println("0"); //apostolh teleutaiwn grammwn
	   			msg = "CLOSE";
	      		out.println(msg);
	      		in.close();
	      		out.close();
	      		user.close();
	      		dataSocket.close();
	      		System.out.println("Data socket closed");
	   		}
	   		catch (IOException e)
	   		{
	      		System.out.println("There was an error communicating with the client");
	   		}
		}	
	}		
}