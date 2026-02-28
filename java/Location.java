public class Location{
    public static void main(String[] args) {
        UniversityTaster u1 = new UniversityTaster();
        u1.name = "Imperial college London";
        u1.location = "England";
        UniversityTaster u2 = new UniversityTaster();
        u2.name = "Eastern University";
        u2.location = "Bangladesh";
        System.out.println(u1.name + " " + u1.location);
        System.out.println(u2.name + " " + u2.location);

    }
    
}
class UniversityTaster{
    String name;
    String location;
}