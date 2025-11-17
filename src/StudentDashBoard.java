import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;

public class StudentDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtStudent;


	/**
	 * Create the frame.
	 */
	public StudentDashBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0,1,10,10));
		setContentPane(contentPane);
		
		txtStudent = new JTextField();
		txtStudent.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtStudent.setBackground(SystemColor.activeCaption);
		txtStudent.setText("                      Student");
		txtStudent.setEditable(false);
		contentPane.add(txtStudent);
		
		
		JButton btnNewButton = new JButton("Browse Course");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enroll Course");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		contentPane.add(btnNewButton_1);
		
		
		JButton btnNewButton_3 = new JButton("Progress Tracking");
		btnNewButton_3.setForeground(SystemColor.desktop);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBackground(SystemColor.inactiveCaption);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Lesson Access");
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnNewButton_2);

	}

}
