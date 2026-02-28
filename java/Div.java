import java.util.Scanner;
public class Div {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter num: ");
        int num = input.nextInt();
        if(num%2==0 ^ num%5==0){
            System.out.println(num);
        }else{
            System.out.println("Not divisible.");
        }
    }
}
