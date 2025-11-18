//package BackEnd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class StudentManagement {

    private final ArrayList<Course> courses;
    private final ArrayList<Student> students;
    private final CourseDatabase2 cdb;
    private final UserDatabase2 udb;
    private final Map<String, Set<String>> completedLessonsMap = new HashMap<>();

    public StudentManagement(CourseDatabase2 cdb, UserDatabase2 udb) throws IOException {
        this.cdb = cdb;
        this.udb=udb;
        this.courses = cdb.getAllCourses();
        this.students = udb.getStudents();
    }

    public ArrayList<Course> getAvailableCourses() {
        return new ArrayList<>(courses);
    }

    public ArrayList<Course> getEnrolledCourses(Student student) {
        ArrayList<Course> result = new ArrayList<>();
        ArrayList<String> enrolledIds = student.getEnrolledCoursesIDs();

        for (Course c : courses) {
            if (enrolledIds.contains(c.getCourseId())) {
                result.add(c);
            }
        }
        return result;
    }

    public void enrollCourse(Student student, String courseId) throws IOException {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        String studentId = student.getUserId();
        if (!student.getEnrolledCoursesIDs().contains(courseId)) {
            //student.getEnrolledCourseIDs().add(courseId);
            //TODO REPLACE
            ArrayList<String> enrolledCourses = student.getEnrolledCoursesIDs();
            enrolledCourses.add(courseId);
            student.setEnrolledCoursesIDs(enrolledCourses);
            
        }
        // if (!course.getEnrolledStudentIds().contains(studentId)) {
        //     course.getEnrolledStudentIds().add(studentId);
        // }
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getCourseId().equals(courseId)){
                courses.get(i).enrollStudent(student);
                cdb.saveCourses(courses);
                break;
            }
        }
        //udb.saveUsers(students);
    }

    public ArrayList<Lesson> getCourseLessons(String courseId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        return new ArrayList<>(course.getLessons());
    }

    public ArrayList<Lesson> getLessonsForStudentInCourse(Student student, String courseId) {
        if (!student.getEnrolledCoursesIDs().contains(courseId)) {
            throw new IllegalStateException("Student is not enrolled in this course");
        }
        return getCourseLessons(courseId);
    }

    public void completeLesson(Student student, String courseId, String lessonId) throws IOException {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        if (!student.getEnrolledCoursesIDs().contains(courseId)) {
            throw new IllegalStateException("Student is not enrolled in this course");
        }
        boolean lessonExists = false;
        for (Lesson lesson : course.getLessons()) {
            if (lesson.getLessonId().equals(lessonId)) {
                lessonExists = true;
                break;
            }
        }
        if (!lessonExists) {
            throw new IllegalArgumentException("Lesson not found in this course: " + lessonId);
        }
        String key = student.getUserId() + "|" + courseId;
        Set<String> completedLessonIds
                = completedLessonsMap.computeIfAbsent(key, k -> new HashSet<>());
        completedLessonIds.add(lessonId);
        updateStudentProgress(student);
        udb.saveUsers((ArrayList<UserAccount>) (ArrayList<?>) students);
    }

    private void updateStudentProgress(Student student) {
        /*ArrayList<String> enrolledCourseIds = student.getEnrolledCoursesIDs();
        if (enrolledCourseIds.isEmpty()) {
            student.setProgress();
            return;
        }
        int completedCourses = 0;

        for (String cId : enrolledCourseIds) {
            Course c = findCourseById(cId);
            if (c == null) {
                continue;
            }

            String key = student.getUserId() + "|" + cId;
            Set<String> completedLessonIds = completedLessonsMap.get(key);
            if (c.getLessons().isEmpty()) {
                continue; // or treat as completed
            }

            if (completedLessonIds != null
                    && completedLessonIds.size() == c.getLessons().size()) {
                completedCourses++;
            }
        }

        double progress = (double) completedCourses / enrolledCourseIds.size();
        student.setProgress(progress);*/
    }

    private Course findCourseById(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
