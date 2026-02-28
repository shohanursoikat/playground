
import java.util.Scanner;
public class Perfect{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter num: ");
        int n = sc.nextInt();
        int sum=0;
        if(n>=1){ 
        for(int i=1; i<n; i++){
            if(n%i==0){
                sum+=i;
            }
        }if(n==sum){
            System.out.println("Perfect.");
        }else{
            System.out.println("Not perfect.");
        }
    }
    }
}