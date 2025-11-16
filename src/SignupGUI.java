import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SignupGUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private JTextField txtFullName;
	private JComboBox<String> roleBox;
	private JTextField txtAge;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtEmail;


	/**
	 * Create the frame.
	 */
	public SignupGUI() {
		setBackground(SystemColor.controlHighlight);
		setLayout(new BorderLayout(20,20));

		
		JLabel lblTitle = new JLabel("Signup",SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 35));
		lblTitle.setForeground(Color.DARK_GRAY);
		add(lblTitle,BorderLayout.NORTH);
		
	    JPanel panel = new JPanel(new GridLayout(6,2,15,15));
	    panel.setBackground(SystemColor.controlHighlight);
	    panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
	    
	    
		JLabel label = new JLabel(" Full Name :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label);
		txtFullName = new JTextField(15);
		txtFullName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFullName.setBackground(SystemColor.inactiveCaption);
		panel.add(txtFullName);
		
		JLabel label_1 = new JLabel(" UserName :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_1);
		txtUserName = new JTextField(15);
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUserName.setBackground(SystemColor.inactiveCaption);
		panel.add(txtUserName);

		JLabel label_2 = new JLabel("  Age : ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_2);
		txtAge = new JTextField(15);
		txtAge.setBackground(SystemColor.inactiveCaption);
		txtAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtAge);

		JLabel label_3 = new JLabel(" Email :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_3);
		txtEmail = new JTextField(15);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setBackground(SystemColor.inactiveCaption);
		panel.add(txtEmail);
		
		JLabel label_4 = new JLabel(" Password :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_4);
		txtPassword = new JTextField(15);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBackground(SystemColor.inactiveCaption);
		panel.add(txtPassword);
		
		JLabel label_5 = new JLabel("  Role : ");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_5);
		roleBox = new JComboBox<>();
		roleBox.setBackground(SystemColor.inactiveCaption);
		roleBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		roleBox.setModel(new DefaultComboBoxModel(new String[] {"Student", "Instructor"}));
		panel.add(roleBox);
       add(panel,BorderLayout.CENTER);
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		buttonPanel.setBackground(SystemColor.controlHighlight);
		
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fullName = txtFullName.getText().trim();
				String userName = txtUserName.getText().trim();
				String role = (String)roleBox.getSelectedItem();
				String password = txtPassword.getText().trim();
				String email = txtEmail.getText().trim();
				try {
					int age=Integer.parseInt(txtAge.getText().trim());
					UserAccount neww=new UserAccount();
					neww.signup(fullName,userName,password,age,role,email);
				}
				catch(NumberFormatException ee) {
					JOptionPane.showMessageDialog(null,"Invalid age");
				}
				catch(NullPointerException eee) {
					JOptionPane.showMessageDialog(null,"Enter the age ");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		buttonPanel.add(btnNewButton);
        add(buttonPanel,BorderLayout.SOUTH);

	}

}