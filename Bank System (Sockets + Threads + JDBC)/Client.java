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
		
		while(true){ //auto to kommati trexei mexri na to diakopsei o xrhsths
		
			System.out.print("Give your name: "); //erwthsh,apo8hkeush toy name kai pin
			String name = user.readLine();
			System.out.print("Give your pin: ");
			String pin = user.readLine();
		
			out.println(name); //apostolh name kai pin ston server
			out.println(pin);
		
			String msg = in.readLine(); //anamonh toy ok apo ton server
			String answer;

			if(msg.equals("OK")){ //o xrhsths edwse swsta stoixeia
				answer = "y";
				while(answer.equals("y")){ //oso o xrhsths 8elei na synesixei
					System.out.println("Analhpsh: A kai to poso (< 600 eyrw)");
					System.out.println("Kata8esh: K kai to poso");
					System.out.println("Enhmerwsh Ypoloipoy: Y");
					String query = user.readLine();
					out.println(query); //apostolh energeias
					msg = in.readLine();
					System.out.println(msg); //ektypwsh mhnymatos telous energeias apo ton server
					System.out.println("8eleis na kaneis kai alles energeies? (Yes = y, Exit = Close)");
					answer = user.readLine();
					out.println(answer);
				}
				break;
			}
			else{
				String msg2 = in.readLine();
				System.out.println(msg2); //ektypwsh mynhmatos la8oys apo server
				System.out.println("8eleis na dokimaseis ksana? (Yes = y, Exit = Close)");
				answer = user.readLine();
				out.println(answer);
				if(answer.equals("Close"))
					break;
			}
		}
		
		out.close();
		in.close();
		user.close();
		dataSocket.close();
		System.out.println("Data Socket closed");
	}
}