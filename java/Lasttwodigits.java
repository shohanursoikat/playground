import java.util.Scanner;
public class Lasttwodigits{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter digit: ");
        int digit = sc.nextInt();
        if(digit>=1000 && digit<=9999){ 
        int lastdigit=digit%10;
       
        int secondlast = (digit/10)%10;
        int sum=lastdigit+secondlast;
        System.out.println("Sum: "+sum);

        }else{
            System.out.println("Enter within four digits.");
        }

    }
}
