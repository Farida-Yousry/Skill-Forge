package pack.pkg7;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

public class InstructorService {
    
    private final UserJsonDB userDB = new UserJsonDB();
    private final CourseJsonDB courseDB = new CourseJsonDB();
    
    public String createCourse(String instructorId, String title, String description) throws IOException {
        JSONArray courses = courseDB.loadCourses();
        JSONArray users = userDB.loadUsers();
        System.out.println(users.length());
        String courseId = UUID.randomUUID().toString();
        
        JSONObject course = new JSONObject();
        course.put("courseId", courseId);
        course.put("title", title);
        course.put("description", description);
        course.put("instructorId", instructorId);
        course.put("lessons", new JSONArray());
        course.put("students", new JSONArray());
        
        for (int i = 0; i < users.length(); i++) {
            JSONObject u = users.getJSONObject(i);
            if (u.getString("userId").equals(instructorId)) {
                courses.put(course);
                System.out.println("found user");
                u.getJSONArray("createdCourses").put(courseId);
            }
        }
        
        courseDB.saveCourses(courses);
        userDB.saveUsers(users);
        
        return courseId;
    }
    
    public void editCourse(String courseId, String newTitle, String newDescription) throws IOException {
        JSONArray courses = courseDB.loadCourses();
        
        for (int i = 0; i < courses.length(); i++) {
            JSONObject c = courses.getJSONObject(i);
            if (c.getString("courseId").equals(courseId)) {
                c.put("title", newTitle);
                c.put("description", newDescription);
                break;
            }
        }
        
        courseDB.saveCourses(courses);
    }
    
    public void deleteCourse(String courseId) throws IOException {
        JSONArray courses = courseDB.loadCourses();
        
        for (int i = 0; i < courses.length(); i++) {
            if (courses.getJSONObject(i).getString("courseId").equals(courseId)) {
                courses.remove(i);
            }
            break;
        }
        courseDB.saveCourses(courses);
    }
    
    public void addLesson(String CourseId, String title, String content) throws IOException {
        JSONArray courses = courseDB.loadCourses();
        
        for (int i = 0; i < courses.length(); i++) {
            JSONObject c = courses.getJSONObject(i);
            
            if (c.getString("courseId").equals(CourseId)) {
                
                String LessonId = UUID.randomUUID().toString();
                
                JSONObject lesson = new JSONObject();
                lesson.put("LessonId", LessonId);
                lesson.put("title", title);
                lesson.put("content", content);
                
                c.getJSONArray("lessons").put(lesson);
                break;
            }
            
            courseDB.saveCourses(courses);
        }
    }
    
    public void EditLesson(String courseId, String lessonId, String newTitle, String newcontent) throws IOException {
        JSONArray courses = courseDB.loadCourses();
        
        for (int i = 0; i < courses.length(); i++) {
            JSONObject c = courses.getJSONObject(i);
            if (c.getString("courseId").equals(courseId)) {
                JSONArray lesson = c.getJSONArray("lessons");
                for (int j = 0; j < lesson.length(); j++) {
                    JSONObject l = lesson.getJSONObject(j);
                    if (l.getString("lessonId").equals(lessonId)) {
                        l.put("title", newTitle);
                        l.put("content", newcontent);
                        break;
                    }
                }
            }
            courseDB.saveCourses(courses);
        }
    }
    
    public void deleteLesson(String courseId, String lessonId) throws IOException {
        JSONArray courses = courseDB.loadCourses();
        
        for (int i = 0; i < courses.length(); i++) {
            JSONObject c = courses.getJSONObject(i);
            if (c.getString("courseId").equals(courseId)) {
                JSONArray lesson = c.getJSONArray("lessons");
                for (int j = 0; j < lesson.length(); j++) {
                    if (lesson.getJSONObject(j).getString("lessonId").equals(lessonId)) {
                        lesson.remove(j);
                        break;
                    }
                }
            }
        }
        courseDB.saveCourses(courses);
        
    }
    
    public JSONArray ViewEnrolledStudents(String courseId) throws IOException {
        
        JSONArray users = userDB.loadUsers();
        JSONArray result = new JSONArray();
        for (int i = 0; i < users.length(); i++) {
            JSONObject u = users.getJSONObject(i);
            if (u.getString("role").equals("Student")) {
                for (Object A : u.getJSONArray("enrolledcourses")) {
                    if (A.equals(courseId)) {
                        result.put(u);
                    }
                }
                
            }
        }
        System.out.println(result);
        return result;
    }
}
