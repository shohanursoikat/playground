
import java.util.Scanner;
public class Cir{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter radius: ");
       int r = sc.nextInt();
       double circumferences = 2 * 3.1416 * r;
       System.out.println("Circumference: " + circumferences);
       double area = 3.1416 * r * r;
       System.out.println("Area: " + area);

    }
}
