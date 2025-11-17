import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class UserDatabase{
    ArrayList<UserAccount> users;
    public UserDatabase(){
        users = getAllUsers();
    }
    public String readFromFile(){
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("user.json"))){
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = String.join("", lines);
        return json;
    }
    public ArrayList<UserAccount> getAllUsers() {
        String json = readFromFile();
        ArrayList<UserAccount> users = new ArrayList<>();
        if (json.length() < 3)  // empty file
            return users;

        // remove[ ]
        json = json.substring(1, json.length() - 1);

        String[] userObjects = json.split("\\},\\{");

        for (String obj : userObjects) {
            obj = obj.replace("{", "").replace("}", "");
            String[] fields = obj.split(",");
            String userId = "", role = "", username = "", email = "", passwordHash = "";

            for (String field : fields) {
                String[] keyValue = field.split(":");
                if (keyValue.length < 2) continue;

                String key = keyValue[0].replace("\"", "").trim();
                String value = keyValue[1].replace("\"", "").trim();

                switch (key) {
                    case "userId": userId = value; break;
                    case "role": role = value; break;
                    case "username": username = value; break;
                    case "email": email = value; break;
                    case "passwordHash": passwordHash = value; break;
                    }
                }
                if (role.equals("student")) {
                Student s = new Student(username, passwordHash, userId,"","student",email);
                users.add(s);
            } else if (role.equals("instructor")) {
                Instructor ins = new Instructor(username, passwordHash,userId, "","instructor",email);
                users.add(ins);
            }
        }
        return users;
    }
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("user.json")) {
            writer.write("[\n");

            for (int i = 0; i < users.size(); i++) {
                UserAccount u = users.get(i);

                String jsonUser = "{\n" +
                        "  \"userId\": \"" + u.getUserId() + "\",\n" +
                        "  \"role\": \"" + u.getRole() + "\",\n" +
                        "  \"username\": \"" + u.getUserName() + "\",\n" +
                        "  \"email\": \"" + u.getEmail() + "\",\n" +
                        "  \"passwordHash\": \"" + u.getPass() + "\"";

                // Role-specific fields
                if (u instanceof Student) {
                    Student s = (Student) u;
                    jsonUser += ",\n  \"enrolledCourses\": " + listToJson(s.getEnrolledCourses()) +
                                ",\n  \"progress\": " + progressToJson(s.getProgress());
                } else if (u instanceof Instructor) {
                    Instructor ins = (Instructor) u;
                    jsonUser += ",\n  \"createdCourses\": " + listToJson(ins.getCreatedCourses());
                }

                jsonUser += "\n}";

                writer.write(jsonUser);
                if (i < users.size() - 1) writer.write(",\n");
            }

            writer.write("\n]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String listToJson(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("\"").append(list.get(i)).append("\"");
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    private String progressToJson(Map<String, List<Boolean>> progress) {
        StringBuilder sb = new StringBuilder("{");
        int count = 0;
        for (Map.Entry<String, List<Boolean>> entry : progress.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":[");
            List<Boolean> flags = entry.getValue();
            for (int i = 0; i < flags.size(); i++) {
                sb.append(flags.get(i));
                if (i < flags.size() - 1) sb.append(",");
            }
            sb.append("]");
            if (count < progress.size() - 1) sb.append(",");
            count++;
        }
        sb.append("}");
        return sb.toString();
    }
    public void addUser(UserAccount user){
        users.add(user);
        saveToFile();
    }
    public boolean removeUser(UserAccount user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserId().equals(user.getUserId())){
                users.remove(i);  
                saveToFile();
                return true;
            }
        }
        return false;
    }
}

