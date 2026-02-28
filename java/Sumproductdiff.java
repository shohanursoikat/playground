import java.util.Scanner;
public class Sumproductdiff{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first num: ");
        int firstNum = input.nextInt();
        System.out.println("Enter second num: ");
        int secNum = input.nextInt();

        System.out.println("sum: " + (firstNum + secNum));
        System.out.println("product: " + (firstNum * secNum));
        System.out.println("Difference: " + (firstNum - secNum));
    }

}