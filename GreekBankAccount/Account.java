public class Account {

    private String accountNumber,bankNumber,shopNumber,owner,telephone,cardNumber;
    private double balance,interest;
    private int k;

    public Account(String accountNumber,String bankNumber,String shopNumber,double balance,double interest,String owner,
                   String telephone,String cardNumber){
        this.accountNumber = accountNumber;
        this.bankNumber = bankNumber;
        this.shopNumber = shopNumber;
        this.balance = balance;
        this.interest = interest;
        this.owner = owner;
        this.telephone = telephone;
        this.cardNumber = cardNumber;
        k = 11;
    }

    public void printIBAN() {
        System.out.println("GR"+k+bankNumber+shopNumber+accountNumber);
    }

    public double getBalance(){
        return balance;
    }

    private void setBalance(int money){
        balance += money;
    }

    public void credit(int credit){
        if(credit > 0){
            setBalance(credit);
            System.out.println("Successful transaction");
            printBalance();
        }else
            System.out.println("Error");
    }

    public void debit(int debit){
        if(debit > 0 && getBalance() >= debit){
            setBalance(-debit);
            System.out.println("Successful transaction");
            printBalance();
        }else
            System.out.println("Error");
    }

    public void printInterest(){
        System.out.printf("Interest: %.2f\n", (180*(interest*getBalance()/365)));
    }

    public void printBalance(){
        System.out.printf("You have a total of: %.2f\n",getBalance());
    }
}
