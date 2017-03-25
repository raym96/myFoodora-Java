package model.customer;

import java.util.ArrayList;

import model.user.MessageBoardUser;
import model.user.User;

public interface Observable {
	// add a new observer to this observable
	public void register(MessageBoardUser obs);
	
	// remove an existing observer of this observable
	public void unregister(MessageBoardUser obs);
	
	// notify all observers of a change of state of this observable
	public void notifyAllObservers();
	public void notifyAllObservers(Object o);
	
	// notify a appointed observer
	public void notifyObserver(MessageBoardUser obs);
	public void notifyObserver(MessageBoardUser obs, Object o);
	
	public void notifyObservers(ArrayList<MessageBoardUser> observers);	
	public void notifyObservers(ArrayList<User> observers, Object o);
}
