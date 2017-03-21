package system;

import java.util.ArrayList;

import model.customer.Observable;
import model.user.Observer;

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
		this.notifyObservers();
	}
	
	public void removeSpecialOffer(SpecialOffer so){
		specialoffers.remove(so);
		this.changed = true;
		this.notifyObservers();
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

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		if (this.changed){
			for (Observer ob :observers){
				ob.update(this.specialoffers);
			}
			this.changed=false;
		}
	}

	public ArrayList<SpecialOffer> getSpecialoffers() {
		return specialoffers;
	}
	
	
	
}
