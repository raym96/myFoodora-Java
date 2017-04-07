package system;

import java.util.ArrayList;

public interface SpecialOfferObserver {
	public void addSpecialOffer(SpecialOffer specialoffer); //add a new special offer
	public void updateSpecialOffer(ArrayList<SpecialOffer> specialoffers); //update the special offers by a list
}
