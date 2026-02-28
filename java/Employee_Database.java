public class Employee_Database{
    public static void main(String[] args){
        Employee emp1 = new Employee();
        Employee emp2 = new Employee();
        Employee emp3 = new Employee();
            emp1.newEmployee("Harry Porter");
            emp2.newEmployee("Hermione Granger");
            emp3.newEmployee("Ron Weasley");

            System.out.println("1========");
            emp1.displayInfo();
            System.out.println("2========");
            emp2.displayInfo();
            System.out.println("3========");
            emp3.displayInfo();
            System.out.println("4========");
            emp1.calculateTax();
            System.out.println("5========");
            emp1.promoteEmployee("lead");
            System.out.println("6========");
            emp1.calculateTax();
            System.out.println("7========");
            emp1.displayInfo();
            System.out.println("8========");
            emp3.promoteEmployee("manager");
            System.out.println("9=======");
            emp3.calculateTax();
            System.out.println("10=======");
            emp3.displayInfo();

           
    }

}
class Employee{
    String name;
    float salary;
    String designation;
    float tax;
    
    void newEmployee(String name){
    this.name = name;
    this.salary = 30000f;
    this.designation = "junior";
   }
   void displayInfo(){
    System.out.println("Employee Name: " + name);
    System.out.println("Employee Salary: " + salary +" Tk");
    System.out.println("Employee Designation: " + designation);
 }
    void promoteEmployee(String designation){
        if(designation == "senior"){
            
            this.salary = 55000f;
            this.designation = "senior";
        }else if(designation == "lead"){
            this.salary = 80000f;
            this.designation = "lead";
            System.out.println(name + " has been promoted to lead \nNew Salary: " + salary + " Tk");

        }else if(designation == "manager"){
            this.salary = 105000f;
            this.designation = "manager";
            System.out.println(name + " has been promoted to lead \nNew Salary: " + salary + " Tk");

        }
        
    }
    void calculateTax(){
        if(salary>30000 && salary<50000){
            tax = (salary * 10) /100;
            System.out.println(name + " Tax Amount: " + tax + " Tk");
        }else if(salary>=50000){
            tax = (salary *30) /100;
            System.out.println(name + " Tax Amount: " + tax + " Tk");
        }else{
            System.out.println("No need to pay tax.");
        }
        
        }
}