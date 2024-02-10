package pkg_password;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Password implements Serializable {
	private String emailid;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		boolean matchedpass = Pattern.matches("((?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[@#%&]).{6,25})",password);
		if(matchedpass) {
			this.password = password;
		}
		else {
			System.out.println("Enter a Strong Password , Suggestion -> MyShop@1");
		}
	}
	public void setEmailid(String emailid) {
		boolean matchedemail = Pattern.matches("^[a-z][a-z\\d._]*[a-z\\d]+@[a-z]+[\\.][a-z]{2,3}$", emailid);
		if(matchedemail) {
			this.emailid = emailid;
		}
		else {
			System.out.println("Enter vailid Email");
		}
	}
	public String getemailid() {
		return emailid;
	}

	public Password(String emailid,String password) {
		super();
		setEmailid(emailid);
		setPassword(password);
	}

	public Password() {
		super();
	}

	@Override
	public String toString() {
		return "Password & Email [password = " + password + "  Email id = "+emailid+ "]";
	}
	
}
