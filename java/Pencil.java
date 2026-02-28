import java.util.Scanner;
public class Pencil{
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number: ");
    int n = sc.nextInt();
    int get = n/4;
    System.out.println("Everyone will get: " + get);
    int remain = n%4;
    System.out.println("Remaining: " + remain);
        
    }
}
