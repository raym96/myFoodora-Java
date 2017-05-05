package gui.model;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import restaurant.Dish;

/**
 * The Class MyListWithButtons.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class MyListWithButtons extends JPanel{

	/** The sub panel list. */
	private JPanel subPanel_list;
	
	/** The sub panel btns. */
	private JPanel subPanel_btns;
	
	/** The list. */
	private JList<String> list;
	
	/** The List scroll pane. */
	private JScrollPane ListScrollPane;
	
	/** The datamodel. */
	private DefaultListModel<String> datamodel;
	
	/** The btns. */
	private ArrayList<BasicButton> btns;
	
	/** The gap btns. */
	private int gap_btns = 5;
	
	/** The single selection. */
	public final int SINGLE_SELECTION = ListSelectionModel.SINGLE_SELECTION;
	
	/** The multiplel selection. */
	public final int MULTIPLEL_SELECTION = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
	
	/** The Constant item_suffix. */
	public static final String item_suffix = "                   ";
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param btnsStr the btns str
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, String[] btnsStr) {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// label
		JLabel indicate = new JLabel(labeltext);
		this.add(indicate);
		this.add(Box.createVerticalStrut(5));
		
		JPanel subPanel_list_btns = new JPanel();
		this.add(subPanel_list_btns);
		subPanel_list_btns.setLayout(new FlowLayout());
		
		// list
		subPanel_list = new JPanel();
		datamodel = new DefaultListModel<String>();
		for(String str : data){
			datamodel.addElement(str+item_suffix);
		}
		list = new JList<String>(datamodel);
		list.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));
		list.setSelectedIndex(0);
		list.setVisibleRowCount(7);
		ListScrollPane = new JScrollPane(list);
		subPanel_list.add(ListScrollPane);
		subPanel_list_btns.add(subPanel_list);
		
		// buttons
		if(btnsStr.length > 0)
		{
			subPanel_btns = new JPanel();
			subPanel_btns.setLayout(new BoxLayout(subPanel_btns, BoxLayout.Y_AXIS));
			btns = new ArrayList<BasicButton>();
			for(int i=0; i<btnsStr.length; i++){
				btns.add(new BasicButton(btnsStr[i], subPanel_btns));
				subPanel_btns.add(Box.createVerticalStrut(gap_btns));
			}
			subPanel_list_btns.add(subPanel_btns);	
		}
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data) {
		this(labeltext, data, new String[] {});
	}
	
	/**
	 * Refresh.
	 *
	 * @param data the data
	 */
	public void refresh(ArrayList<String> data){
		datamodel = new DefaultListModel<String>();
		for(String str : data){
			datamodel.addElement(str+item_suffix);
		}
		list.setModel(datamodel);
		this.validate();
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public JList<String> getList() {
		return list;
	}

	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param x the x
	 * @param y the y
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, int x, int y){
		this(labeltext, data);
		this.setLocation(x, y);
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param selection_style the selection style
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style){
		this(labeltext, data);
		if(selection_style==this.SINGLE_SELECTION){
			list.setSelectionMode(this.SINGLE_SELECTION);
		}
		if(selection_style==this.MULTIPLEL_SELECTION){
			list.setSelectionMode(this.MULTIPLEL_SELECTION);
		}
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param c the c
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, Container c){
		this(labeltext, data);
		c.add(this);
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param c the c
	 * @param btnsStr the btns str
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, Container c, String[] btnsStr){
		this(labeltext, data, btnsStr);
		c.add(this);
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param selection_style the selection style
	 * @param c the c
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style, Container c){
		this(labeltext, data, selection_style);
		c.add(this);
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param selection_style the selection style
	 * @param x the x
	 * @param y the y
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style, int x, int y){
		this(labeltext, data, selection_style);
		this.setLocation(x, y);
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param c the c
	 * @param x the x
	 * @param y the y
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, Container c, int x, int y){
		this(labeltext, data, c);
		this.setLocation(x, y);
	}
	
	/**
	 * Instantiates a new my list with buttons.
	 *
	 * @param labeltext the labeltext
	 * @param data the data
	 * @param selection_style the selection style
	 * @param c the c
	 * @param x the x
	 * @param y the y
	 */
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style, Container c, int x, int y){
		this(labeltext, data, selection_style, c);
		this.setLocation(x, y);
	}

	/**
	 * Gets the button.
	 *
	 * @param btnStr the btn str
	 * @return the button
	 */
	public BasicButton getButton(String btnStr){
		for(BasicButton btn : btns){
			if(btnStr.equalsIgnoreCase(btn.getText())){
				return btn;
			}
		}
		return null;
	}
	
	/**
	 * Bind action listener.
	 *
	 * @param listenr the listenr
	 */
	public void bindActionListener(ActionListener listenr){
		for(BasicButton b : btns){
			b.addActionListener(listenr);
		}
	}
	
	/**
	 * Gets the selected value.
	 *
	 * @return the selected value
	 */
	public String getSelectedValue(){
		return list.getSelectedValue().trim();
	}
	
	/**
	 * Sets the gap btns.
	 *
	 * @param gap_btns the new gap btns
	 */
	public void setGap_btns(int gap_btns) {
		this.gap_btns = gap_btns;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ArrayList<String> data = new ArrayList<String>();
		data.add("A");
		data.add("B");
		data.add("C");
		data.add("D");
		data.add("E");
		data.add("F");
		data.add("G");
		data.add("H");
		data.add("I");
		
//		BasicFrameSimplePage frame = new BasicFrameSimplePage("test") {
//			
//			@Override
//			public void placeComponents() {
//				// TODO Auto-generated method stub
//				
//			}
//		};
		
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
//			
		MyListWithButtons list = new MyListWithButtons("test below: ", data, new String[] {"a", "b", "c"});
		frame.add(list);
		
		frame.setVisible(true);
		
		list.getButton("a").addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(frame,"a");
			}
		});
		
		
	}
}
