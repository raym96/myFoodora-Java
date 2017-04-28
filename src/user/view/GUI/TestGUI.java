package user.view.GUI;

import javax.swing.SwingUtilities;

public class TestGUI {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Login login = new Login();
			}
		});
		
	}
}
