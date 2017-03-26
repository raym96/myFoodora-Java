package model.user;

import java.util.ArrayList;

import model.myfoodora.*;

public interface Observer {

	// method for updating the state of the Observer
	public void update(Object o);
	public void observe(Observable obv);
	public void observe(Observable obv, Object o);
}
