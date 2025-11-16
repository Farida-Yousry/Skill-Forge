import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDatabase {
    public String readFromFile(){
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("course.json"))){
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
    public ArrayList<Course> getAllCourses() {
        String json = readFromFile();
        ArrayList<Course> courses = new ArrayList<>();
        // remove[ ]
        json = json.substring(1, json.length() - 1);
        String[] courseObjects = json.split("\\},\\{");

        for (String obj : courseObjects) {
            obj = obj.replace("{", "").replace("}", "");
            String[] fields = obj.split(",");
            String courseId = "", title = "", description = "", instructorId = "";

            for (String field : fields) {
                String[] keyValue = field.split(":");
                if (keyValue.length < 2) continue;

                String key = keyValue[0].replace("\"", "").trim();
                String value = keyValue[1].replace("\"", "").trim();

                switch (key) {
                    case "courseId": courseId = value; break;
                    case "title": title = value; break;
                    case "description": description = value; break;
                    case "instructorId": instructorId = value; break;
                }
            }

            Course course = new Course(courseId, title,description, instructorId);
            courses.add(course);
        }
        return courses;
    }
    public void addCourse(Course course){
        ArrayList<Course> courses = this.getAllCourses();
        courses.add(course);
        saveToFile();
    }
    public void saveToFile(){
        ArrayList<Course> courses = this.getAllCourses();
        try (FileWriter writer = new FileWriter("course.json")) {
            writer.write("[\n"); 

            for (int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);

                String jsonCourse = "{\n" +
                        "  \"courseId\": \"" + c.getCourseId() + "\",\n" +
                        "  \"title\": \"" + c.getTitle() + "\",\n" +
                        "  \"description\": \"" + c.getDescription() + "\",\n" +
                        "  \"instructorId\": \"" + c.getInstructorId() + "\"\n" +
                        "  \"lessons\": " + lessonsToJson(c.getLessons()) + ",\n" +
                        "}";

                writer.write(jsonCourse);

                // Add comma if not the last course
                if (i < courses.size() - 1) {
                    writer.write(",\n");
                }
            }

            writer.write("\n]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String lessonsToJson(ArrayList<Lesson> lessons) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < lessons.size(); i++) {
            Lesson l = lessons.get(i);
            sb.append("{\"lessonId\":\"").append(l.getLessonId())
              .append("\",\"title\":\"").append(l.getTitle())
              .append("\",\"content\":\"").append(l.getContent()).append("\"}");
            if (i < lessons.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    public boolean reomveCourse(Course course){
        ArrayList<Course> courses = getAllCourses();
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getCourseId() == course.getCourseId()){
                courses.remove(courses.get(i));  
                saveToFile();
                return true;
            }
        }
        return false;
    }
}




