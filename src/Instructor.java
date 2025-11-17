import java.util.ArrayList;

public class Instructor extends UserAccount {
	private ArrayList<Course> createdCourses;

	public Instructor(String userId,String username,String email,String fullName,String password,int age) {
      super(userId,username,password,email,fullName,"Instructor",age);
      createdCourses=new ArrayList<>();
	}
public Instructor() {}
	public ArrayList<Course> getCreatedCourses() {
		return createdCourses;
	}
	public void addCourse(Course course) {
		if(!createdCourses.contains(course)) {
			createdCourses.add(course);
		}
	}

}
