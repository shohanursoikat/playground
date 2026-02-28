import java.util.Scanner;
public class Max_min{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();

        System.out.println("Enter numbers: ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        
        for(int i = 0; i<quantity; i++){
          int num = sc.nextInt();
          if(num > max){
            max = num;
            
          }
          if(num<min){
            min = num;
          }
          sum += num ;

               double average = (double)sum / quantity;
        }
   
        
          System.out.println("Max: " + max);
          System.out.println("Min: " + min);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + quantity);
    }
  }
