import java.net.*;
import java.io.*;
import java.sql.*;

public class ChatServerThread extends Thread
{  
	private ChatServer       server    = null;
	private Socket           socket    = null;
	private DataInputStream  streamIn  = null;
	private DataOutputStream streamOut = null;
	private String 			 username  = null;
	private int 			 id   	   = 0;

	public ChatServerThread(ChatServer _server, Socket _socket)
	{  
		super();
		server = _server;
		socket = _socket;
	}
	
	@SuppressWarnings("deprecation")
	public void send(String msg)
	{   
		try{  
			streamOut.writeUTF(msg);
			streamOut.flush();
		}
		catch(IOException ioe){  
			System.out.println(id + " ERROR sending: " + ioe.getMessage());
			server.remove(id);
			stop();
		}
	}
	
	public int getID()
	{  
		return id;
	}
	
	@SuppressWarnings("deprecation")
	public void run()
	{  
		int friend_id =0;
		try {
		   	System.out.println("Server Thread " + id + " running.");
	   		String driverName="com.mysql.jdbc.Driver";
	   		String serverName="195.251.209.12";
	   		String portNumber="3306";
	   		String sid = "it125";
	   		String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+sid;
	   		
	   		Class.forName(driverName);
	   		Connection con = DriverManager.getConnection(url, "pdpuser", "resupdp");
	   		
	   		Statement st = con.createStatement();
	   		ResultSet rs = null;
	   		String[] friend ={"","","","",""}; //pinakas gia apo8hkeysh filwn
	   		
	   		String query;
	   		
	   		while(true){ //eksakribwsh stoixeiwn
	   			String info = streamIn.readUTF();
	   			String[] info_parts = info.split(" ");
	   			
	   			if(info_parts.length!=2){ //an den einai swsta dwsmena ta stoixeia ksanazhtame
	   				send("There was an error with the credentials");
	   				send("Please try again");
	   			}
	   			else{
	   				username = info_parts[0];
	   				query = "select id from Users where Username = '"+info_parts[0]+"' AND Pin = '"+info_parts[1]+"'";
	   				rs = st.executeQuery(query);
	   				while(rs.next()){
	   					id  = rs.getInt(1);
	   				}
	   				if(id !=0){ //efoson yparxei o xrhsths ton bazoume stous online xrhstes
	   					query = "INSERT INTO Online (ID,Username) VALUES ("+id+",'"+info_parts[0]+"')";
	   					st.execute(query);
	   					break;
	   				}
	   				else{ //an den yparxei sthn bash o xrhsths ksanazhtame stoixeia
	   					send("There was an error with the credentials");
	   					send("Please try again");
	   				}
	   			}
	   		}
	   		
	   		query = "select Friend from Friends where ID = "+id; //pairnoume olous tous filoys apo th bash
	   		rs = st.executeQuery(query);
	   		int i=0;
				while(rs.next()){
					friend[i] = rs.getString(1);
					i++;
				}
				
			String who = null,cont ="n";
			send("Online friends:");
			String[] online = {"","","","",""};
			int j=0;
			
			while(j==0){
				for(i=0;i<friend.length;i++){ //elegxoume poioi apo autous einai online
					if(!friend[i].equals("")){
						query = "select Username from Online where Username = '"+friend[i]+"'";
						rs = st.executeQuery(query);
						while(rs.next()){
							if(!rs.getString(1).equals("")){
								online[j] = rs.getString(1);
								j++;
								send(rs.getString(1));}
						}
					}
				}
				if(j==0){ //an den einai kaneis offline ksanaelegxoume mexri na mpei kapoios
					send("No one is online at the moment");
					send("Please wait as I check again (4 sec delay)");
					sleep(4000);
				}	
			}
			
			while(cont.equals("n")){// elegxos an edwse swsto onoma o xrhsths
				send("Type the username of the user you wish to speak with:");
				who = streamIn.readUTF();
				for(i=0;i<online.length;i++){
					if(online[i].equals(who))
						cont = "y";
				}
				if(cont.equals("n"))
					send("The username you typed does not exist");
			}
			
			query = "select ID from Users where Username = '"+who+"'"; //pairnoyme to id toy filoy gia na stalei to mhnyma
			rs = st.executeQuery(query);
			while(rs.next()){
				friend_id=rs.getInt("ID");
			}
			
			send("You are now chatting with: "+who);
			rs.close();
			st.close();
   			con.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (true){  
			try{  
				
				server.handle(streamIn.readUTF(),username,id,friend_id);
			}
			catch(IOException ioe){  
				System.out.println(id + " ERROR reading: " + ioe.getMessage());
				server.remove(id);
				stop();
			}
		}
	}
	
	public void offline(){ //kata thn eksodo toy xrhsth ton bgazoume apo toys online
		try{
			String driverName="com.mysql.jdbc.Driver";
			String serverName="195.251.209.12";
			String portNumber="3306";
			String sid = "it125";
   			String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+sid;
   		
   			Class.forName(driverName);
   			Connection con = DriverManager.getConnection(url, "pdpuser", "resupdp");
   		
   			Statement st = con.createStatement();
   			String query;
   			query = "delete from Online where Username = '"+username+"'";
   			st.execute(query);	
   			st.close();
   			con.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void open() throws IOException
	{  
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	public void close() throws IOException
	{  
		if (socket != null)    
			socket.close();
		if (streamIn != null)  
			streamIn.close();
		if (streamOut != null) 
			streamOut.close();
	}
}
