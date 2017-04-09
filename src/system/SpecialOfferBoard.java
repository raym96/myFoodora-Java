package system;

import java.util.ArrayList;

import user.model.User;

public interface SpecialOfferBoard {

	// add a new observer to this observable
	public void register(SpecialOfferObserver obs);
	
	// remove an existing observer of this observable
	public void unregister(SpecialOfferObserver obs);
	
	// notify all observers of a change of state of this observable
	public void notifyAllObservers();
	public void notifyAllObservers(Object o);
	
	// notify a appointed observer
	public void notifyObserver(Observer obs);
	public void notifyObserver(Observer obs, Object o);
	
	public void notifyObservers(ArrayList<Observer> observers);	
	public void notifyObservers(ArrayList<User> observers, Object o);
}
