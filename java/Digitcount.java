import java.util.Scanner;
public class Digitcount{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number: ");
        int n = sc.nextInt();
        int count = 0;
        int temp = n;
        while(temp>0){
            temp = temp/10;
            
            
            count ++;
            
        }System.out.println(count);
    }

}