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
		manager.getMyfoodoraService().addUser(user);
	}
	
	public void removeUser(User user){
		manager.getMyfoodoraService().removeUser(user);
	}
	
	public void activateUser(User user) throws UserNotFoundException{
		manager.getMyfoodoraService().activateUser(user);
	}
	
	public void disactivateUser(User user){
		manager.getMyfoodoraService().disactivateUser(user);
		
	}

	@Override
	public void displayUsers() {
		// TODO Auto-generated method stub
		manager.getMyfoodoraService().displayUsers();
	}

	@Override
	public void displayActiveUsers() {
		// TODO Auto-generated method stub
		manager.getMyfoodoraService().displayActiveUsers();
	}

	@Override
	public User selectUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return manager.getMyfoodoraService().selectUser(username);
	}
	
}
