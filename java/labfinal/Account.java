public class Account{
    private  double balance;
    public Account(){
        balance=0.8;
    }
   public Account(double b){
    balance=b;
   }
  
    public double showBalance(){
        return balance;
    }

}