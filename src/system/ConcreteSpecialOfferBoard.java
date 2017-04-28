/*
 * 
 */
package system;

import java.util.ArrayList;

import restaurant.Meal;
import user.model.Customer;
import user.model.User;


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
	 * @param meal the specialmeal
	 */
	public void addSpecialOffer(Meal meal){
		specialoffers.add(meal);
		this.changed = true;
		this.notifyAllSpecialObservers(meal);
	}
	
	/**
	 * Removes the special offer.
	 *
	 * @param meal the meal
	 */
	public void removeSpecialOffer(Meal meal){
		specialoffers.remove(meal);
		this.changed = true;
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#register(system.SpecialOfferObserver)
	 */
	@Override
	public void register(SpecialOfferObserver observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	/* (non-Javadoc)
	 * @see system.SpecialOfferBoard#unregister(system.SpecialOfferObserver)
	 */
	@Override
	public void unregister(SpecialOfferObserver obs) {
		// TODO Auto-generated method stub
		observers.remove(obs);
	}

	
	public ArrayList<SpecialOfferObserver> getObservers() {
		return observers;
	}


	public ArrayList<Meal> getSpecialOffers() {
		// TODO Auto-generated method stub
		return specialoffers;
	}

	@Override
	public void notifyAllSpecialObservers(Meal meal) {
		if (this.changed){
			for (SpecialOfferObserver ob :observers){
				ob.updateNewOffer(meal);
			}
			this.changed = false;
		}
	}

}
