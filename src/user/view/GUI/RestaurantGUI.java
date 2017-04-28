package user.view.GUI;

import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import user.model.User;
import user.view.GUI.model.BasicFrameWithTabs;

public class RestaurantGUI extends BasicFrameWithTabs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RestaurantGUI(User user) {
		super("restaurant");

	}

	@Override
	public void placeComponents() {

		
		addTab("Menu");
		addTab("Meal Menu");
		addTab("Special Offer");
		addTab("Performance");
		addTab("Setting");
		
	}

	
}
