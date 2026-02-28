public class Taster5 {
public static void main(String[] args) {
    Course c1 = new Course();
    Course c2 = new Course();
    Course c3 = new Course();
    c1.name = "Structured Programming";
    c1.code = "CSE32";
    c1.credit = 3f;
    System.out.println("------1------");
    c1.displayCourse();
    c2.name = "oop";
    c2.code = "12342";
    c2.credit = 2.5f;
    System.out.println("-------2------");
    c2.displayCourse();

    c3.name = "Algotithm";
    c3.code = "al323";
    c3.credit = 3f;
    System.out.println("---------3--------");
    c3.displayCourse();
}    
}
class Course{
    String name;
    String code;
    float credit;
    void displayCourse(){
        System.out.print("Course: " + name + " \nCourse code: " + code + " \nCredit: " + credit + "\n");
    }
}