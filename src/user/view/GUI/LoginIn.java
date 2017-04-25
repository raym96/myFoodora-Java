package user.view.GUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginIn extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton OkButton;
	private JButton CancelButton;
	
	public LoginIn(){
		this.setTitle("Login In");
		this.setSize(800,600);
		this.setLocation(600,00);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		this.add(panel1);
		
		placeComponents(panel1);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		
		LoginIn login = new LoginIn();
		
	}
	
	private void placeComponents(JPanel panel) {
		
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("set username:");
		
		// username
		userLabel.setBounds(200, 100, 200, 25);
		panel.add(userLabel);
		
		JTextField userText = new JTextField(20);
		userText.setBounds(400,100,165,25);
		panel.add(userText);
		
		// password
		JLabel passwordLabel = new JLabel("set password:");
		passwordLabel.setBounds(200, 150, 200, 25);
		panel.add(passwordLabel);
		
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(400,150,165,25);
		panel.add(passwordText);
		
		// make sure password
		JLabel makesure_passwordLabel = new JLabel("make sure password:");
		makesure_passwordLabel.setBounds(200, 200, 200, 25);
		panel.add(makesure_passwordLabel);
		
		JPasswordField makesure_passwordText = new JPasswordField(20);
		makesure_passwordText.setBounds(400,200,165,25);
		panel.add(makesure_passwordText);
		
		// button for login up
		OkButton = new JButton("OK");
		OkButton.setBounds(300, 300, 80, 25);
		OkButton.addActionListener(this);
		panel.add(OkButton);
		
		// button for login in 
		CancelButton = new JButton("cancel");
		CancelButton.setBounds(400, 300, 80, 25);
		CancelButton.addActionListener(this);
		panel.add(CancelButton);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==OkButton){
			System.out.println("OK");
		}
		else if(e.getSource()==CancelButton){
			System.out.println("Cancel");
		}
	}

	
}
