import java.rmi.*;
import java.rmi.server.*;

public class AdditionImpl extends UnicastRemoteObject implements Addition{
    //Constructor
    public AdditionImpl() throws RemoteException{
        super();
    }
    //Code
    public int add(int a, int b) throws RemoteException{
        return (a+b);
    }
}
