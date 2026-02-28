import java.util.Scanner;
public class Time{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter second: ");
    
        int sec = sc.nextInt();
        int hour = sec/3600;
        
        int reminsec =sec%3600;
        int min=reminsec/60;

        
        int Sec=reminsec%60;
        System.out.println("Hour: "+hour+" Min: "+min+" Sec: "+Sec);

        
        System.out.println("");
    }
}