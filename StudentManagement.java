package BackEnd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentManagement {

    private final List<Course> courses;
    private final List<Student> students;
    private final CourseDatabase cdb;
    private final UserDatabase udb;
    private final Map<String, Set<String>> completedLessonsMap = new HashMap<>();

    public StudentManagement(CourseDatabase cdb,UserDatabase udb) throws IOException {
        this.cdb = cdb;
        this.udb=udb;
        this.courses = cdb.getAllCourses();
        this.students = udb.getStudents();
    }

    public List<Course> getAvailableCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> getEnrolledCourses(Student student) {
        List<Course> result = new ArrayList<>();
        List<String> enrolledIds = student.getEnrolledCourseIDs();

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
        if (!student.getEnrolledCourseIDs().contains(courseId)) {
            student.getEnrolledCourseIDs().add(courseId);
        }
        if (!course.getEnrolledStudentIds().contains(studentId)) {
            course.getEnrolledStudentIds().add(studentId);
        }
        udb.saveUsers(students);
        cdb.saveCourses(courses);
    }

    public List<Lesson> getCourseLessons(String courseId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        return new ArrayList<>(course.getLessons());
    }

    public List<Lesson> getLessonsForStudentInCourse(Student student, String courseId) {
        if (!student.getEnrolledCourseIDs().contains(courseId)) {
            throw new IllegalStateException("Student is not enrolled in this course");
        }
        return getCourseLessons(courseId);
    }

    public void completeLesson(Student student, String courseId, String lessonId) throws IOException {
        Course course = findCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        if (!student.getEnrolledCourseIDs().contains(courseId)) {
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
        udb.saveUsers(students);
    }

    private void updateStudentProgress(Student student) {
        List<String> enrolledCourseIds = student.getEnrolledCourseIDs();
        if (enrolledCourseIds.isEmpty()) {
            student.setProgress(0.0);
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
        student.setProgress(progress);
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
