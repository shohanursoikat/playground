import java.util.Scanner;
public class Circle1{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter radius: ");
        double radius = input.nextDouble();
        double area = Math.PI * radius * radius;
        double circumference = 2 * Math.PI * radius;
        System.out.printf("Area: %.2f circumference: %.2f %n", area , circumference);

    }
}