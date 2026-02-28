public class Cat{
    public static void main(String[] args) {
        CatDetails c1 = new CatDetails();
        
        c1.PrintCat();
        c1.color = "Black";
        c1.action = "sitting";
        c1.PrintCat();
        c1.color = "Brown";
        c1.action="jumping";
        c1.PrintCat();
        
    }
    
}
class CatDetails {
    String color = "white";
    String action = "sitting";
    void PrintCat(){
    System.out.println(color + " cat is " + action);
}
}

