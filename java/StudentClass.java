public class StudentClass {
    String id;
    public String name;
    private int age;
    protected double cgpa;
    
    public StudentClass(String name, String id, int age, double cgpa){
        this.name = name;
        this.id = id;
    }
    public void SetAge(int age){
        if(age>=15){
            this.age = age;
        }else{
            System.out.println("Invalid age.");
        }
    }
}

