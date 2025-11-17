
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class StudentLessonPannel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Lesson lesson;
	private JButton btnMarkCompleted;
	private JLabel lblTitle;
	private JTextArea txtArea;
	private Student student;
	private Course course;
	/**
	 * Create the panel.
	 */
	public StudentLessonPannel(Lesson lesson,Student student,Course course) {
		this.lesson=lesson;
		this.student=student;
		this.course=course;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		lblTitle=new JLabel(lesson.getTitle());
		add(lblTitle);
		
		txtArea = new JTextArea(lesson.getContent());
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setEditable(false);
		add(txtArea);
		
		 btnMarkCompleted = new JButton("Mark Completed");
		add(btnMarkCompleted);
		
		btnMarkCompleted.addActionListener(new ActionListener() 
		{public void actionPerformed(ActionEvent e) {
<<<<<<< Updated upstream
			student.markLessonCompleted(lesson.getLessonId(),course.getCourseId());
=======
			student.markLessonCompleted(lesson.getLessonId());
>>>>>>> Stashed changes
			btnMarkCompleted.setEnabled(false);
			lblTitle.setText(lesson.getTitle() + "Completed..");
			//save in File
			}
		});
		
	 if(student.checkIfLessonCompleted(lesson.getLessonId(),course.getCourseId())) {
		 btnMarkCompleted.setEnabled(false);
		 lblTitle.setText(lesson.getTitle() + "Completed..");
<<<<<<< Updated upstream
		 student.markLessonCompleted(lesson.getLessonId(),course.getCourseId());
=======
		 student.markLessonCompleted(lesson.getLessonId());
>>>>>>> Stashed changes
	 }
		
		

	}

}