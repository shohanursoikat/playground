import java.util.Scanner;
public class Pub{
public static void main(String[] args) {
 Scanner input = new Scanner(System.in);
 System.out.println(" Enter a num: ");
 int num = input.nextInt();
  System.out.println("Enter second num: ");
 int num1 = input.nextInt();

 int sum , multi , div , mod;
 System.out.println("Sum: " + (num + num1) + " Multi: " + num * num1 + " Div: " + num / num1 + " Mod " + num % num1);

 
    
}
}