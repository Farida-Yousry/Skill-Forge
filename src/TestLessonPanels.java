import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Minimal Course class for testing
class Course {
    private ArrayList<Lesson> lessons = new ArrayList<>();

    public ArrayList<Lesson> fetchLesson() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        System.out.println("Lesson added: " + lesson.getTitle());
    }

    public boolean deleteLesson(String lessonId) {
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getLessonId().equals(lessonId)) {
                lessons.remove(i);
                System.out.println("Lesson deleted: " + lessonId);
                return true;
            }
        }
        return false;
    }
}

// Minimal Student class for testing
class Student {
    private ArrayList<String> progress = new ArrayList<>();

    public void MarkLessonCompleted(String lessonId) {
        if (!progress.contains(lessonId))
            progress.add(lessonId);
    }

    public boolean checkIfLessonCompleted(String lessonId) {
        return progress.contains(lessonId);
    }
}

public class TestLessonPanels {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create test course
            Course course = new Course();
            course.addLesson(new Lesson("L1", "Lesson 1", "Content for Lesson 1", new ArrayList<>()));
            course.addLesson(new Lesson("L2", "Lesson 2", "Content for Lesson 2", new ArrayList<>()));

            // Instructor Panel
            JFrame instructorFrame = new JFrame("Instructor Lesson Panel");
            instructorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            instructorFrame.setSize(600, 400);
            instructorFrame.add(new InstructorLessonPannel(course));
            instructorFrame.setLocationRelativeTo(null);
            instructorFrame.setVisible(true);

            // Student Panel (optional)
            Student student = new Student();
            if (!course.fetchLesson().isEmpty()) {
                JFrame studentFrame = new JFrame("Student Lesson Panel");
                studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                studentFrame.setSize(400, 300);
                studentFrame.add(new StudentLessonPannel(course.fetchLesson().get(0), student));
                studentFrame.setLocation(650, 100);
                studentFrame.setVisible(true);
            }
        });
    }
}
