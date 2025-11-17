import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
public class UserDatabase2 {
    List<UserAccount> users;
    public ObjectMapper mapper;
    public UserDatabase2(){
        users = new ArrayList<>();
        mapper = new ObjectMapper();
    }
    public List<UserAccount> getAllUsers(){
        try {
            File file = new File("user.json");
            List<Map<String,Object>> data = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
            users.clear();  //
            for(Map<String,Object> raw : data){
                String role = (String) raw.get("role");
                String userId = (String) raw.get("userId");
                String username = (String) raw.get("username");
                String email = (String) raw.get("email");
                String passwordHash = (String) raw.get("passwordHash");
                
                if(role.equalsIgnoreCase("student")){
                    List<String> courses = (List<String>) raw.getOrDefault("enrolledCourses", new ArrayList<>());
                    Map<String, List<Boolean>> progress = (Map<String, List<Boolean>>) raw.getOrDefault("progress", new HashMap<>());
                    Student s = new Student(username, passwordHash, userId, "", role, email);
                    s.setCourses(courses);
                    s.setProgress(progress);
                    users.add(s);
                } else if(role.equalsIgnoreCase("instructor")) {
                    List<String> createdCourses = (List<String>) raw.getOrDefault("createdCourses", new ArrayList<>());
                    Instructor i = new Instructor(username, pass, userId, "", role, email);
                    i.setcreatedCourses(createdCourses);
                    users.add(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    public void saveToFile(){
        try {
            mapper.writeValue(new File("user.json"), users);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean addUser(UserAccount user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserId().equals(user.getUserId()))
                return false;
        }
        users.add(user);
        saveToFile();
        return true;
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
