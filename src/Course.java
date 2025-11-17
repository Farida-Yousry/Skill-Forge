import java.util.ArrayList;

public class Course {
	private String courseId;
	private String title;
	private String description;
	private String instructorId;
	private ArrayList<Lesson> lessons;
<<<<<<< Updated upstream
	private ArrayList<Student> enrolledStudents;

	public Course(String courseId,String title,String description,String instructorId) {
		this.courseId=courseId;
		this.title=title;
		this.description=description;
		this.instructorId=instructorId;
		lessons=new ArrayList<>();
		enrolledStudents=new ArrayList<>();
	}
// getters
	public String getCourseId() {
=======
	private ArrayList<String> enrolledStudents;
	//private CourseDatabase db;


   public String getCourseId() {
>>>>>>> Stashed changes
		return courseId;
	}

	public Course(String courseId, String title, String description, String instructorId) {
	super();
	this.courseId = courseId;
	this.title = title;
	this.description = description;
	this.instructorId = instructorId;
	lessons =  new ArrayList<>();
	enrolledStudents = new ArrayList<>();
}

	public String getTitle() {
		return title;
	}
<<<<<<< Updated upstream

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
	/*public void setCourseId(String courseId) {
		this.courseId = courseId;
	*/}
=======
>>>>>>> Stashed changes
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
<<<<<<< Updated upstream
	/*public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	*/}
=======
	public String getInstructorId() {
		return instructorId;
	}
	
	public ArrayList<Lesson> getLessons() {
		return lessons;
	}
>>>>>>> Stashed changes
	public void setLessons(ArrayList<Lesson> lessons) {
		this.lessons = lessons;
	}
	public ArrayList<String> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(ArrayList<String> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	public void addLesson(Lesson lesson) {
<<<<<<< Updated upstream
	lessons.add(lesson);
	saveToFile();	
=======
	lessons.add(lesson);	
>>>>>>> Stashed changes
}
   public boolean deleteLesson(String lessonId) {
	for(int i=0;i<lessons.size();i++) {
	if(lessons.get(i).getLessonId().equals(lessonId)) {
	lessons.remove(i);
<<<<<<< Updated upstream
	saveToFile();
=======
	
>>>>>>> Stashed changes
	return true;
	}}
	return false;
}
  public boolean editLesson(Lesson updatedLesson) {
	for(int i=0;i<lessons.size();i++) {
		if(lessons.get(i).getLessonId().equals(updatedLesson.getLessonId())) {
		lessons.set(i,updatedLesson);
<<<<<<< Updated upstream
		saveToFile();
=======
>>>>>>> Stashed changes
		return true;
		
		}}
	return false;	
}

public void enrollStudent(String studentId) {
	if(!enrolledStudents.contains(studentId)) {
		enrolledStudents.add(studentId);
	     }
	
}
public boolean removeEnrolledStudent(String studentId) {
	return	enrolledStudents.remove(studentId);
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
<<<<<<< Updated upstream
public boolean enrollStudent(Student student) {
	if(!enrolledStudents.contains(student)) {
		enrolledStudents.add(student);
	     saveToFile();
	     return true;
	     }
	return false;
}
public boolean removeEnrolledStudent(Student student) {
	if(enrolledStudents.contains(student)) {
		enrolledStudents.remove(student);
	     saveToFile();
	     return true;
	     }
	return false;
}

import java.util.List;

public class Course {
    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private ArrayList<Lesson> lessons;
    private ArrayList<String> enrolledStudents; 

    public Course(String courseId, String title, String description, String instructorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();      
=======
>>>>>>> Stashed changes
}
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

    public ArrayList<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setEnrolledStudents(ArrayList<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
    
}
