import java.util.Scanner;
public class FirstChar{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a line: ");
        String line = input.nextLine(); 
        char firstChar = line.charAt(0);
        System.out.println(firstChar);
        input.close();
    }
}