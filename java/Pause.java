import java.util.Scanner;
public class Pause {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first num: ");
        int num = input.nextInt();
        System.out.println("Enter second num: ");
        int n = input.nextInt();
        double exp = Math.pow(num,n);
    }
    
}
