import java.io.File;
import java.util.Scanner;

public class Readinteger {
public static void main(String[] args) {
   File file=new File("int.txt");

   try {
       Scanner sc = new Scanner(file);
       while(sc.hasNextLine()){
        int nextInt=sc.hasNextInt();
       }
   } catch (Exception e) {
    System.out.println("File not found");
   }}
   


}    

