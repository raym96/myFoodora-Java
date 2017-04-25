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

public class Login extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton loginUpButton;
	private JButton loginInButton;
	
	public Login(){
		this.setTitle("Login");
		this.setSize(800,600);
		this.setLocation(600,00);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		this.add(panel1);
		
		placeComponents(panel1);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		
		Login login = new Login();
		
	}
	
	private void placeComponents(JPanel panel) {
		
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("User:");
		
		// username
		userLabel.setBounds(200, 100, 80, 25);
		panel.add(userLabel);
		
		JTextField userText = new JTextField(20);
		userText.setBounds(300,100,165,25);
		panel.add(userText);
		
		// password
		JLabel passwordLabel = new JLabel("password:");
		passwordLabel.setBounds(200, 150, 80, 25);
		panel.add(passwordLabel);
		
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(300,150,165,25);
		panel.add(passwordText);
		
		// button for login up
		loginUpButton = new JButton("login up");
		loginUpButton.setBounds(300, 200, 80, 25);
		loginUpButton.addActionListener(this);
		panel.add(loginUpButton);
		
		// button for login in 
		loginInButton = new JButton("login in ");
		loginInButton.setBounds(400, 200, 80, 25);
		loginInButton.addActionListener(this);
		panel.add(loginInButton);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==loginUpButton){
			System.out.println("login up");
		}
		else if(e.getSource()==loginInButton){
			System.out.println("login in");
			this.dispose();
			new LoginIn();
		}
	}

	
}
