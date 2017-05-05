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

/**
 * The Class MyComboBox.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MyComboBox extends JPanel{

	/** The model. */
	private DefaultComboBoxModel<String> model;
	
	/** The combo. */
	private JComboBox<String> combo;
	
	/** The Constant item_suffix. */
	public static final String item_suffix = "                   ";
	
	/**
	 * Instantiates a new my combo box.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 */
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

	/**
	 * Instantiates a new my combo box.
	 *
	 * @param labeltext the labeltext
	 * @param c the c
	 * @param data the data
	 */
	public MyComboBox(String labeltext, Container c, ArrayList<String> data) {
		this(labeltext, data);
		c.add(this);	
	}
	
	/**
	 * Gets the combo selected.
	 *
	 * @return the combo selected
	 */
	public String getComboSelected(){
		return ((String) combo.getSelectedItem()).trim();
	}
	
	/**
	 * Sets the combo selected.
	 *
	 * @param text the new combo selected
	 */
	public void setComboSelected(String text){
		combo.setSelectedItem(text);
	}
	
	/**
	 * Refresh.
	 *
	 * @param data the data
	 */
	public void refresh(ArrayList<String> data){
		model = new DefaultComboBoxModel<String>();
		for(String str : data){
			model.addElement(str+item_suffix);
		}
		combo.setModel(model);
		this.validate();
	}

	/**
	 * Gets the combo.
	 *
	 * @return the combo
	 */
	public JComboBox<String> getCombo() {
		return combo;
	}
	
	
}
