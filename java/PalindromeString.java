import java.util.Scanner;
public class PalindromeString{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        String strp= "";
        for(int i=str.length()-1; i>=0; i--){
            strp = strp + str.charAt(i);
        }
        if(str.equals(strp)){
            System.out.println("Plaindrome.");
        }else{
            System.out.println("Not Plaindrome.");
        }
    }
}