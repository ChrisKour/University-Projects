import java.rmi.Naming;

public class StartFileServer {
	public static void main(String[] args){
		try{
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			FileServer fs = new FileServer();
			fs.setFile("new.txt");
			Naming.rebind("rmi://192.168.1.66/abc", fs);
			System.out.println("File Server is ready");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
