import java.net.*;
import java.io.*;

public class Client {
	private static final int PORT = 1234;
	public static void main(String args[]) throws IOException
	{
		Socket dataSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader user = null;
		
		try{
			dataSocket = new Socket(InetAddress.getLocalHost(),PORT); //Dhmioyrgia ypodoxhs reymatos kai syndesi me to diakomisth sthn thyra PORT
			out = new PrintWriter(dataSocket.getOutputStream(),true); //Dhmioyrgia reymatos exerxomenon dedomenon ths ypodoxhs dataSocket	
			in = new BufferedReader(new InputStreamReader(dataSocket.getInputStream())); //Dhmioyrgia reymatos eiserxomenon dedomenon ths ypodoxhs dataSocket
			user = new BufferedReader(new InputStreamReader(System.in)); // Dhmioyrgia antikeimenou eiserxomenon dedomenon gia to pliktrologio
			System.out.println("Connection established");
		}
		catch (UnknownHostException e){
			System.err.println("Don't know about host: local host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: local host");
            System.exit(1);
        }
		
		String stoixeia = in.readLine(); //diabasma twn eiserxomenwn dedomenwn
		String msg = in.readLine();
		int first,second,result;
		String apot;
		while(!msg.equals("CLOSE")) {
			String diaxwrismos_stoixeiwn[] = stoixeia.split(","); //diaxwrismos mynhmatos se pinaka
			if(diaxwrismos_stoixeiwn.length == 3 && elegxosPrakshs(diaxwrismos_stoixeiwn[0]) && elegxosArithmitikou(diaxwrismos_stoixeiwn[1]) && elegxosArithmitikou(diaxwrismos_stoixeiwn[2])){ //elegxos swstou format mynhmatos
				first = Integer.parseInt(diaxwrismos_stoixeiwn[1]);
				second = Integer.parseInt(diaxwrismos_stoixeiwn[2]);
				if(diaxwrismos_stoixeiwn[0].equals("+")) { // Apostoli prostheshs
					result = first + second;
					apot = String.valueOf(result);
					out.println(apot);
				}
				else if(diaxwrismos_stoixeiwn[0].equals("-")) { // Apostoli afaireshs
					result = first - second;
					apot = String.valueOf(result);
					out.println(apot);
				}
				else if(diaxwrismos_stoixeiwn[0].equals("*")) { // Apostoli pollaplasiasmou
					result = first * second;
					apot = String.valueOf(result);
					out.println(apot);
				}
				else if(diaxwrismos_stoixeiwn[0].equals("/")) { // Apostoli diaireshs
					result = first / second;
					apot = String.valueOf(result);
					out.println(apot);
				}
			}
			else //an yparksei kapoio problhma apostolh analogou mhnymatos
				out.println("There was an error with the data");
			
			stoixeia = in.readLine();		
			msg=in.readLine();
			try{ //edw exw balei sleep gia ta thread wste na ginetai eukola elegxos an trexoyn taytoxrona
				Thread.sleep(4000);
			}
			catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
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
