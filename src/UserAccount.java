import java.util.Scanner;


import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class UserAccount{

	private String userName;
	private String password;
	private String email;
	private String userId;
	private String fullName;
	private String role;
	private int age;

	
	


	public UserAccount(String id,String username, String pass, String email, String fullname, String role,int age){

		userName = username;
		password = pass;
		this.email = email;
		userId = id;
		this.fullName = fullname;
		this.role = role;
		this.age = age;
		
	}	
	public String hashPassword(String password) {
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public UserAccount(){
	}
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
	}public String getPassword() {
		return password;
	}
	public String getFullName() {
		return fullName;
	}
	public int getAge() {
		return age;
	}
}
