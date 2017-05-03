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

public class MyRadioButton extends JPanel{

	private ArrayList<JRadioButton> radbtns;
	private ButtonGroup group;
	
	public static final int gap = 10;
	
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

	public MyRadioButton(String labeltext, Container c, String[] radbtnsStr) {
		this(labeltext, radbtnsStr);
		c.add(this);	
	}

	public ButtonGroup getGroup() {
		return group;
	}
	
	public ArrayList<JRadioButton> getRadbtns() {
		return radbtns;
	}

	public JRadioButton getButton(String text){
		for(JRadioButton r : radbtns){
			if(text.equals(r.getText())){
				return r;
			}
		}
		return null;
	}
	
	public void bindItemListener(ItemListener listener){
		
		for(JRadioButton rbtn : radbtns){
			rbtn.addItemListener(listener);
		}
	}
}
