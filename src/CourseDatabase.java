import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDatabase {
    public String readFromFile(){
        List<String> lines = new ArrayList<>();
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
    public List<Course> getAllCourses() {
        String json = readFromFile();
        List<Course> courses = new ArrayList<>();
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
    public void writeToFile(Course course){
        List<Course> courses = this.getAllCourses();
        courses.add(course);
            try (FileWriter writer = new FileWriter("course.json")) {
            writer.write("[\n"); // start of JSON array

            for (int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);

                String jsonCourse = "{\n" +
                        "  \"courseId\": \"" + c.getCourseId() + "\",\n" +
                        "  \"title\": \"" + c.getTitle() + "\",\n" +
                        "  \"description\": \"" + c.getDescription() + "\",\n" +
                        "  \"instructorId\": \"" + c.getInstructorId() + "\"\n" +
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
}


