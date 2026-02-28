import java.util.Scanner;
public class Ten{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter digits: ");
        int[]arr = new int[10];
        for(int i=0; i<arr.length; i++){
            arr[i]=sc.nextInt();
        }
        int total=0;
        int oddTotal=0;
        int count=0;
        for(int i=0; i<10; i++){
            total+=arr[i];
            if(arr[i]%2!=0){
                oddTotal+=arr[i];
                count++;
            }
        }
        double avg = (double)oddTotal/count;
        System.err.print("Total: "+ total );
        System.err.print(" Average: "+avg);

    }
}