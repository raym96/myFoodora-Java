package user.view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clui.Command;
import clui.InitialScenario;
import exceptions.LoginErrorException;
import exceptions.NameNotFoundException;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.MyFoodoraService;
import user.view.GUI.model.BasicButton;
import user.view.GUI.model.BasicFrameSimplePage;
import user.view.GUI.model.TextFieldWithLabel;

public class Login extends BasicFrameSimplePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BasicButton loginUpButton;
	private BasicButton loginInButton;
	private TextFieldWithLabel usernameField;
	private JPasswordField passwordField;
	
	private User user;

	public Login() {
		super("login");
		
		initialMyFoodora();
		
		headerLabel.setText("Welcome to MyFoodora!");
		statusLabel.setText("login in or login up");

	}
	
	public void initialMyFoodora(){
		MyFoodora.reset();
		InitialScenario.load("my_foodora.ini");
	}
	
	@Override
	public void placeComponents() {
		// TODO Auto-generated method stub
		
		controlPanel.setLayout(null);
		
		int x=370, y=300;
	
		// username
//		JLabel userLabel = new JLabel("User:");
//		userLabel.setBounds(200, 100, 80, 25);
//		controlPanel.add(userLabel);
//		
//		userText = new JTextField(20);
//		userText.setBounds(300,100,165,25);
//		controlPanel.add(userText);
		usernameField = new TextFieldWithLabel("username", controlPanel, x, y);
		
		// password
		JLabel passwordLabel = new JLabel("password:");
		passwordLabel.setBounds(x, y+50, 80, 25);
		controlPanel.add(passwordLabel);
		
		passwordField = new JPasswordField(20);
		passwordField.setBounds(x+100, y+50,150,25);
		controlPanel.add(passwordField);
		
		// button for login up
		loginUpButton = new BasicButton("login up", x, y+100);
//		loginUpButton.setBEColor(BasicButton.LIGHTBLUE);
		loginUpButton.addActionListener(this);
		controlPanel.add(loginUpButton);
		
		// button for login in 
		loginInButton = new BasicButton("login in", x+150, y+100);
//		loginInButton.setBEColor(BasicButton.LIGHTBLUE);
		loginInButton.addActionListener(this);
		controlPanel.add(loginInButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);

		if(e.getSource()==loginUpButton){
//			System.out.println("login up");
			dealLoginUp();
		}
		else if(e.getSource()==loginInButton){
//			System.out.println("login in");
			this.dispose();
			new LoginIn();
		}
	}

	public void dealLoginUp(){
		String username = usernameField.getTextFieldContent();
		String password = String.valueOf(passwordField.getPassword());
		System.out.println(username + " " + password );

		MyFoodoraService foodoraService = MyFoodora.getInstance().getService();
		
		try{
			user = foodoraService.selectUser(username);
			foodoraService.login(username,password);
			System.out.println("Welcome on MyFoodora, user "+username+". Please enter a command.");
		}
		catch (LoginErrorException e){
			e.printError();
		} catch (NameNotFoundException e) {
			e.printError();
		}
		
		if(user != null){
			this.dispose();
			if(user instanceof Manager){
				new ManagerGUI(user);
			}else if(user instanceof Restaurant){
				new RestaurantGUI(user);
			}else if(user instanceof Courier){
				new CourierGUI(user);
			}else if(user instanceof Customer){
				new CustomerGUI(user);
			}
		}
	}
	
	
	
}
