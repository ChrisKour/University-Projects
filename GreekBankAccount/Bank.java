public class Bank {

    public static void main(String[] args){
        Account account1 = new Account("1234567891011121","011","1547",
                1000,0.8,"Papadopoulos","2310000000","1111111111111111");
        Account account2 = new Account("2345678901112233","014","2344",
                2300,0.7,"Georgiou","2310111111","2222222222222222");

        account1.printIBAN();
        account1.printBalance();
        account1.credit(500);
        account1.printBalance();
        account1.debit(1000);
        account1.printBalance();
        account1.printInterest();
    }
}
