package model.customer;

import model.user.*;

public interface Observable {

	// add a new observer to this observable
	public void register(Observer obs);
	
	// remove an existing observer of this observable
	public void unregister(Observer obs);
	
	// notify all observers of a change of state of this observable
	public void notifyObservers();
}
