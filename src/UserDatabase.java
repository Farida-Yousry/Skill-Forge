import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class UserDatabase{
    public ArrayList<String> readFromFile(){
        ArrayList<String> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("users.json"))){
            String line;
            while ((line = br.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    } 
    public void writeJson() {
        Student student = new Student("amle","12345","9512","amlegomaa","student","amlegomaa22@gmail.com");
        try (FileWriter writer = new FileWriter("user.json")) {
            writer.write(student.toJson());
            System.out.println("Wrote user.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
