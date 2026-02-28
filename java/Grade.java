import java.util.Scanner;
public class Grade{
    public static void main(String[]args){
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter mark: ");
        int mark = sc.nextInt();
        if(mark>=0 && mark<=100){
            
            if(mark>=90&&mark<100){
                System.out.println("A+");
            }else if(mark>=80&&mark<=89){
                System.out.println("B");
            }else if(mark>=70&&mark<=79){
                System.out.println("C");
            }else if(mark>=60&&mark<=69){
                System.out.println("D");
            }else if(mark>=50&&mark<=59){
                System.out.println("E");
            }else{
                System.out.println("F");
            }
        }
    }
}