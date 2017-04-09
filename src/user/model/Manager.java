package user.model;

import java.util.ArrayList;

import system.Message;
import system.Observable;
import system.SpecialOfferBoard;
import user.service.ManagerService;
import user.service.MyFoodoraService;
import user.service.impl.ManagerServiceImpl;
import user.service.impl.MyFoodoraServiceImpl;

public class Manager extends User{
	
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

	@Override
	public String toString() {
		return  "<Manager> "+username+"; fullname = "+surname+" "+name;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		super.update(o);
	}

	@Override
	public void observe(Observable o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void observe(Observable obv, Object o) {
		// TODO Auto-generated method stub
		super.observe(obv, o);
	}


}
