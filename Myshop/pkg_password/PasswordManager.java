package pkg_password;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PasswordManager {
	
	ObjectInputStream ois_password= null;
	ObjectOutputStream oos_password= null;
	
	File password_file = null;
	ArrayList<Password> Password_list = null;
	
	@SuppressWarnings("unchecked")
	public PasswordManager() {
		password_file = new File("Password.dat");
		Password_list = new ArrayList<Password>();
		if(password_file.exists()){
			try {
				ois_password = new ObjectInputStream(new FileInputStream(password_file));
				Password_list = (ArrayList<Password>) ois_password.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean isEmpty(){
		return Password_list.isEmpty();
	}
	public boolean firstLogin(String email,String password){
		if(!Password_list.isEmpty()){
			return false;
		}
		Password_list.add(new Password(email, password));
		if((Password_list.get(0).getemailid() == null) || (Password_list.get(0).getPassword() == null)){
			return false;
		}
		wirteToFile();
		return true;
	}
	public boolean singin(String email,String password){
		for(Password pw:Password_list){
			if(pw.getemailid().equals(email)){
				if(pw.getPassword().equals(password)){
					System.out.println("Welcome back to System");
					return true;
				}else{
					System.out.println("Incurrect password");
					return false;
				}
			}
		}
		System.out.println("LogIn Carefully");
		return false;
	}
	
	public void viewLogininfo() {
		for(Password ps:Password_list) {
			System.out.println(ps);
		}
	}
	public boolean changepassword(String emailid ,String oldpass,String newpass) {
		for(Password changepass:Password_list) {
			if(changepass.getemailid().equals(emailid)) {
				if(changepass.getPassword().equals(oldpass)) {
					changepass.setPassword(newpass);
					if(!changepass.getPassword().equals(newpass)){
						System.out.println("Password is not Update");
						return false;
					}
					System.out.println("Password is Changed");
					wirteToFile();
					return true;
				}
				else {
					System.out.println("Incorrect Password");
					return false;
				}
			}
		}
		System.out.println("User not found");
		return false;
	}
	public void wirteToFile() {
		try {
			oos_password = new ObjectOutputStream(new FileOutputStream(password_file));
			oos_password.writeObject(Password_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
