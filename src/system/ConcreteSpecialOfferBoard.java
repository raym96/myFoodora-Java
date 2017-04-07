package system;

import java.util.ArrayList;

import user.Customer;
import user.User;

public class ConcreteSpecialOfferBoard implements SpecialOfferBoard{
	private ArrayList<SpecialOfferObserver> observers;
	private ArrayList<SpecialOffer> specialoffers;
	private boolean changed;
	
	public ConcreteSpecialOfferBoard(){
		observers = new ArrayList<SpecialOfferObserver>();
		specialoffers = new ArrayList<SpecialOffer>();
		this.changed = false;
	}
	
	public void addSpecialOffer(SpecialOffer so){
		specialoffers.add(so);
		this.changed = true;
		this.notifyAllObservers();
	}
	
	public void removeSpecialOffer(SpecialOffer so){
		specialoffers.remove(so);
		this.changed = true;
		this.notifyAllObservers();
	}

	@Override
	public void register(SpecialOfferObserver obs) {
		// TODO Auto-generated method stub
		observers.add(obs);
	}

	@Override
	public void unregister(SpecialOfferObserver obs) {
		// TODO Auto-generated method stub
		observers.remove(obs);
	}

	public ArrayList<SpecialOffer> getSpecialoffers() {
		return specialoffers;
	}

	@Override
	public void notifyObserver(Observer obs) {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public void notifyAllObservers(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver(Observer obs, Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers(ArrayList<Observer> observers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers(ArrayList<User> observers, Object o) {
		// TODO Auto-generated method stub
		
	}

	
	
}
