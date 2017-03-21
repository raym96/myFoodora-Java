package service;

import exceptions.UserNotFoundException;
import model.user.User;

public interface ManagerService {

	public void addUser(User user);
	
	public void removeUser(User user);
	
	public void activateUser(User user) throws UserNotFoundException;
	
	public void disactivateUser(User user);
	
	public void displayUsers();
	
	public void displayActiveUsers();
	
	public User selectUser(String username) throws UserNotFoundException;
}
