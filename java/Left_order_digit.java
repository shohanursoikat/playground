import java.util.Scanner;
public class Left_order_digit{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter digit: ");
        int n = sc.nextInt();
        int count = 0;
        int temp = n;
       while(temp>0){
         temp = temp / 10;
        count++;

       }
       System.out.println("Digits: " + count);
       int exp = 1;
       for(int i = 1; i<count; i++){
        exp = exp * 10;
       }
        
         int num = 1;
         while(n>0){
            num = n/exp;
            System.out.print(num);
            n = n%exp;
            exp = exp /10;

         }
    }
   
    
}