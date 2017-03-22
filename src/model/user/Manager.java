package model.user;

import java.util.ArrayList;

import service.ManagerService;
import service.MyFoodoraService;
import service.impl.ManagerServiceImpl;
import service.impl.MyFoodoraServiceImpl;

public class Manager extends User {
	
	private String name;
	private String surname;
	private MyFoodoraService myFoodoraService;
	
	private ManagerService managerService;
	
	public Manager(String name, String surname, String username) {
		super(username);
		this.name = name;
		this.surname = surname;
		this.myFoodoraService = new MyFoodoraServiceImpl();
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

	public MyFoodoraService getMyfoodoraService() {
		return myFoodoraService;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
