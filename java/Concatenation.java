import java.util.Scanner;
public class Concatenation{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first string: ");
        String first = sc.nextLine();
        System.out.println("Enter second string: ");
        String second = sc.nextLine();
        System.out.println(first + " " + second);
        
    }
}