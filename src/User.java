import java.util.Scanner;


import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileNotFoundException;

<<<<<<< Updated upstream:src/UserAccount.java
public class UserAccount extends Validations{


	private String userName;
	private String password;
	private String email;
	private String userId;
	private String fullName;
	private String role;
	private int age;
	UserAccount(String username, String pass, String email, String id, String fullname, String role,int age){
		userName = username;
		password = pass;
=======
public class User{

	protected String userName;
	protected String password;
	protected String email;
	protected String userId;
	//protected String fullName;
	protected String role;
	protected int age;

	
	


	
	public User(String userName, String password, String email, String userId, String role, int age) {
		this.userName = userName;
		this.password = password;
>>>>>>> Stashed changes:src/User.java
		this.email = email;
		this.userId = userId;
		this.role = role;
		this.age = age;
<<<<<<< Updated upstream:src/UserAccount.java
		
	}

	public boolean login(String userName,String password,String role)  {
		try(Scanner read = new Scanner(new File("Users.txt"))){
			while(read.hasNextLine()) {
				String data = read.nextLine().trim();
				if(data.isEmpty())continue;
				String[] hold = data.split(",");
				
				String name = hold[0];
				String pass = hold[1];
				String r = hold[2];
				
				if(name.equals(userName) && pass.equals(hashPassword(password)) && r.equals(role))
					return true;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return false;
	}
=======
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/*public String hashPassword(String password) {
		try {
			MessageDigest pass = MessageDigest.getInstance("SHA-256");
			byte[] temp = pass.digest(password.getBytes());
			StringBuffer s = new StringBuffer();
			for(byte b : temp)
				s.append(String.format("%02x",b & 0xff));
			return s.toString();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
			
	}
	
}

>>>>>>> Stashed changes:src/User.java
	 public void logout() {
		 System.exit(0);
	 }
	public String generateNewId(String role) {
		String id;
		if(role.equals("Student"))
			id = String.format("S%05d",System.currentTimeMillis()%100000);
		else 
			id = String.format("I%05d",System.currentTimeMillis()%100000);
		
		this.userId = id;
		 JOptionPane.showMessageDialog(null,"ID --> " + id);
		 return id;
		
<<<<<<< Updated upstream:src/UserAccount.java
	}
	public void signup(String fullName,String userName,String password,int age,String role,String email) {
		if(!validateFullName(fullName))
		    JOptionPane.showMessageDialog(null,"Invalid name format[Please Enter at least 3 names]");
		else if(!validateEmail(email))
			 JOptionPane.showMessageDialog(null,"Invalid Email format");
		else if(!validateUserName(userName))
			JOptionPane.showMessageDialog(null,"Invalid Username format[Please Enter at least 5 characters]");
		else if(!validatePassword(password))
			JOptionPane.showMessageDialog(null,"Invalid Password format[Please Enter at least 3 digits ]");
		else if(!validateAge(age))
			JOptionPane.showMessageDialog(null,"Invalid age");
	    else {
		this.userName = userName.trim();
		this.password = hashPassword(password);
		this.email = email.trim();
		this.userId = generateNewId(role);
		this.fullName = fullName.trim();
		this.role = role;
		this.age = age;
		//	saveToFile();
		//JOptionPane.showMessageDialog(null,"Account Created");
	}
	}
	public String getUserId(){
		return userId;
	}
	public String getUserName(){
		return userName;
	}
	public String getPass(){
		return password;
	}
	public String getEmail(){
		return email;
	}
	public String getRole(){
		return role;
	}
=======
	}*/

>>>>>>> Stashed changes:src/User.java
}
