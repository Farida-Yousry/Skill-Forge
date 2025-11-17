import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
    public class Student extends UserAccount{
        public ArrayList<Course> enrolledCourses;
        public Map<String, ArrayList<Boolean>> progress = new HashMap<>();
        CourseDatabase cDb = new CourseDatabase();
        public Student(String username, String pass, String id, String fullname,String role, String email){
            super(username, pass, email, id, fullname, role, 0);
        }
        public ArrayList<String> getEnrolledCourses(){
            ArrayList<String> courses = new ArrayList<>();
            for(int i = 0; i < enrolledCourses.size(); i++)
                courses.add(enrolledCourses.get(i).getCourseId());
            return courses;
        }
        public Map<String, ArrayList<Boolean>> getProgress(){
            return progress;
        }
        ArrayList<Course> browseCourses(){
            return cDb.getAllCourses();
        }
        public boolean enrollCourse(Course course){
            for(int i = 0; i < enrolledCourses.size(); i++){
                if(enrolledCourses.get(i).getCourseId().equals(course.getCourseId()))    //found in the enrolled courses
                    return false;
            }
            ArrayList<Course> courses = cDb.getAllCourses();
            for(int i = 0; i < courses.size(); i++){
                if(courses.get(i).getCourseId().equals(course.getCourseId())){  //found in courses
                    enrolledCourses.add(course);
                    return true;
                }
            }
            return false;
        }
        Lesson accessLesson(String lessonId){
            //search if this lesson id in the enrolledcourses
            return null;

        }
        public boolean checkIfLessonCompleted(String lessonId) {
			return progress.contains(lessonId);
		}

    

    


    
}
