package gui.model;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gui.Login;

public class TextFieldWithLabel extends JPanel{

	private JLabel label;
	private JTextField textfield;
	private JLabel illegal;
	
	private int label_width = 150;
	private int label_height = 25;
	private int textfield_width = 120;
	private int textfield_height = 25;
	
//	private final int locate_x = 0;
//	private final int locate_y = 0;
	private final int gap = 20;
	
	public TextFieldWithLabel(String labeltext) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		label = new JLabel(labeltext);
		label.setSize(label_width, label_height);
//		label.setLocation(locate_x, locate_y);
		this.add(label);
		
		textfield = new JTextField(20);
		textfield.setSize(textfield_width, textfield_height);
//		textfield.setLocation(locate_x+label_width+gap, locate_y);
		this.add(textfield);
		
		illegal = new JLabel("");
		illegal.setFont(new Font("Arial", Font.ITALIC, 12));
		illegal.setForeground(Color.red);
		illegal.setSize(label_width, label_height);
//		illegal.setLocation(locate_x+label_width+gap+textfield_width+gap, locate_y);
		illegal.setVisible(false);
		this.add(illegal);
	}
	
//	public void changeLocation(int x, int y){
//		label.setLocation(x, y);
//		textfield.setLocation(x+label_width+gap, y);
//		illegal.setLocation(x+label_width+gap+textfield_width+gap, y);
//	}
	
	public TextFieldWithLabel(String labeltext, String illegalText) {
		this(labeltext);
		this.setIllegal(illegalText);
	}
	
	public TextFieldWithLabel(String labeltext, String illegalText, Container c) {
		this(labeltext, illegalText);
		c.add(this);
	}
	
	public TextFieldWithLabel(String labeltext, String illegalText, Container c, int x, int y) {
		this(labeltext, illegalText, c);
//		changeLocation(x, y);
		this.setLocation(x, y);
	}
	
	public TextFieldWithLabel(String labeltext,  int x, int y) {
		this(labeltext);
//		changeLocation(x, y);
		this.setLocation(x, y);
	}
	
	public TextFieldWithLabel(String labeltext, Container c) {
		this(labeltext);
		c.add(this);
	}
		
	public TextFieldWithLabel(String labeltext, Container c, int x, int y) {
		this(labeltext, c);
//		changeLocation(x, y);
		this.setLocation(x, y);
	}

	public String getTextFieldContent(){
		return textfield.getText();
	}
	
	public void setTextFieldContent(String text){
		textfield.setText(text);
	}
	
	public JLabel getLabel(){
		return this.label;
	}

	public JTextField getTextfield() {
		return textfield;
	}
	
	public void setIllegal(String illegalText){
		illegal.setText("* " + illegalText);
	}
	
	public void showIllegal(){
		illegal.setVisible(true);
	}
	
	public void hideIllegal(){
		illegal.setVisible(false);
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame main = new JFrame();
				main.setBounds(400, 200, 800, 800);
				main.setLayout(null);

				JPanel controlPanel = new JPanel();
				controlPanel.setLocation(100, 100);
				controlPanel.setSize(400, 400);
				main.add(controlPanel);
//				controlPanel.setLayout(new GridLayout(4, 1));
				
				TextFieldWithLabel test1 = new TextFieldWithLabel("test1: ", "illegal", controlPanel);
//				new TextFieldWithLabel("test2: ", controlPanel, 100, 100);
//				new TextFieldWithLabel("test3: ", controlPanel, 100, 200);
				
				BasicButton btn = new BasicButton("illegal", controlPanel, 100, 600);
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						test1.showIllegal();
					}
				});
				
				main.setVisible(true);
			}
		});
		
		
	}
}
