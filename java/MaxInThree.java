import java.util.Scanner;
public class MaxInThree{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First number: ");
        int n1 = sc.nextInt();
        System.out.println("Enter second number: ");
        int n2 = sc.nextInt();
        System.out.println("Enter third number: ");
        int n3 = sc.nextInt();
        if(n1>n2 && n1>n3){
            System.out.println("Max: " + n1);
        }else if(n2>n3 && n2>n1){
            System.out.println("Max: " + n2);

        }else{
            System.out.println("Max: " + n3);
        }
      
    
    }
}