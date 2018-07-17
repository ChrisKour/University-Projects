import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class CalculatorServer {
	
	private static final int PORT = Registry.REGISTRY_PORT; //1099
	
	public static void main(String[] args){
		try{
			System.setSecurityManager(new RMISecurityManager());
			Calculator calc = new Calculator();
			Registry registry = LocateRegistry.createRegistry(PORT);
			registry.rebind("Calculator", calc);
			System.out.println("The server is ready");
		}
		catch (Exception e){
			System.out.println("The server failed to begin: "+e);
		}
	}
}
