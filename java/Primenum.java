import java.util.Scanner;
public class Primenum{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number: ");
        int num = sc.nextInt();
        boolean prime = true;
        for(int i=2; i<num/2; i++){

             if(num<=2){
            prime = false;
        }else if(num%i==0){
            prime = false;
        }
    }
    if(prime==true){
        System.out.println("Prime.");
    }else{
        System.out.println("Not Prime.");
    }
}
        }
       