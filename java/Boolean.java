import java.util.Scanner;
public class Boolean{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a number: ");
        int num = input.nextInt();

        boolean even = num % 2 == 0;

        if(even){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
        input.close();
    }
}