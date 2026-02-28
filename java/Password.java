import java.io.File;
import java.util.Scanner;
public class Password {
    public static void main(String[] args) {
        
    File file = new File("passwords.txt");
    try {
    Scanner sc = new Scanner(file);
    while(sc.hasNextLine()){
        System.out.println("peter");
        System.out.println(sc.nextLine());
        System.out.println(sc.nextLine());
    }
        
    } catch (Exception e) {
        System.out.println("404 not found.");
    }

    }
    
}
