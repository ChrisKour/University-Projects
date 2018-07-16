/*Author: Chris Kourounis */

import java.util.Scanner;

public class Bank {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Account account1 = new Account("1234567891011121","011","1547", 1000,0.8,"Papadopoulos","2310000000","1111111111111111");
        Account account2 = new Account("2345678901112233","014","2344", 2300,0.7,"Georgiou","2310111111","2222222222222222");

        System.out.println("IBAN from account 1:");
        account1.printIBAN();
        System.out.print("Do you want to deposit money to account 1? (Y)ES / (N)O ");
        String answer = in.next();
        if(answer.equalsIgnoreCase("y")){
            System.out.println("How much money do you want to deposit?");
            account1.credit(in.nextInt());}
        System.out.print("Do you want to withdraw money from account 1? (Y)ES / (N)O ");
        answer = in.next();
        if(answer.equalsIgnoreCase("y")){
            System.out.println("How much money do you want to withdraw?");
            account1.debit(in.nextInt());}
        account1.printInterest();

        System.out.println("IBAN from account 2:");
        account2.printIBAN();
        System.out.print("Do you want to deposit money to account 2? (Y)ES / (N)O ");
        answer = in.next();
        if(answer.equalsIgnoreCase("y")){
            System.out.println("How much money do you want to deposit?");
            account2.credit(in.nextInt());}
        System.out.print("Do you want to withdraw money from account 2? (Y)ES / (N)O ");
        answer = in.next();
        if(answer.equalsIgnoreCase("y")){
            System.out.println("How much money do you want to withdraw?");
            account2.debit(in.nextInt());}
        account2.printInterest();
    }
}
