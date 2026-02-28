import java.util.Scanner;
public class Velocity{
public static void main(String[] args) {
 Scanner input = new Scanner(System.in);
 System.out.println("Enter distance: ");
 float distance = input.nextFloat();   
 System.out.println("Enter time: ");
 float time = input.nextFloat();
 float velocity = (distance/time) * 3.6f;
 
 System.out.printf("Velocity: %.2f\n",velocity);
if(velocity<60){
    System.out.println("Slow.");
}else if(velocity>60 && velocity <90){
    System.out.println("Perfect.");
}else{
    System.out.println("Fast.");
}
}
}