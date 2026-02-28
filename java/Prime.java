public class Prime{
    public static void main(String[] args) {
        int num = 12;
         boolean isPrime = true;
         if(num <=1){
            isPrime = false;
            
         }else{
            for(int i=2; i<=num/2; i++ )
            if(num % i == 0){
                isPrime = false;
            }

         }
         if(isPrime){
            System.out.println("Prime");
         }else{
            System.out.println("Not prime");
         }
    }
    
}