public class Manager extends Employee{
    public double bonus;
    public double  increment;   
    public Manager(String r, int hr, double b, double bonus){
        super(r, hr, b);
        this.bonus=bonus;
    }
    
  public void calculateSalary(){
    if(getHoursWorked()>40){
        setFinalSalary(getBaseSalary()+getBaseSalary()*bonus/100);
    }else{
        setFinalSalary(getBaseSalary());
    }
   }

   public void requestIncrement(double  increment){
    this.increment=increment;
    if(getHoursWorked()>100){
        setFinalSalary(getBaseSalary()+increment); 
    }else if(getHoursWorked()>80){
       setFinalSalary(getBaseSalary()+increment/2); 
    }else{
      System.out.println("Increment denied");
      setFinalSalary(getBaseSalary());
    }


   }
  
   public void displayInfo(){
    System.out.println("Name: "+ super.role +" base salary: "+ super.getBaseSalary()+ " final salary: "+getFinalSalary()+ " hour: " +getHoursWorked());
   }

}