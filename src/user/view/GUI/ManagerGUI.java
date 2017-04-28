package user.view.GUI;

import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import user.model.Manager;
import user.model.User;
import user.service.ManagerService;
import user.view.GUI.model.BasicFrameWithTabs;

public class ManagerGUI extends BasicFrameWithTabs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Manager manager;
	private ManagerService managerService;

	public ManagerGUI(User user) {
		super("manager");

		manager = (Manager)user;
		managerService = manager.getService();
		this.setTitle("Manager-"+manager.getUsername());
	}

	@Override
	public void placeComponents() {

		
		addTab("users");
		addTab("restaurants");
		addTab("couriers");
		addTab("Performance");
		addTab("Setting");
		
	}
	
}
