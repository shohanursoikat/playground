import java.io.File;
import java.util.Scanner;
public class ReadFile {
    public static void main(String[] args) {
        File file=new File("Test.java");
        try {
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            sc.close();

        } catch (Exception e) {
            System.out.println("file not found");
        }
    }
    
}
