/*
 * 
 */
package user.model;

import java.util.ArrayList;

import system.Message;
import system.SpecialOfferBoard;
import user.service.ManagerService;
import user.service.MyFoodoraService;
import user.service.impl.ManagerServiceImpl;
import user.service.impl.MyFoodoraServiceImpl;
import user.view.ManagerView;


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
	
	/** The manager service. */
	private ManagerService managerService;
	
	
	/**
	 * Instantiates a new manager.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param username the username
	 * @param password the password
	 */
	public Manager(String name, String surname, String username, String password) {
		super(username, password);
		this.name = name;
		this.surname = surname;
		managerService = new ManagerServiceImpl(this);
	}

	/**
	 * Gets the manager service.
	 *
	 * @return the manager service
	 */
	public ManagerService getService() {
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
		return  "<Manager> "+username+"; fullname = "+surname+" "+name.toUpperCase();
	}


}
