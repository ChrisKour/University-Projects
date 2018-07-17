package project;

public class Fork {
    private boolean taken = false;  //xrhsimopoieitai h oxi to pirouni
    private int identity;           //ari8mos toy pirouniou

    Fork(int id){                   //constructor
        identity = id;              //arxikopoihsh tou ari8mou tou
    }

    public int getIdentity() {      //getter gia na kseroume poios einai o ari8mos tou
        return identity;            //apo tous filosofous
    }

    public void put(){              //plasmatikh synarthsh pou afhnei ena pirouni
        taken = false;
    }

    public  void get(){             ////plasmatikh synarthsh pou pairnei ena pirouni
        taken = true;
    }
}
