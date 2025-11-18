

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {

    private final UserAccount currentUser;
    private final StudentManagement studentManagement;       // null if not student
    private final InstructorManagement instructorManagement; // null if not instructor

    public CourseManagement(CourseDatabase2 cdb,UserDatabase2 udb, UserAccount currentUser) throws IOException {
        this.currentUser = currentUser;
        if (currentUser instanceof Student) {
            this.studentManagement = new StudentManagement(cdb,udb);
            this.instructorManagement = null;
        } else if (currentUser instanceof Instructor) {
            this.instructorManagement = new InstructorManagement(udb,cdb);
            this.studentManagement = null;
        } else {
            this.studentManagement = null;
            this.instructorManagement = null;
        }
    }

    private Student asStudent() {
        if (!(currentUser instanceof Student s)) {
            throw new IllegalStateException("Current user is not a Student");
        }
        return (Student) currentUser;
    }

    private Instructor asInstructor() {
        if (!(currentUser instanceof Instructor i)) {
            throw new IllegalStateException("Current user is not an Instructor");
        }
        return (Instructor) currentUser;
    }

    private StudentManagement getStudentManagement() {
        if (studentManagement == null) {
            throw new IllegalStateException("StudentManagement is not available for this user");
        }
        return studentManagement;
    }

    private InstructorManagement getInstructorManagement() {
        if (instructorManagement == null) {
            throw new IllegalStateException("InstructorManagement is not available for this user");
        }
        return instructorManagement;
    }

    public ArrayList<Course> getAvailableCourses() {
        Student s = asStudent();
        return getStudentManagement().getAvailableCourses();
    }

    public ArrayList<Course> getEnrolledCourses() {
        Student s = asStudent();
        return getStudentManagement().getEnrolledCourses(s);
    }

    public void enrollCourse(String courseId) throws IOException {
        Student s = asStudent();
        getStudentManagement().enrollCourse(s, courseId);
    }

    public ArrayList<Lesson> getLessonsForCurrentStudentInCourse(String courseId) {
        Student s = asStudent();
        return getStudentManagement().getLessonsForStudentInCourse(s, courseId);
    }

    public void completeLesson(String courseId, String lessonId) throws IOException {
        Student s = asStudent();
        getStudentManagement().completeLesson(s, courseId, lessonId);
    }

    public Course createCourse(String title, String description) throws IOException {
        Instructor inst = asInstructor();
        return getInstructorManagement().createCourse(inst, title, description);
    }

    public void editCourse(String courseId, String newTitle, String newDescription) throws IOException {
        asInstructor(); // just to check role
        getInstructorManagement().editCourse(courseId, newTitle, newDescription);
    }

    public void deleteCourse(String courseId) throws IOException {
        asInstructor();
        getInstructorManagement().deleteCourse(courseId);
    }

    public Lesson addLesson(String courseId,
            String title,
            String content,
            List<String> resources) throws IOException {

        asInstructor();
        return getInstructorManagement().addLesson(courseId, title, content, (ArrayList<String>) resources);
    }

    public void editLesson(String courseId,
            String lessonId,
            String newTitle,
            String newContent,
            List<String> newResources) throws IOException {

        asInstructor();
        getInstructorManagement().editLesson(courseId, lessonId, newTitle, newContent, newResources);
    }

    public void deleteLesson(String courseId, String lessonId) throws IOException {
        asInstructor();
        getInstructorManagement().deleteLesson(courseId, lessonId);
    }

    public List<Student> getEnrolledStudents(String courseId) {
        asInstructor();
        return getInstructorManagement().getEnrolledStudents(courseId);
    }
}
