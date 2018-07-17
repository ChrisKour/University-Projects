import java.io.*;
import java.net.*;
import java.sql.*;

class ServerThread extends Thread
{
	private Socket dataSocket;
	BufferedReader in,user;
   	PrintWriter out;
   	public ServerThread(Socket socket)
   	{
      	dataSocket = socket;
      	try
      	{
	 		in = new BufferedReader(new InputStreamReader(dataSocket.getInputStream())); //Dhmioyrgia reymatos eiserxomenon dedomenon ths ypodoxhs dataSocket
	 		out = new PrintWriter(dataSocket.getOutputStream(),true); //Dhmioyrgia reymatos exerxomenon dedomenon ths ypodoxhs dataSocket
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
   			String driverName="com.mysql.jdbc.Driver"; //metablhtes gia syndesh sthn bash dedomenwn
   			String serverName = "195.251.209.12";
   			String portNumber = "3306";
   			String sid = "it125";
   			String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+sid;
   			
   			Class.forName(driverName); //syndesh
   			Connection con = DriverManager.getConnection(url, "pdpuser", "resupdp");
   			
   			Statement st = con.createStatement(); //dhmiourgia statement
   			
   			String query,error ="Error",error1,name,pin,again="y"; //metablhtes pou 8a xrhsimopoih8oun mesa sto prwtokollo
   			ResultSet rs= null;
   			int id=0,initial_amount=0;
   			
   			while(again.equals("y")){ //mesa se auto to while ginetai elegxos gia to an yparxei o xrhsths sth bash
   				name = in.readLine(); //apo8hkeysh onomatos kai pin
   				pin = in.readLine();
   			
   				query = "select id from Info where Name = '"+name+"' AND Pin = '"+pin+"'";
   				rs = st.executeQuery(query); //elegxos an yparxei o xrhsths
   				while(rs.next()){
   					id = rs.getInt(1); //an den yparxei 8a einai 0
   				}
   				
   				String confirmation = "y",request;
   				String[] request_parts;
   				
   				if(id != 0){ //o xrhsths einai sth bash
   					out.println("OK"); //apostolh toy ok ston client wste na synexisei
   					while(confirmation.equals("y")){
   						request = in.readLine();
   						request_parts = request.split(" "); //epeksergasia prakshs
   						int amount;
   						String apotelesma;
   					
   						if((request_parts[0].equals("K") || request_parts[0].equals("k"))){ //kata8esh
   							amount = Integer.parseInt(request_parts[1]);
   							query = "SELECT Balance FROM Money WHERE ID = "+id; //epilogh tou arxikou posou tou xrhsth
   							rs = st.executeQuery(query);
   							while(rs.next()){
   	   		   					initial_amount = rs.getInt(1);
   	   		   				}
   							initial_amount += amount; //pros8esh posou pou 8elei o xrhsths
   							query = "UPDATE Money SET Balance = "+initial_amount+" WHERE ID = "+id; //ananewsh posou
   							st.execute(query);
   							query = "INSERT INTO Transaction (Customer,Action,Money) VALUES ("+id+",'Kata8esh',"+amount+")"; //kratima log
   							st.execute(query);
   							apotelesma = "Neo Ypoloipo: "+initial_amount;
   							out.println(apotelesma);
   							confirmation = in.readLine();
   						}
   						else if(request_parts[0].equals("A") || request_parts[0].equals("a")){ //analhpsh
   							amount = Integer.parseInt(request_parts[1]);
   							query = "SELECT Balance FROM Money WHERE ID = "+id; //epilogh tou arxikou posou tou xrhsth
   							rs = st.executeQuery(query);
   							while(rs.next()){
   								initial_amount = rs.getInt(1);
   							}
   							if(amount <= initial_amount && amount < 600){ //elegxos ta lefta mesa ston logariasmo na einai arketa kai ligotera apo 600
   								initial_amount -= amount; //afairesh posou pou 8elei o xrhsths
   								query = "UPDATE Money SET Balance = "+initial_amount+" WHERE ID = "+id;
   								st.execute(query);
   								query = "INSERT INTO Transaction (Customer,Action,Money) VALUES ("+id+",'Analhpsh',"+amount+")";
   								st.execute(query);
   								apotelesma = "Neo Ypoloipo: "+initial_amount;
   	   							out.println(apotelesma);
   	   							confirmation = in.readLine();
   							}
   							else{
   								error1 = "Den mporeis na kaneis analhpsh tetoiou posou";
   								out.println(error1);
   								confirmation = in.readLine();   								
   							}
   						}
   						else if(request_parts[0].equals("Y") || request_parts[0].equals("y")){
   							query = "SELECT Balance FROM Money WHERE ID = "+id;
   							rs = st.executeQuery(query);
   							while(rs.next()){
   								initial_amount = rs.getInt(1);
   	   		   				}
   							query = "INSERT INTO Transaction (Customer,Action,Money) VALUES ("+id+",'Elegxos Ypoloipou',0)";
   							st.execute(query);
   							apotelesma = "Ypoloipo: "+initial_amount;
	   						out.println(apotelesma);
	   						confirmation = in.readLine();	   						
   						}	
   					}
   					break;//eksodos apo to ekswteriko while
   				}
   				else{ // o xrhsths den einai sthn bash
   					error1 = "O xrhsths den brisketai sthn bash";
   	   				out.println(error);
   	   				out.println(error1);
   	   				again = in.readLine();
   				}
   			}
   			
   			rs.close();
   			st.close();
   			con.close();
   			out.close();
   			in.close();
   			dataSocket.close();
   			System.out.println("Datasocket closed");
   		}
   		catch (IOException e)
   		{
      		System.out.println("Error " + e);
   		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}		