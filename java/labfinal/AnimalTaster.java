public class AnimalTaster{
    public static void main(String[] args) {
         Animal a1 = new Animal();
         Dog d1 = new Dog();
         d1.name="pummy";
         
         d1.updatesound("bark");
         System.out.println("name: " +d1.getName());
         d1.details();
    }
}