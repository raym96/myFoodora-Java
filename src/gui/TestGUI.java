package gui;

import javax.swing.SwingUtilities;

import gui.model.SystemData;

/**
 * The Class TestGUI.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class TestGUI {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
