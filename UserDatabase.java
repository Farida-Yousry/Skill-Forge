package BackEnd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class UserDatabase{
    public String readFromFile(){
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("users.json"))){
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = String.join("", lines);
        return json;
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
    //overwritee
     public List<UserAccount> loadUsers() throws IOException {
    
        return null;
    }
     public List<Student> getStudents(){
     return null;}
     //Add saveUsers and Save courses
    //public List<UserAccount> LoadAllUsers
}
