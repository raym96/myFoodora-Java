package exceptions;

import model.users.User;

public class UserNotFoundException extends Exception{
	
	public static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String username){
		System.out.println("[UserNotFoundException] :: The user '" + username + "' is not recognized");
	}
	
}
