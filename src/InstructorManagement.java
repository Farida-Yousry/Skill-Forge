import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstructorManagement {

    private int cid = 0;
    private int lid = 0;
    private final ArrayList<Course> courses;
    private final ArrayList<UserAccount> users;
    private  CourseDatabase2 cdb;
    private  UserDatabase2 udb;

    public InstructorManagement(UserDatabase2 udb,CourseDatabase2 cdb) throws IOException {
        this.cdb=cdb;
        this.udb=udb;
        this.courses = cdb.getAllCourses();
       
        this.users = udb.getAllUsers();
    }

    public Course createCourse(Instructor instructor, String title, String description) throws IOException {
        String courseId = "" + (cid++);
        Course course = new Course(courseId, title, description, instructor.getUserId());
        courses.add(course);
        instructor.getCreatedCourseIds().add(courseId);
        cdb.saveCourses(courses);
        return course;
    }

    public void editCourse(String courseId, String newTitle, String newDescription) throws IOException {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        course.setTitle(newTitle);
        course.setDescription(newDescription);
        cdb.saveCourses(courses);
    }

    public void deleteCourse(String courseId) throws IOException {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }
        UserAccount u = findUserById(course.getInstructorId());
        if (u instanceof Instructor inst) {
            inst.getCreatedCourseIds().remove(courseId);
        }
        courses.remove(course);
        cdb.saveCourses(courses);
    }

    public Lesson addLesson(String courseId,
            String title,
            String content,
            ArrayList<String> resources) throws IOException {

        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        String lessonId = "" + (lid++);
        Lesson lesson = new Lesson(
                lessonId,
                title,
                content,
                resources != null ? resources : new ArrayList<>()
        );
        course.getLessons().add(lesson);
        cdb.saveCourses(courses);
        return lesson;

    }

    public void editLesson(String courseId,
            String lessonId,
            String newTitle,
            String newContent,
            List<String> newResources) throws IOException {

        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }

        Lesson target = null;
        for (Lesson lesson : course.getLessons()) {
            if (lesson.getLessonId().equals(lessonId)) {
                target = lesson;
                break;
            }
        }

        if (target == null) {
            throw new IllegalArgumentException("Lesson not found: " + lessonId);
        }

        if (newTitle != null) {
            target.setTitle(newTitle);
        }
        if (newContent != null) {
            target.setContent(newContent);
        }
        if (newResources != null) {
            target.setResources((ArrayList<String>) newResources);
        }
        cdb.saveCourses(courses);

    }

    public void deleteLesson(String courseId, String lessonId) throws IOException {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        Lesson target = null;
        for (Lesson lesson : course.getLessons()) {
            if (lesson.getLessonId().equals(lessonId)) {
                target = lesson;
                break;
            }
        }
        if (target == null) {
            throw new IllegalArgumentException("Lesson not found: " + lessonId);
        }
        course.getLessons().remove(target);
        cdb.saveCourses(courses);

    }

    public List<Student> getEnrolledStudents(String courseId) {

        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        List<Student> result = new ArrayList<>();
        for (String studentId : course.getEnrolledStudentIDs()) {
            UserAccount u = findUserById(studentId);
            if (u instanceof Student s) {
                result.add(s);
            }
        }

        return result;
    }

    private Course findCourseById(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private UserAccount findUserById(String id) {
        for (UserAccount u : users) {
            if (u.getUserId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
