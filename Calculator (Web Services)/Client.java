package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import calculator.CalculatorStub.Calculation;

public class Client {
	public static void main(String[] args) throws Exception{
		CalculatorStub stub = new CalculatorStub();
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter message:");
		String msg = user.readLine();
		
		while(!msg.equals("CLOSE")) {
			String msg_parts[] = msg.split(","); //diaxwrismos mhnymatos toy xrhsth se pinaka
			//Elegxos gia ton arithmo twn arguments, an exoume swsth praksh kai swstous arithmous
			if( msg_parts.length==3 && elegxosPrakshs(msg_parts[0]) && elegxosArithmitikou(msg_parts[1]) && elegxosArithmitikou(msg_parts[2])){
				//creating the request
				calculator.CalculatorStub.Calculation request = new calculator.CalculatorStub.Calculation();
				request.setCalc(msg);

				calculator.CalculatorStub.CalculationResponse responce = stub.calculation(request);
				System.out.println(responce.get_return());
				System.out.print("Enter message:");
				msg = user.readLine();
			}
			else{
				System.out.println("There was an error. The correct input is: +-*/,number,number.");
				System.out.print("Please try again");
				msg = user.readLine();
			}
		}
	}
	
	public static boolean elegxosArithmitikou(String message) {
		try {//elegxos arithmitikou an mporei na ginei to parse int tote return true
			Integer.parseInt(message);
		    return true;
		}
		catch (NumberFormatException e) {//diaforetika false
			return false;
		}
	}
	
	public static boolean elegxosPrakshs(String message) {//elegxos an yparxei ena apo ta 4 symbola twn praksewn
		  if(message.equals("+") || message.equals("-") || message.equals("*") || message.equals("/"))
			  return true;
		  else
			  return false;
	}
}
