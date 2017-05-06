package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import gui.model.BasicButton;
import gui.model.BasicFrame;
import gui.model.BasicFrameSimplePage;
import gui.model.PsdFieldWithLabel;
import gui.model.TextFieldWithLabel;
import user.model.Courier;
import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;
import user.model.Restaurant;
import user.model.User;
import user.service.MyFoodoraService;

public class Login extends BasicFrameSimplePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BasicButton loginUpButton;
	private BasicButton loginInButton;
	private TextFieldWithLabel usernameField;
	private PsdFieldWithLabel passwordField;
	
	private User user;

	public Login() {
		super("Login - MyFoodora");

		placeComponents();
	}
	
	
	@Override
	public void placeComponents() {
		
		headerLabel.setText(null);
		
		statusLabel.setText(null);
		
		JPanel loginPanel = new JPanel();
		controlPanel.add(loginPanel);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		
		final int gap = 10;
		
		loginPanel.add(Box.createVerticalStrut(2*gap));
		
		JLabel head = new JLabel("Welcome to MyFoodora");
		head.setFont(new Font("Arial", Font.ITALIC, 20));
		head.setForeground(Color.BLUE);
		loginPanel.add(head);
		loginPanel.add(Box.createVerticalStrut(2*gap));
		
		// username
		usernameField = new TextFieldWithLabel("username: ", "user does not exist !", loginPanel);
		
		loginPanel.add(Box.createVerticalStrut(gap));
		
		// password
		passwordField = new PsdFieldWithLabel("password: ", "wrong password !", loginPanel);
		loginPanel.add(Box.createVerticalStrut(gap));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.add(Box.createHorizontalStrut(100));
		// button for login up
		loginUpButton = new BasicButton("Login");
		loginUpButton.addActionListener(this);
		btnPanel.add(loginUpButton);
		
		btnPanel.add(Box.createHorizontalStrut(100));
		
		// button for login in 
		loginInButton = new BasicButton("Register");
		loginInButton.addActionListener(this);
		btnPanel.add(loginInButton);
		loginPanel.add(btnPanel);
		
		loginPanel.add(Box.createVerticalStrut(100));
		
		Font font = new Font("Arial", Font.ITALIC, 12);
		Color color = Color.gray;
		JLabel indicate = new JLabel("Tips: accounts for test : ");
		indicate.setFont(font);
		indicate.setForeground(color);
		loginPanel.add(indicate);
		loginPanel.add(Box.createVerticalStrut(10));
		
		JLabel manager = new JLabel("Manager: username = ceo, password = 123456789");
		manager.setFont(font);
		manager.setForeground(color);
		loginPanel.add(manager);
		loginPanel.add(Box.createVerticalStrut(10));
		
		JLabel courier = new JLabel("Courier: username = dtrump, password = password");
		courier.setFont(font);
		courier.setForeground(color);
		loginPanel.add(courier);
		loginPanel.add(Box.createVerticalStrut(10));
		
		JLabel customer = new JLabel("Customer: username = emacron, password = password");
		customer.setFont(font);
		customer.setForeground(color);
		loginPanel.add(customer);
		loginPanel.add(Box.createVerticalStrut(10));
		
		JLabel restaurant = new JLabel("Restaurant: username = french, password = password");
		restaurant.setFont(font);
		restaurant.setForeground(color);
		loginPanel.add(restaurant);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);

		if(e.getSource()==loginUpButton){
			dealLoginUp();
		}
		else if(e.getSource()==loginInButton){
//			System.out.println("login in");
			this.dispose();
			new Register();
		}
	}

	public void dealLoginUp(){
		String username = usernameField.getTextFieldContent();
		String password = passwordField.getPsdFieldContent();
		System.out.println(username + " " + password );

		MyFoodoraService foodoraService = MyFoodora.getInstance().getService();
		
		try{
			user = foodoraService.selectUser(username);	
			usernameField.hideIllegal();
			foodoraService.login(username,password);

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
		catch (LoginErrorException e){
			e.printError();
			passwordField.showIllegal();
		} catch (NameNotFoundException e) {
			e.printError();
			usernameField.showIllegal();
		}
	}
	
}
