import java.rmi.*;
 
public interface FileServerInterface extends Remote{
 
		public boolean login(FileClientInterface c) throws RemoteException;
}