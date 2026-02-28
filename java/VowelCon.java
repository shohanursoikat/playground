import java.util.Scanner;
public class VowelCon{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string: ");
        String str = sc.nextLine().toLowerCase();
        int vowel =0;
        int con = 0;
        for(int i=0; i<str.length(); i++){
            
            char c = str.charAt(i) ;
            if(c>='a' && c<='z'){ 
            if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u'){
                
                vowel++;
                }else{
                    con++;
                }
            }
        }
        System.out.println("Vowel: "+vowel+" Consonent: "+con);
    }
}