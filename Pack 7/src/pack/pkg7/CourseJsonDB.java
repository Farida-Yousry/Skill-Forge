package pack.pkg7;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;

public class CourseJsonDB {
     private static final String FILE = "courses.json";

    public JSONArray loadCourses() throws IOException {
        File file = new File(FILE);
        if (!file.exists()) return new JSONArray();

        String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
        return new JSONArray(content);
    }

    public void saveCourses(JSONArray courses) throws IOException {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(courses.toString(4));
        }
    }
}
