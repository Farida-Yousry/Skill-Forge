import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
    public class Student extends UserAccount{
        ArrayList<Course> enrolledCourses;
        ArrayList<Progress> progress;
        
        public Student() {
        	super();
        	enrolledCourses = new ArrayList<>();
        	progress= new ArrayList<>();
        }
        
        public Student(String username, String pass, String id, String fullname,String role, String email,int age){
            super(username, pass, email, id, fullname, role, age);
            enrolledCourses = new ArrayList<>();
        	progress= new ArrayList<>();
            
        }
        public ArrayList<Course> getEnrolledCourses(){
            return enrolledCourses;
        }
        public ArrayList<Progress> getProgress(){
            return progress;
        }

    public boolean enrollCourse(Course course) {
    	for(Course e:enrolledCourses) {
    	if(e.getCourseId().equals(course.getCourseId())) {
    		return false;
    	}}
    	enrolledCourses.add(course);
    	return true;
    }
   
		public void markLessonCompleted(String lessonId,String courseId) {
			for(Progress e:progress) {
		    	if(e.getLessonId().equals(lessonId)&&e.getCourseId().equals(courseId)) {
		    		return ;
		    	}}
		    	progress.add(new Progress(courseId,lessonId));
		    	
=======
        ArrayList<Course> browseCourses(){
            //return the data from courses,jason

            return null;
        }
        public boolean enrollCourse(Course course){
            for(int i = 0; i < enrolledCourses.size(); i++){
                if(enrolledCourses.get(i).getCourseId().equals(course.getCourseId()))    //found in the enrolled courses
                    return false;
            }
            CourseDatabase cDb = new CourseDatabase();
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
		// student mangement
		/*public void markLessonCompleted(String lessonId) {
			if(!progress.contains(lessonId))
				progress.add(lessonId);
>>>>>>> f2d6651a003d13b890d341dc75d6c7dfb3f4faf1
		}
		public boolean checkIfLessonCompleted(String lessonId,String courseId) {
			for(Progress e:progress) {
		    	if(e.getLessonId().equals(lessonId)&&e.getCourseId().equals(courseId)) {
		    		return true;
		    	}}
		    	return false;
		}
		public ArrayList<Progress> getprogress(){
			return progress;
		}
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
