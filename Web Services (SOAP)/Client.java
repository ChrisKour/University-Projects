package pkg;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		String line;
		
		AdditionStub stub = new AdditionStub();
		
		//Creating the request
		pkg.AdditionStub.Add request = new pkg.AdditionStub.Add();
		System.out.println("Give 2 numbers that you wish to add");
		line = in.nextLine();
		String[] parts = line.split(" ");
		request.setA(Integer.parseInt(parts[0]));
		request.setB(Integer.parseInt(parts[1]));
		
		//Getting the responce
		pkg.AdditionStub.AddResponse responce = stub.add(request);
		//Printing it
		System.out.println(responce.get_return());
	}
	
}
