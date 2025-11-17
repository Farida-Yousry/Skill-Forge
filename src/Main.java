import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Initialize database and managers
        Database db = new Database();
        UserManager userManager = new UserManager(db);
        CourseManager courseManager = new CourseManager(db);
        LessonManager lessonManager = new LessonManager(courseManager);

        // --- 1. Sign up users ---
        Instructor instructor = new Instructor("aliceProf", "pass123", "alice@gmail.com", "", "Instructor", 40);
        Student student1 = new Student("bobStudent", "abc123", "bob@gmail.com", "", "Student", 22);
        Student student2 = new Student("charlieStudent", "xyz456", "charlie@yahoo.com", "", "Student", 25);

        userManager.signup(instructor, instructor.getUserName(), "pass123", instructor.getAge(), "Instructor", instructor.getEmail());
        userManager.signup(student1, student1.getUserName(), "abc123", student1.getAge(), "Student", student1.getEmail());
        userManager.signup(student2, student2.getUserName(), "xyz456", student2.getAge(), "Student", student2.getEmail());

        System.out.println("All users: " + userManager.getAllUsers().size());

        // --- 2. Login ---
        User loggedInstructor = userManager.login("aliceProf", "pass123", "Instructor");
        User loggedStudent1 = userManager.login("bobStudent", "abc123", "Student");
        User loggedStudent2 = userManager.login("charlieStudent", "xyz456", "Student");

        System.out.println("Instructor logged in: " + (loggedInstructor != null));
        System.out.println("Student1 logged in: " + (loggedStudent1 != null));
        System.out.println("Student2 logged in: " + (loggedStudent2 != null));

        // --- 3. Create courses ---
        courseManager.createCourse("Java Basics", "Intro to Java", loggedInstructor.getUserId());
        courseManager.createCourse("Python Basics", "Intro to Python", loggedInstructor.getUserId());

        System.out.println("Courses after creation: " + courseManager.getAllCourses().size());

        // --- 4. Edit a course ---
        Course javaCourse = courseManager.getAllCourses().get(0);
        boolean edited = courseManager.editCourse(javaCourse.getCourseId(), "Advanced Java", "Deep dive into Java");
        System.out.println("Java course edited: " + edited);

        // --- 5. Add lessons ---
        Lesson lesson1 = new Lesson("L001", "Variables", "Understanding variables", null);
        Lesson lesson2 = new Lesson("L002", "Loops", "For and while loops", null);
        courseManager.addLessonToCourse(javaCourse.getCourseId(), lesson1);
        courseManager.addLessonToCourse(javaCourse.getCourseId(), lesson2);

        System.out.println("Lessons in Java course: " + javaCourse.getLessons().size());

        // --- 6. Edit lesson ---
        Lesson updatedLesson = new Lesson("L002", "Loops in Java", "Updated content", null);
        boolean lessonEdited = javaCourse.editLesson(updatedLesson);
        System.out.println("Lesson edited: " + lessonEdited);

        // --- 7. Delete lesson ---
        boolean lessonDeleted = courseManager.deleteLessonFromCourse(javaCourse.getCourseId(), "L001");
        System.out.println("Lesson deleted: " + lessonDeleted);

        // --- 8. Enroll students ---
        courseManager.enrollStudent(javaCourse.getCourseId(), loggedStudent1.getUserId());
        courseManager.enrollStudent(javaCourse.getCourseId(), loggedStudent2.getUserId());

        System.out.println("Students enrolled: " + javaCourse.getEnrolledStudents().size());

        // --- 9. Student progress ---
        if (loggedStudent1 instanceof Student) {
            ((Student) loggedStudent1).markLessonCompleted(javaCourse.getCourseId(), "L002");
            boolean completed = ((Student) loggedStudent1).checkIfLessonCompleted(javaCourse.getCourseId(), "L002");
            System.out.println("Student1 completed L002: " + completed);
        }

        if (loggedStudent2 instanceof Student) {
            ((Student) loggedStudent2).markLessonCompleted(javaCourse.getCourseId(), "L002");
            boolean completed = ((Student) loggedStudent2).checkIfLessonCompleted(javaCourse.getCourseId(), "L002");
            System.out.println("Student2 completed L002: " + completed);
        }

        // --- 10. Unenroll student ---
        boolean removed = javaCourse.removeEnrolledStudent(loggedStudent1.getUserId());
        System.out.println("Student1 unenrolled: " + removed);
        System.out.println("Students left enrolled: " + javaCourse.getEnrolledStudents().size());

        // --- 11. Delete course ---
        boolean courseDeleted = courseManager.removeCourse(javaCourse.getCourseId());
        System.out.println("Java course deleted: " + courseDeleted);
        System.out.println("Remaining courses: " + courseManager.getAllCourses().size());

        // --- 12. Final JSON debug ---
        System.out.println("User.json path: " + new java.io.File("user.json").getAbsolutePath());
        System.out.println("Course.json path: " + new java.io.File("course.json").getAbsolutePath());
    }
}