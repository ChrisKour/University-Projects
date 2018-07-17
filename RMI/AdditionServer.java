import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class AdditionServer {

    private static final int PORT = Registry.REGISTRY_PORT; //1099

    public static void main(String[] args) throws Exception{
        //dhmiourgia antikeimenou
        Addition robj = new AdditionImpl();

        //Trexoume to rmi regisrty
        Registry registry = LocateRegistry.createRegistry(PORT);

        //Eggrafh tou antikeimenoy sthn registry me onoma Addition
        String rmiObjectName = "Addition";
        registry.rebind(rmiObjectName,robj);
        System.out.println("Remote object bounded");

        System.out.println("Press <Enter> for exit.");
        System.in.read();

        //Aposyndeoume to apokrysmeno antikeimeno
        UnicastRemoteObject.unexportObject(robj,true);
        registry.unbind(rmiObjectName);
        System.out.println("Remote object unbounded.");
    }
}
