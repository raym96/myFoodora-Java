package model.customer;

import java.util.ArrayList;

import model.myfoodora.SpecialOffer;
import model.myfoodora.ConcreteSpecialOfferBoard;

public interface SpecialOfferObserver {
	public void addSpecialOffer(SpecialOffer specialoffer); //add a new special offer
	public void updateSpecialOffer(ArrayList<SpecialOffer> specialoffers); //update the special offers by a list
}
