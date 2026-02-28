import java.util.Scanner;
public class Nextline{
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter srting: ");
        String str = sc.nextLine().toLowerCase();
        System.out.println(str );
        int vowel = 0;
        int con = 0;
        int space = 0;
        int specialchar = 0;
         for(int i = 0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch >= 'a' && ch<='z'){ 
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ){
                vowel++;
            }else{
                con++;
            }
            
          }else if(ch == ' '){
                space ++;
            }else {
                specialchar++;
            }
         }

         System.out.println("Vowel: " + vowel);
         System.out.println("Consonent: " + con);
         System.out.println("space: " + space);
         System.out.println("specialchar: " + specialchar);
    }
   

    }