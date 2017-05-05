package gui.model;

import java.awt.Container;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * The Class MyRadioButton.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MyRadioButton extends JPanel{

	/** The radbtns. */
	private ArrayList<JRadioButton> radbtns;
	
	/** The group. */
	private ButtonGroup group;
	
	/** The Constant gap. */
	public static final int gap = 10;
	
	/**
	 * Instantiates a new my radio button.
	 *
	 * @param labeltext the labeltext
	 * @param radbtnsStr the radbtns str
	 */
	public MyRadioButton(String labeltext, String[] radbtnsStr) {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel indicate = new JLabel(labeltext);
		this.add(indicate);
		
		this.add(Box.createVerticalStrut(gap));
		
		radbtns = new ArrayList<JRadioButton>();
		group = new ButtonGroup();
		JPanel btnPanel = new JPanel();
		this.add(btnPanel);
		
		for(int i=0; i<radbtnsStr.length; i++){
			JRadioButton radbtn = new JRadioButton(radbtnsStr[i]);
			radbtns.add(radbtn);
			btnPanel.add(radbtn);
			group.add(radbtn);
		}
		
	}

	/**
	 * Instantiates a new my radio button.
	 *
	 * @param labeltext the labeltext
	 * @param c the c
	 * @param radbtnsStr the radbtns str
	 */
	public MyRadioButton(String labeltext, Container c, String[] radbtnsStr) {
		this(labeltext, radbtnsStr);
		c.add(this);	
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public ButtonGroup getGroup() {
		return group;
	}
	
	/**
	 * Gets the radbtns.
	 *
	 * @return the radbtns
	 */
	public ArrayList<JRadioButton> getRadbtns() {
		return radbtns;
	}

	/**
	 * Gets the button.
	 *
	 * @param text the text
	 * @return the button
	 */
	public JRadioButton getButton(String text){
		for(JRadioButton r : radbtns){
			if(text.equals(r.getText())){
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Bind item listener.
	 *
	 * @param listener the listener
	 */
	public void bindItemListener(ItemListener listener){
		
		for(JRadioButton rbtn : radbtns){
			rbtn.addItemListener(listener);
		}
	}
}
