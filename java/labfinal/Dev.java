public class Dev extends Employee{
    public String cp;
    

   public Dev(String n, int h, double b, String cp){
    super(n, h, b);
    this.cp=cp;
   }
   public void calculateSalary(){
if(cp.equalsIgnoreCase("java")){
    setFinalSalary(getBaseSalary()+700);
   }else{
    setFinalSalary(getBaseSalary());
   }
   }
   
    public void displayInfo(){
    System.out.println("Name: "+ super.role +" base salary: "+ super.getBaseSalary()+ " final salary: "+getFinalSalary()+ " hour: " +getHoursWorked() + " cp: "+ cp);
   }
    
}