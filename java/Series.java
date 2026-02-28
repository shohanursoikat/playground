import java.util.Scanner;
public class Series{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = sc.nextInt();
        int y = 0;
        if(n>=12){ 
            for(int i=12; i<=n; i+=3){
                if(i%2==0){
                    y = y+i;
                }else{
                    y=y-i;
                }
            }
        }else{
            System.err.println("Enter number greater than 12.");
        }
        System.err.print(y);
        
    }
}