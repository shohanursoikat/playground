public class Dog extends Animal{

    public String name;

    
    public Dog(){
        System.out.println("The dog says hello.");
    }
    public void updatesound(String s){
        super.sound=s;
    }
    public String getName(){
        return name;
    }
    
}