package gui.model;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MyComboBox extends JPanel{

	private DefaultComboBoxModel<String> model;
	private JComboBox<String> combo;
	public static final String item_suffix = "                   ";
	
	public MyComboBox(String labeltext, ArrayList<String> data) {
		super();
		this.setLayout(new GridLayout(2,1));
		JLabel indicate = new JLabel(labeltext);
		this.add(indicate);
		
		model = new DefaultComboBoxModel<String>();
		
		for(String str : data){
			model.addElement(str+item_suffix);
		}
		
		combo = new JComboBox<String>(model);
		combo.setSelectedIndex(0);
		
		JScrollPane scrollPane = new JScrollPane(combo);
		
		this.add(scrollPane);
		
	}

	public MyComboBox(String labeltext, Container c, ArrayList<String> data) {
		this(labeltext, data);
		c.add(this);	
	}
	
	public String getComboSelected(){
		return ((String) combo.getSelectedItem()).trim();
	}
	
	public void setComboSelected(String text){
		combo.setSelectedItem(text);
	}
	
	public void refresh(ArrayList<String> data){
		model = new DefaultComboBoxModel<String>();
		for(String str : data){
			model.addElement(str+item_suffix);
		}
		combo.setModel(model);
		this.validate();
	}

	public JComboBox<String> getCombo() {
		return combo;
	}
	
	
}
