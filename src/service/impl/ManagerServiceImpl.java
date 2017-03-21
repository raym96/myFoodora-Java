package service.impl;

import exceptions.UserNotFoundException;
import model.user.Manager;
import model.user.User;
import service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private Manager manager;
	
	public ManagerServiceImpl(Manager manager) {
		super();
		this.manager = manager;
	}

	public void addUser(User user){
		manager.getMyfoodora().addUser(user);
	}
	
	public void removeUser(User user){
		manager.getMyfoodora().removeUser(user);
	}
	
	public void activateUser(User user) throws UserNotFoundException{
		manager.getMyfoodora().activateUser(user);
	}
	
	public void disactivateUser(User user){
		manager.getMyfoodora().disactivateUser(user);
		
	}

	@Override
	public void displayUsers() {
		// TODO Auto-generated method stub
		manager.getMyfoodora().displayUsers();
	}

	@Override
	public void displayActiveUsers() {
		// TODO Auto-generated method stub
		manager.getMyfoodora().displayActiveUsers();
	}

	@Override
	public User selectUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return manager.getMyfoodora().selectUser(username);
	}
	
}
