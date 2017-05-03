package gui;

import javax.swing.SwingUtilities;

import gui.model.SystemData;

public class TestGUI {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SystemData.initialMyFoodora();
				Login login = new Login();
			}
		});
		
	}
}
