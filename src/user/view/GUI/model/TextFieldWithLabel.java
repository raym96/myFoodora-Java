package user.view.GUI.model;

import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldWithLabel {

	private JLabel label;
	private JTextField textfield;
	
	private final int label_width = 80;
	private final int label_height = 25;
	private final int textfield_width = 150;
	private final int textfield_height = 25;
	
	public TextFieldWithLabel(String labeltext, Container c) {
		
		label = new JLabel(labeltext);
		label.setSize(label_width, label_height);
		c.add(label);
		
		textfield = new JTextField(20);
		textfield.setSize(textfield_width, textfield_height);
		textfield.setLocation(label.getLocation().x+100, label.getLocation().y);
		c.add(textfield);
	}
	
	public TextFieldWithLabel(String labeltext, Container c, int x, int y) {
		
		label = new JLabel(labeltext);
		label.setSize(label_width, label_height);
		label.setLocation(x, y);
		c.add(label);
		
		textfield = new JTextField(20);
		textfield.setSize(textfield_width, textfield_height);
		textfield.setLocation(x+100, y);
		c.add(textfield);
	}

	public String getTextFieldContent(){
		return textfield.getText();
	}
	
	public void setLocation(int x, int y){
		label.setLocation(x, y);
		label.setLocation(x+100, y);
	}
	
	public JLabel getLabel(){
		return this.label;
	}

	public JTextField getTextfield() {
		return textfield;
	}
}
