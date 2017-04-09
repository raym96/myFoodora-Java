/*
 * 
 */
package system;

import java.util.ArrayList;

import restaurant.Meal;
import user.model.Customer;
import user.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class ConcreteSpecialOfferBoard.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ConcreteSpecialOfferBoard implements SpecialOfferBoard{
	
	/** The observers. */
	private ArrayList<SpecialOfferObserver> observers;
	
	/** The specialoffers. */
	private ArrayList<Meal> specialoffers;
	
	/** The changed. */
	private boolean changed;
	
	/**
	 * Instantiates a new concrete special offer board.
	 */
	public ConcreteSpecialOfferBoard(){
		observers = new ArrayList<SpecialOfferObserver>();
		specialoffers = new ArrayList<Meal>();
		this.changed = false;
	}
	
	/**
	 * Adds the special offer.
	 *
	 * @param specialmeal the specialmeal
	 */
	public void addSpecialOffer(Meal specialmeal){
		specialoffers.add(specialmeal);
		this.changed = true;
		this.notifyAllObservers();
	}
	
	/**
	 * Removes the special offer.
	 *
	 * @param so the so
	 */
	public void removeSpecialOffer(SpecialOffer so){
		specialoffers.remove(so);
		this.changed = true;
		this.notifyAllObservers();
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#register(system.SpecialOfferObserver)
	 */
	@Override
	public void register(SpecialOfferObserver obs) {
		// TODO Auto-generated method stub
		observers.add(obs);
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#unregister(system.SpecialOfferObserver)
	 */
	@Override
	public void unregister(SpecialOfferObserver obs) {
		// TODO Auto-generated method stub
		observers.remove(obs);
	}

	/**
	 * Gets the specialoffers.
	 *
	 * @return the specialoffers
	 */
	public ArrayList<Meal> getSpecialoffers() {
		return specialoffers;
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#notifyObserver(system.Observer)
	 */
	@Override
	public void notifyObserver(Observer obs) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#notifyAllObservers()
	 */
	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		if (this.changed){
			for (SpecialOfferObserver ob :observers){
				ob.updateSpecialOffer(this.specialoffers);
			}
			this.changed=false;
		}
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#notifyAllObservers(java.lang.Object)
	 */
	@Override
	public void notifyAllObservers(Object o) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#notifyObserver(system.Observer, java.lang.Object)
	 */
	@Override
	public void notifyObserver(Observer obs, Object o) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#notifyObservers(java.util.ArrayList)
	 */
	@Override
	public void notifyObservers(ArrayList<Observer> observers) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#notifyObservers(java.util.ArrayList, java.lang.Object)
	 */
	@Override
	public void notifyObservers(ArrayList<User> observers, Object o) {
		// TODO Auto-generated method stub
		
	}

	
	
}
