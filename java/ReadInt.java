import java.io.File;
import java.util.Scanner;
public class ReadInt {
    public static void main(String[] args) {
        File file = new File("int.txt");
        
       
        try {
             Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                Scanner lineScanner = new Scanner(line);

                while(lineScanner.hasNextInt()){
                    int n = lineScanner.nextInt();
                    System.out.print(n+" ");
                }
                
             
                lineScanner.close();
                System.out.println();
        }
        sc.close();

        } catch (Exception e) {
            System.out.println("File not found.");
        }
        
    }
}
