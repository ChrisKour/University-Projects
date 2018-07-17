import java.rmi.*;

public interface CalculatorInterface extends Remote{
	public String calculation(String calc) throws RemoteException;
}
