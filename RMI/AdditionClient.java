import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class AdditionClient {

    private static final int PORT = Registry.REGISTRY_PORT; //1099

    public static void main(String[] args){
        try{
            Scanner in = new Scanner(System.in);

            //pairnoume reference sthn registry (localhost) kai port 1099
            Registry registry = LocateRegistry.getRegistry(PORT);

            //psaxnoume sth registry gia to addition kai to metatrepoume se klash Addition
            String rmiObjectName = "Addition";
            Addition ref = (Addition)registry.lookup(rmiObjectName);

            //klhsh ths apomakrysmenhs me8odou
            System.out.println("Enter 2 numbers that you wish to add.");
            String numbers = in.nextLine();
            String[] parts = numbers.split(" ");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            int result = ref.add(num1,num2);
            System.out.println("The result is " + result);
        }
        catch (RemoteException re){
            System.out.println("Remote Exception");
            re.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Other Exception");
            e.printStackTrace();
        }
    }
}
