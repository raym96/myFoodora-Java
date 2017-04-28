package user.view.GUI;

import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import user.model.User;
import user.view.GUI.model.BasicFrameSimplePage;

public class CourierGUI extends BasicFrameSimplePage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourierGUI(User user) {
		super("courier");

		headerLabel.setText("Welcome courier");
		statusLabel.setText("courier management");
	}

	@Override
	public void placeComponents() {
		// TODO Auto-generated method stub
	}


	
}
