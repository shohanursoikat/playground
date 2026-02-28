import java.util.Scanner;

public class Ser{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int n = sc.nextInt();

        int y = 0;
        int value = 12;   // starting point
        boolean add = true;  // first term is +

        while (value <= n) {
            if (add) {
                y += value;
            } else {
                y -= value;
            }

            value += 3;   // next number increases by 3
            add = !add;   // flip + and -
        }

        System.out.println(y);
    }
}
