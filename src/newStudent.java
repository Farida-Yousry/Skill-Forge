import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class newStudent extends UserAccount{
    public ArrayList<Course> enrolledCourses;
    public Map<String, ArrayList<Boolean>> progress = new HashMap<>();
    CourseDatabase cDb = new CourseDatabase();
    public newStudent(String username, String pass, String id, String fullname,String role, String email){
        super(username, pass, email, id, fullname, role, 0);
    }
    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses; 
    }
    public void setEnrolledCourses(ArrayList<Course> enrolledCourses) { 
        this.enrolledCourses = enrolledCourses; 
    }

    public java.util.Map<String, ArrayList<Boolean>> getProgress() {
        return progress; 
    }
    public void setProgress(java.util.Map<String, ArrayList<Boolean>> progress) {
        this.progress = progress; 
    }
    
}
