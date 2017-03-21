package model.user;

import java.util.ArrayList;

import model.myfoodora.*;

public interface Observer {

	// method for updating the state of the Observer
	public void update(ArrayList<SpecialOffer> specialoffers);
	public void observe(SpecialOfferBoard specialofferboard);
}
