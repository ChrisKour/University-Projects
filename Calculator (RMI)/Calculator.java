import java.rmi.*;
import java.rmi.server.*;

public class Calculator extends UnicastRemoteObject implements CalculatorInterface{
	public Calculator () throws RemoteException{}
	
	public String calculation(String calc) throws RemoteException{
		String calc_parts[] = calc.split(",");
		int result =0;
		if( calc_parts.length==3 && elegxosPrakshs(calc_parts[0]) && elegxosArithmitikou(calc_parts[1]) && elegxosArithmitikou(calc_parts[2])){
			if(calc_parts[0].equals("+"))
				result = (Integer.parseInt(calc_parts[1]) + Integer.parseInt(calc_parts[2]));
			else if(calc_parts[0].equals("-"))
				result = (Integer.parseInt(calc_parts[1]) - Integer.parseInt(calc_parts[2]));
			else if(calc_parts[0].equals("*"))
				result = (Integer.parseInt(calc_parts[1]) * Integer.parseInt(calc_parts[2]));
			else if(calc_parts[0].equals("/"))
				result = (Integer.parseInt(calc_parts[1]) / Integer.parseInt(calc_parts[2]));
			return Integer.toString(result);
		}
		else
			return "There was an error please try again";
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
