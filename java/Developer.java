
public class Developer extends Employee{
   private String programming_language;
   double final_salary;
   public Developer(String name, double base_salary, int working_hours, String programmig_language){
    super(name, base_salary, working_hours);
    this.programming_language=programmig_language;
   }
   public String getProgammingLanguage(){
    return programming_language;
}
public void calculateSalary(){
    if(programming_language.equalsIgnoreCase("java")){
        final_salary = getBaseSalary() + 700;
    }else{
        final_salary=getBaseSalary();
    }
}
public void displayInfo() { 
System.out.println("Name: " + name); 
System.out.println("Base Salary: $" + getBaseSalary()); 
System.out.println("Work Hours: " + getHoursWorked()); 
System.out.println("Progamming Language: " + programming_language);
System.out.println("Final Salary: $"+final_salary);
 
} 
}
