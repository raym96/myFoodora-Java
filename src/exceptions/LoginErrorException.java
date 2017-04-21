package exceptions;

public class LoginErrorException extends Exception {
	String username;
	String password;
	public LoginErrorException(String username, String password){
		this.username = username;
		this.password = password;
	}
}
