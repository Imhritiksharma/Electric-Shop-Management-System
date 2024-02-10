package pkg_exception;

public class PasswordNotMatchException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public PasswordNotMatchException() {
		super();
	}
	public String toString() {
		return "PasswordNotMatchException";
	}

}
