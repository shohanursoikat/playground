import java.util.Scanner;
public class Right_order_digit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        System.out.println("Enter numbers: ");
        int n = sc.nextInt();
        
        
       while(n > 0){
        int mod = n%10;
         System.out.print(mod + " ");
       n = n/10;
       }
         
       
    }
    
}
