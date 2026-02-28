import java.io.FileWriter;
import java.io.IOException;
public class Write {
    public static void main(String[] args) {
        try {
            FileWriter file = new FileWriter("est.java",false);
            file.write("Hello Java\n");
            file.write("what's up bro....\n");
            file.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error while occured writing the file.");
            e.printStackTrace();
        }
    }
}
