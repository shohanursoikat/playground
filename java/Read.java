import java.io.File;
import java.util.Scanner;
public class Read{
    public static void main(String[] args) {
        File file = new File("Test.java");
       
        try {
             Scanner sc = new Scanner(file);
            
            while(sc.hasNextLine()){
                String a=sc.nextLine();
                System.out.println(a);

            }
            sc.close();
        } catch(Exception e){
            System.out.println("File not found.");
        }
    }
}