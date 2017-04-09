/*
 * 
 */
package user.model;

import java.util.ArrayList;

import system.Message;
import system.Observable;
import system.SpecialOfferBoard;
import user.service.ManagerService;
import user.service.MyFoodoraService;
import user.service.impl.ManagerServiceImpl;
import user.service.impl.MyFoodoraServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class Manager.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Manager extends User{
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;
	
	/** The my foodora service. */
	private MyFoodoraService myFoodoraService;
	
	/** The manager service. */
	private ManagerService managerService;
	
	/**
	 * Instantiates a new manager.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param username the username
	 */
	public Manager(String name, String surname, String username) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.myFoodoraService = new MyFoodoraServiceImpl();
		managerService = new ManagerServiceImpl(this);
	}

	/**
	 * Gets the manager service.
	 *
	 * @return the manager service
	 */
	public ManagerService getManagerService() {
		return managerService;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the myfoodora service.
	 *
	 * @return the myfoodora service
	 */
	public MyFoodoraService getMyfoodoraService() {
		return myFoodoraService;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/* (non-Javadoc)
	 * @see user.model.User#toString()
	 */
	@Override
	public String toString() {
		return  "<Manager> "+username+"; fullname = "+surname+" "+name;
	}

	/* (non-Javadoc)
	 * @see user.model.User#update(java.lang.Object)
	 */
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		super.update(o);
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable)
	 */
	@Override
	public void observe(Observable o) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see user.model.User#observe(system.Observable, java.lang.Object)
	 */
	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		super.observe(obv, o);
	}


}
