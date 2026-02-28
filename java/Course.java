public class Course {
    public static void main(String[] args) {
        Cour c1 = new Cour();
        c1.name = "Strucruted Programming";
        c1.code = "CSE223";
        c1.credit = 3f;
        System.out.println("Name: " + c1.name + " Course code: " + c1.code + " Credit: " + c1.credit);
        Cour c2 = new Cour();
        c2.name = "oop";
        c2.code = "23322";
        c2.credit = 1.5f;
        System.out.println("Name: " + c2.name + " Course code: " + c2.code + " Credit: " + c2.credit);

    }
}
class Cour{
    String name;
    String code;
    float credit;

}
