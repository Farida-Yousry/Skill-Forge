import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
<<<<<<< Updated upstream
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
=======
    public class Student extends User{
        private ArrayList<Course> enrolledCourses;
        private  ArrayList<Progress> progress;
        
       
        
   
        public Student(String userName, String password, String email, String userId, String role, int age) {
			super(userName, password, email, userId, role, age);
			this.enrolledCourses = new ArrayList<>();
			this.progress = new ArrayList<>();
		}
       public boolean enrollCourse(Course course) {
    	for(Course e:enrolledCourses) {
    	if(e.getCourseId().equals(course.getCourseId())) {
    		return false;
    	 }
    	}
    	enrolledCourses.add(course);
    	return true;
       }
       public void markLessonCompleted(String courseId,String lessonId) {
			for(Progress e:progress) {
		    	if(e.getLessonId().equals(lessonId)&&e.getCourseId().equals(courseId)) {
		    		return ;
		    	}}
		    	progress.add(new Progress(courseId,lessonId,true));
		    	
		}
		public boolean checkIfLessonCompleted(String courseId,String lessonId) {
			for(Progress e:progress) {
		    	if(e.getLessonId().equals(lessonId)&&e.getCourseId().equals(courseId)) {
		    		return e.isCompleted();
		    	}}
		    	return false;
		}
		public ArrayList<Course> getEnrolledCourses() {
			return enrolledCourses;
		}
		public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
			this.enrolledCourses = enrolledCourses;
		}
		public ArrayList<Progress> getProgress() {
			return progress;
		}
		public void setProgress(ArrayList<Progress> progress) {
			this.progress = progress;
		}
		
		
        /*    private String escape(String s) {
>>>>>>> Stashed changes
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
    public String toJson() {
        return "{\n" +
                "  \"id\": " + this.getUserId() + ",\n" +
                "  \"name\": \"" + escape(this.getUserName()) + "\",\n" +
                "}";
    }*/
    

    


    
}
