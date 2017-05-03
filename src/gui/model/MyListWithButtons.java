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

public class MyListWithButtons extends JPanel{

	private JPanel subPanel_list;
	private JPanel subPanel_btns;
	private JList<String> list;
	private JScrollPane ListScrollPane;
	private DefaultListModel<String> datamodel;
	
	private ArrayList<BasicButton> btns;
	private int gap_btns = 5;
	
	public final int SINGLE_SELECTION = ListSelectionModel.SINGLE_SELECTION;
	public final int MULTIPLEL_SELECTION = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
	
	public static final String item_suffix = "                   ";
	
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
	
	public MyListWithButtons(String labeltext, ArrayList<String> data) {
		this(labeltext, data, new String[] {});
	}
	
	public void refresh(ArrayList<String> data){
		datamodel = new DefaultListModel<String>();
		for(String str : data){
			datamodel.addElement(str+item_suffix);
		}
		list.setModel(datamodel);
		this.validate();
	}

	public JList<String> getList() {
		return list;
	}

	public MyListWithButtons(String labeltext, ArrayList<String> data, int x, int y){
		this(labeltext, data);
		this.setLocation(x, y);
	}
	
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style){
		this(labeltext, data);
		if(selection_style==this.SINGLE_SELECTION){
			list.setSelectionMode(this.SINGLE_SELECTION);
		}
		if(selection_style==this.MULTIPLEL_SELECTION){
			list.setSelectionMode(this.MULTIPLEL_SELECTION);
		}
	}
	
	public MyListWithButtons(String labeltext, ArrayList<String> data, Container c){
		this(labeltext, data);
		c.add(this);
	}
	
	public MyListWithButtons(String labeltext, ArrayList<String> data, Container c, String[] btnsStr){
		this(labeltext, data, btnsStr);
		c.add(this);
	}
	
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style, Container c){
		this(labeltext, data, selection_style);
		c.add(this);
	}
	
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style, int x, int y){
		this(labeltext, data, selection_style);
		this.setLocation(x, y);
	}
	
	public MyListWithButtons(String labeltext, ArrayList<String> data, Container c, int x, int y){
		this(labeltext, data, c);
		this.setLocation(x, y);
	}
	
	public MyListWithButtons(String labeltext, ArrayList<String> data, int selection_style, Container c, int x, int y){
		this(labeltext, data, selection_style, c);
		this.setLocation(x, y);
	}

	public BasicButton getButton(String btnStr){
		for(BasicButton btn : btns){
			if(btnStr.equalsIgnoreCase(btn.getText())){
				return btn;
			}
		}
		return null;
	}
	
	public void bindActionListener(ActionListener listenr){
		for(BasicButton b : btns){
			b.addActionListener(listenr);
		}
	}
	
	public String getSelectedValue(){
		return list.getSelectedValue().trim();
	}
	
	public void setGap_btns(int gap_btns) {
		this.gap_btns = gap_btns;
	}

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
