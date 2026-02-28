import java.util.Scanner;
public class Ascii{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string: ");
        String str = sc.nextLine();
        int Ascii=0;
        for(int i = 0; i<str.length(); i++){
            //Ascii = str.codePointAt(i);
            char c = str.charAt(i);
            Ascii = (int) c;
            System.out.print(Ascii + " ");
        }
        
    }
}