import java.util.Scanner;
public class Exp{
    public static void main(String[] args) {
        double m;
        double n;
        Scanner exp = new Scanner(System.in);
        System.out.println("Enter m: ");
        m = exp.nextDouble();
        System.out.println("Enter n: ");
        n = exp.nextDouble();
        double e = Math.pow(m,n);
        System.out.println("exp: "+ e);

        

    }
}