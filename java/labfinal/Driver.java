public class Driver{
    public static void main(String[] args) {
        Manager naymer = new Manager("nye",95,1000,10);
        naymer.requestIncrement(99);
        naymer.displayInfo();
        Dev messi = new Dev("messi", 99, 11111,"java");
        messi.calculateSalary();;
        messi.displayInfo();
    }
}