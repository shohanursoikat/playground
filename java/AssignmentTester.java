public class AssignmentTester {
public static void main(String[] args) {
    Assignment a1 = new Assignment();
    a1.DisplayDetails();
    a1.tasks = 11;
    a1.difficulty = "Moderate";
    a1.submission = true;
    a1.DisplayDetails();
    System.out.println(a1.makeOptional());
}    
}
class Assignment{ 
    String difficulty;
    boolean submission;
    int tasks;
    void DisplayDetails(){
        System.out.println("Tasks: " + tasks + " \nDifficulty: " + difficulty + " \nSubmission required: " + submission + "\n");

    }
    String makeOptional(){
        if(submission==true){
            submission = false;
            return "Assignment will not required submission.";
        }else{
            return "You are not allowed to submit.";
        }
    }
}
