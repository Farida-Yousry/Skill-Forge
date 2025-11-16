import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
    public class Student extends UserAccount{
        ArrayList<Course> enrolledCourses;
        Map<String, List<Boolean>> progress;
        public Student(String username, String pass, String id, String fullname,String role, String email){
            super(username, pass, email, id, fullname, role, 0);
        }
        public ArrayList<String> getEnrolledCourses(){
            ArrayList<String> courses = new ArrayList<>();
            for(int i = 0; i < enrolledCourses.size(); i++)
                courses.add(enrolledCourses.get(i).getCourseId());
            return courses;
        }
        public Map<String, List<Boolean>> getProgress(){
            return progress;
        }
        ArrayList<Course> browseCourses(){
            //return the data from courses,jason

            return null;
        }
        void enrollCourse(String courseId){
            //call browse courses and get the array list
            //then search in this array if exist 
            //if exsit search in the erolled sourses if exist
            //if not exist add it to enrolledcourse
            //and update the progress
        }
        Lesson accessLesson(String lessonId){
            //search if this lesson id in the enrolledcourses
            return null;

        }
		// student mangement
		/*public void markLessonCompleted(String lessonId) {
			if(!progress.contains(lessonId))
				progress.add(lessonId);
		}
		public boolean checkIfLessonCompleted(String lessonId) {
			return progress.contains(lessonId);
		}
		public ArrayList<String> getprogress(){
			return progress;
		}*/
            private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
    public String toJson() {
        return "{\n" +
                "  \"id\": " + this.getUserId() + ",\n" +
                "  \"name\": \"" + escape(this.getUserName()) + "\",\n" +
                "}";
    }
    

    


    
}
