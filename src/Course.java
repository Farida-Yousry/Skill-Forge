import java.util.ArrayList;

public class Course {
	private String courseId;
	private String title;
	private String description;
	private String instructorId;
	private ArrayList<Lesson> lessons;
	private ArrayList<Student> enrolledStudents;
	private CourseDatabase db;

	public Course(String courseId,String title,String description,String instructorId) {
		this.courseId=courseId;
		this.title=title;
		this.description=description;
		this.instructorId=instructorId;
		lessons=new ArrayList<>();
		enrolledStudents=new ArrayList<>();
		db=new CourseDatabase();
	}
// getters
	public String getCourseId() {
		return courseId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public ArrayList<Lesson> getLessons() {
		return lessons;
	}

	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	// setters
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setLessons(ArrayList<Lesson> lessons) {
		this.lessons = lessons;
	}
	public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	public void addLesson(Lesson lesson) {
	lessons.add(lesson);
	db.saveToFile();	
}
   public boolean deleteLesson(Lesson lesson) {
	for(int i=0;i<lessons.size();i++) {
	if(lessons.get(i).getLessonId().equals(lesson.getLessonId())) {
	lessons.remove(i);
	db.saveToFile();
	return true;
	}}
	return false;
}
  public boolean editLesson(Lesson updatedLesson) {
	for(int i=0;i<lessons.size();i++) {
		if(lessons.get(i).getLessonId().equals(updatedLesson.getLessonId())) {
		lessons.set(i,updatedLesson);
		db.saveToFile();
		return true;
		}}
		return false;
}
public ArrayList<Lesson> fetchLesson(){
	return lessons;
}
public Lesson getLessonById(String lessonId) {
	for(int i=0;i<lessons.size();i++) {
		if(lessons.get(i).getLessonId().equals(lessonId)){
			return lessons.get(i);
		}
	}
	return null;	
}
public boolean enrollStudent(Student student) {
	if(!enrolledStudents.contains(student)) {
		enrolledStudents.add(student);
	     db.saveToFile();
	     return true;
	     }
	return false;
}
public boolean removeEnrolledStudent(Student student) {
	if(enrolledStudents.contains(student)) {
		enrolledStudents.remove(student);
	     db.saveToFile();
	     return true;
	     }
	return false;
}
}


