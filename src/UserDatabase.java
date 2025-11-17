import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class UserDatabase extends Validations{
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
            String userId = "", role = "", username = "", email = "", passwordHash = "",fullName="";
            int age=0;
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
                    case "fullName":fullName=value;break;
                    case "age":age=Integer.parseInt(value);break;
                    }
                }
                if (role.equals("student")) {
                Student s = new Student(userId,username, passwordHash, email,fullName,role, age);
                users.add(s);
            } else if (role.equals("instructor")) {
                Instructor ins = new Instructor(userId,username, passwordHash, email,fullName,age);
                users.add(ins);
            }
        }
        return users;
    }
    public UserAccount findUserByUserName(String username) {
    	for(UserAccount user : users) {
    		if(user.getUserName().equals(username))
    			return user;}
    	return null;
 
    }
public boolean login(String userName,String password,String role)  {
		
		UserDatabase dp = new UserDatabase();
		UserAccount user = dp.findUserByUserName(userName);
				if(user !=null && user.getPass().equals(hashPassword(password)) && user.getRole().equals(role))
					return true;
		return false;
	}
public void signup(UserAccount user,String fullName,String userName,String password,int age,String role,String email) {
	if(!validateFullName(fullName)) {
	    JOptionPane.showMessageDialog(null,"Invalid name format[Please Enter at least 3 names]");
	return;}
	else if(!validateEmail(email)) {
		 JOptionPane.showMessageDialog(null,"Invalid Email format");
	return;}	
	else if(!validateUserName(userName)) {
		JOptionPane.showMessageDialog(null,"Invalid Username format[Please Enter at least 5 characters]");
	return;}
	else if(!validatePassword(password)) {
		JOptionPane.showMessageDialog(null,"Invalid Password format[Please Enter at least 3 digits ]");
	return;}
else if(!validateAge(age)) {
		JOptionPane.showMessageDialog(null,"Invalid age");
		return;}
    
	user.setUserName(userName.trim());
	user.setPassword(user.hashPassword(password));
	user.setEmail( email.trim());
	user.setUserId(user.generateNewId(role));
	user.setFullName(fullName.trim());
	user.setRole(role);
	user.setAge(age);
	addUser(user);
	JOptionPane.showMessageDialog(null,"Account Created");

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
                    jsonUser += ",\n  \"enrolledCourses\": " + coursesToJson(s.getEnrolledCourses()) +
                                ",\n  \"progress\": " + progressToJson(s.getProgress());
                } else if (u instanceof Instructor) {
                    Instructor ins = (Instructor) u;
                    jsonUser += ",\n  \"createdCourses\": " + coursesToJson(ins.getCreatedCourses());
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
    private String progressToJson(ArrayList<Progress> progress) {
        StringBuilder sb = new StringBuilder("[");
        int count = 0;
        for (int i=0;i<progress.size();i++) {
        	Progress p = progress.get(i);
            sb.append("{\"courseId\":\"").append(p.getLessonId()).append("\"}");
    
 
                if (i < progress.size() - 1) sb.append(",");
        }
            sb.append("]");
        return sb.toString();
    }
    private String coursesToJson(ArrayList<Course> courses) {
        StringBuilder sb = new StringBuilder("[");
        for (int i=0;i<courses.size();i++) {
            sb.append("\"").append(courses.get(i).getCourseId()).append("\"");
    
 
                if (i < courses.size() - 1) sb.append(",");
        }
            sb.append("]");
        return sb.toString();
    }
    public void addUser(UserAccount user){
        users.add(user);
        saveToFile();
    }
    public boolean removeUser(UserAccount user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserId().equals(user.getUserId())){
                users.remove(users.get(i));  
                saveToFile();
                return true;
            }
        }
        return false;
    }
	public String hashPassword(String password) {
		try {
			MessageDigest pass = MessageDigest.getInstance("SHA-256");
			byte[] temp = pass.digest(password.getBytes());
			StringBuffer s = new StringBuffer();
			for(byte b : temp)
				s.append(String.format("%02x",b & 0xff));
			return s.toString();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
			
	}
	
}
}

