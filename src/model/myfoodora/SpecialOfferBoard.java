package model.myfoodora;

import java.util.ArrayList;

import model.customer.Observable;
import model.user.Customer;
import model.user.Observer;
import model.user.User;

public class SpecialOfferBoard implements Observable{
	private ArrayList<Observer> observers;
	private ArrayList<SpecialOffer> specialoffers;
	private boolean changed;
	
	public SpecialOfferBoard(){
		observers = new ArrayList<Observer>();
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
	public void register(Observer obs) {
		// TODO Auto-generated method stub
		observers.add(obs);
	}

	@Override
	public void unregister(Observer obs) {
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
			for (Observer ob :observers){
				ob.update(this.specialoffers);
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
