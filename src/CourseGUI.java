import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CourseGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtInstructor;
	private JTextField txtTitle;
	private JTextField txtCourseId;
	private JTextField txtDescription;
	private JTextField txtInstructorId;
	private JList<Course> courses;
	private DefaultListModel<Course> courseModel;
	private JButton btnAdd,btnEdit,btnDelete,btnView;
	private CourseDatabase courseDb ;

	/**
	 * Create the panel.
	 */
	public CourseGUI() {
		 courseDb = new CourseDatabase();
		setLayout(new BorderLayout());
		
		courseModel=new DefaultListModel<>();
		
		for(Course e:courseDb.getAllCourses()) {
			courseModel.addElement(e);
		}
		
		courses = new JList<>(courseModel);
		courses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		courses.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
		    load();
			}
		});
		    add(new JScrollPane(courses),BorderLayout.WEST);
		    JPanel panel=new JPanel(new GridLayout(3,1,10,10));
		    
		    txtTitle =new JTextField();
            txtCourseId = new JTextField();
            txtInstructorId = new JTextField();
		    txtDescription=new JTextField();
		    
      panel.add(createPanel("Title",txtTitle));
      panel.add(createPanel("CourseID",txtCourseId));
      panel.add(createPanel("InstructorId",txtInstructorId));   
	  panel.add(createPanel("Description",txtDescription));
	  
	  add(panel,BorderLayout.CENTER);
	  
	  JPanel mainPanel=new JPanel(new FlowLayout());
	  
		    
       btnAdd = new JButton("Add Course");
       btnEdit = new JButton("Edit Course");
       btnDelete = new JButton("Delete Course");
       btnView = new JButton("View Students");
       
       mainPanel.add(btnAdd);
       mainPanel.add(btnEdit);
       mainPanel.add(btnDelete);
       
       add(mainPanel,BorderLayout.SOUTH);
       
   	 btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			addCourse();
		}
	    
   	});
	btnEdit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			editCourse();
		}
	    
   	});
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			deleteCourse();
		}
	    
   	});
   	
	btnView.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			view();
		}
	    
   	});  	
   	
	}
	
	private JPanel createPanel(String text,Component element) {
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(new JLabel(text),BorderLayout.NORTH);
		panel.add(element,BorderLayout.CENTER);
		return panel;
		
	}
		   private void addCourse() {
				String title = txtTitle.getText().trim();
				String courseId = txtCourseId.getText().trim();
				String description = txtDescription.getText().trim();
				String instructorId = txtInstructorId.getText().trim();
					
				Course course = new Course(courseId,title,description,instructorId);
				courseDb.writeToFile(course);
				courseModel.addElement(course);
				JOptionPane.showMessageDialog(this,"Course Added Successfully");

					}
			private void deleteCourse() {
				Course selected = courses.getSelectedValue();
				if(selected==null) {
					JOptionPane.showMessageDialog(this,"Select a Course to be deleted");
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this student?","Confirm",JOptionPane.YES_NO_OPTION);
			    if(confirm == JOptionPane.YES_OPTION) {
			      boolean deleted=courseDb.deleteCourse(selected.getCourseId());
			   if(deleted) {
				   courseModel.removeElement(selected);
			   JOptionPane.showMessageDialog(this,"Course deleted Successfully");
			    }
			   else
				JOptionPane.showMessageDialog(this,"Course Not Found");
				
			}
	   }
			
			private void editCourse() {
				Course selected = courses.getSelectedValue();
				if(selected==null) {
					JOptionPane.showMessageDialog(this,"Select a Course First");
					return;
				}
				selected.setTitle(txtTitle.getText().trim());
				selected.setDescription(txtDescription.getText().trim());
				
				ArrayList<Course> updateCourses = new ArrayList<>();
				for(int i=0;i<courseModel.size();i++) {
					updateCourses.add(courseModel.getElementAt(i));
				}
				for(int i=0;i<courseModel.size();i++) {
				if(updateCourses.get(i).getCourseId().equals(selected.getCourseId()));
				updateCourses.set(i,selected);{
				break;
				}
				}
				
				courses.repaint();
				
				JOptionPane.showMessageDialog(this,"Course Updated Successfully");
				
			}
            private void load(){
           	 Course selected = courses.getSelectedValue();
           	 if(selected==null) {
					JOptionPane.showMessageDialog(this,"Please select a Course");
					return;
				}
           	 txtTitle.setText(selected.getTitle());
        
		}
            private void view(){
              	 Course selected = courses.getSelectedValue();
              	 if(selected==null) {
   					JOptionPane.showMessageDialog(this,"Please select a Course");
   					return;
   				}
              	 ArrayList<Student> students = selected.getEnrolledStudents();
              	 if(students.isEmpty()) {
              		JOptionPane.showMessageDialog(CourseGUI.this,"No Students enrolled in this Course!!");
              		return;
              	 }
              		StringBuilder sb = new StringBuilder("Enrolled Sudents:\n");
              		for(Student s: students) {
              			sb.append("-".append(s.getUsername()).append("(").append(s.getEmail()).append(")\n"));
              		}
              		JTextArea textArea = new JTextArea(sb.toString());
              		textArea.setEditable(false);
              		JScrollPane scrollPane = new JScrollPane(textArea);
              		scrollPane.setPreferredSize(new Dimension(300,200));
              		JOptionPane.showMessageDialog(CourseGUI.this,scrollPane,"Students",JOptionPane.INFORMATION_MESSAGE);
              	 }
           
   		}
            