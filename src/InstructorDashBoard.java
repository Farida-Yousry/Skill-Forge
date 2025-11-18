import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InstructorDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInstructor;

	/**
	 * Create the frame.
	 */
	public InstructorDashBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		txtInstructor = new JTextField();
		txtInstructor.setBackground(SystemColor.activeCaption);
		txtInstructor.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtInstructor.setText("                   Instructor");
		txtInstructor.setEditable(false);
		contentPane.add(txtInstructor,BorderLayout.NORTH);
		txtInstructor.setColumns(10);
		
		JPanel buttonPanelContainer = new JPanel();
		buttonPanelContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
		contentPane.add(buttonPanelContainer,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1,10,10));
		buttonPanelContainer.add(buttonPanel);
		
		JButton btnNewButton = new JButton("Manage Courses");
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonPanel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			new CourseGUI().setVisible(true);
	 		}
	 	    
	    	});
		
		JButton btnNewButton_1 = new JButton("Manage Lessons");
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonPanel.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			new StudentLessonPannel().setVisible(true);
	 		}
	 	    
	    	});
		

	}

}
