public class Employee{
    public String role;
    private int hour;
    private double base_salary;
    private double final_salary;

    public Employee(String role, int hour, double base_salary) {
    this.role=role;
    this.hour=hour;
    this.base_salary=base_salary;
    }
    public double  getBaseSalary(){
        return base_salary;
    }

    public int getHoursWorked(){
        return hour;
    }
    public double getFinalSalary(){
        return final_salary;
    }
    public void setBaseSalary(double b){
        base_salary=b;
    }
    public void setFinalSalary(double Final){
        final_salary=Final;
    }
    
    public void setHoursWorked(int h){
        hour=h;
    }
}