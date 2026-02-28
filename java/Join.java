import java.util.Scanner;
public class Join{
    public static void main(String[] args) {
        Scanner join = new Scanner(System.in);
        System.out.println("Enter a value for M: ");
        String m = join.nextLine();
        System.out.println("Enter a value for N: ");
        String n = join.nextLine();
        join.close();
        System.out.println(n + m);

    }
}