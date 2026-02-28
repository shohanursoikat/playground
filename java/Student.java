package java;

public class Student {
    public String name;
    String id;
    private int age;
    protected double cgpa;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setAge(int n) {
        if (n > 15) {
            age = n;
        } else {
            System.out.println("Invalid Age");
        }
    }

    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Age: " + age);
        System.out.println("CGPA: " + cgpa);
    }
}
