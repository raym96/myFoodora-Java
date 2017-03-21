package model.user;

import java.util.ArrayList;

import model.myfoodora.MyFoodora;
import service.ManagerService;
import service.impl.ManagerServiceImpl;
import service.impl.MyFoodoraServiceImpl;

public class Manager extends User {
	
	private String name;
	private String surname;
	private MyFoodora myfoodora;
	
	private ManagerService managerService;
	
	public Manager(String name, String surname, String username, MyFoodoraServiceImpl myfoodora) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.myfoodora=MyFoodora.getInstance();
		managerService = new ManagerServiceImpl(this);
	}

	public ManagerService getManagerService() {
		return managerService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public MyFoodora getMyfoodora() {
		return myfoodora;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
