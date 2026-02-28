import java.util.Scanner;
public class Searchkey{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size: ");
        int size = sc.nextInt();
        int [] arr = new int[size];
        System.out.println("Enter elements: ");
        for(int i = 0; i<size; i++){
            arr[i] = sc.nextInt();
                    System.out.print(arr[i] + " " + '\n');

        }
        boolean found = true;
        int index = 0;
        System.out.println("Enter element to search: ");
        int element = sc.nextInt();
        for(int i = 0; i<arr.length; i++){
        if(arr[i] == element){
            found = true;
            index = i;
        }
        }
        if(found){
            System.out.println("Element found at: " + index);
        }else{
            System.out.println("Element not found.");
        }
    }
}