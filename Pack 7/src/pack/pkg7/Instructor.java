package pack.pkg7;

import java.util.ArrayList;
import java.util.List;


public class Instructor {
private String userId;
    private String role; 
    private String username;
    private String email;
    private String passwordHash;
    private List<String> createdCourses; 

    public Instructor( String userId, String role, String username, String email, String passwordHash) {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdCourses = new ArrayList<>();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setCreatedCourses(List<String> createdCourses) {
        this.createdCourses = createdCourses;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<String> getCreatedCourses() {
        return createdCourses;
    }
    
    
}

