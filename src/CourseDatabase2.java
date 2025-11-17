import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
public class CourseDatabase2 {
    ArrayList<Course> courses = new ArrayList<>();
    public List<Course> getAllCourses(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            courses = mapper.readValue(new File("course.json"),new TypeReference<List<Course>>() {});
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }
    public void addCourse(Course course){
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getCourseId().equals(course.getCourseId()))
                return;
        }
        courses.add(course);
        saveToFile();
    }
    public void saveToFile(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("course.json"), courses);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public boolean removeCourse(Course course){
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getCourseId().equals(course.getCourseId())){
                courses.remove(i);
                saveToFile();
                return true;
            }
        }
        return false;
    }
}
