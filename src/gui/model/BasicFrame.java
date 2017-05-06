package gui.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.sun.glass.ui.MenuBar;

import gui.Login;

public abstract class BasicFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int width = screenSize.width * 3/4;
	public static final int height = screenSize.height * 5/6;
	public static final int x = screenSize.width/2 - width/2;
	public static final int y = screenSize.height/2 - height/2;
	
	protected JMenuBar menuBar;
	
	public BasicFrame(String title){
		super();
		prepareGUI(title);
	}
	
	public abstract void placeComponents();

	public void prepareGUI(String title){
		
		beautyEyeSetting();
		
		this.setTitle(title);
		this.setSize(width,height);
		this.setLocation(x,y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    menuBar = new JMenuBar();
	    this.setJMenuBar(menuBar);
	    
	    editMenuBar();
	    bindMenuItemActionListeners();
	     
	    this.setVisible(true);
	    this.setResizable(false);
//	    this.setMinimumSize(new Dimension(width, height));
	}
	
	public void beautyEyeSetting(){
		
		try{
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			UIManager.put("RootPane.setupButtonVisible", false);
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void editMenuBar(){
		JMenu menu_ope = new JMenu("Operations");
		JMenu menu_other = new JMenu("Other");
		menuBar.add(menu_ope);
		menuBar.add(menu_other);
	    
		menu_ope.add(new JMenuItem("Logout"));
		menu_ope.add(new JMenuItem("Exit"));
	    
		menu_other.add(new JMenuItem("About"));		
	}
	
	public final void bindMenuItemActionListeners(){
		for(int i=0; i<menuBar.getMenuCount(); i++){
			JMenu menu = menuBar.getMenu(i);
			for(int j=0; j<menu.getItemCount(); j++){
				menu.getItem(j).addActionListener(this);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if(eventSource instanceof JMenuItem){
			if(eventSource==menuBar.getMenu(0).getItem(0)){
				this.dispose();
				new Login();
			}else if(eventSource==menuBar.getMenu(0).getItem(1)){
				this.dispose();
				new Login();
			}else if(eventSource==menuBar.getMenu(0).getItem(2)){
				this.dispose();
				System.exit(0);
			}else if(eventSource==menuBar.getMenu(menuBar.getMenuCount()-1).getItem(0)){
				ChildFrame helpFrame = new ChildFrame("About", this);
				Font font = new Font("Arial", Font.ITALIC, 16);
				
				JPanel panel_content = new JPanel();
				helpFrame.controlPanel.add(panel_content);
				panel_content.setLayout(new BoxLayout(panel_content, BoxLayout.Y_AXIS));
				panel_content.add(Box.createVerticalStrut(100));
				
				JPanel subPanel = new JPanel();
				panel_content.add(subPanel);
				panel_content.add(Box.createVerticalStrut(80));
				subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
				
				JLabel title = new JLabel("Declaration");
				title.setFont(new Font("Arial", Font.BOLD, 25));
				subPanel.add(Box.createHorizontalStrut(300));
				subPanel.add(title);
				
				JLabel author = new JLabel("Author : He Xiaoan, Ji Raymond");
				author.setFont(font);
				author.setForeground(Color.GRAY);
				panel_content.add(author);
				panel_content.add(Box.createVerticalStrut(30));
				
				JLabel date = new JLabel("Date : Fri May 05 17:01:46 CEST 2017");
				date.setFont(font);
				date.setForeground(Color.GRAY);
				panel_content.add(date);
				panel_content.add(Box.createVerticalStrut(30));
				
				JLabel version = new JLabel("version : 1.0");
				version.setFont(font);
				version.setForeground(Color.GRAY);
				panel_content.add(version);
				panel_content.add(Box.createVerticalStrut(30));
				
				JLabel declaration = new JLabel("This booking system MyFoodora is made for the project of IS1220HB");
				declaration.setFont(font);
				declaration.setForeground(Color.GRAY);
				panel_content.add(declaration);
				
				System.out.println(new Date());
						
			}
		}
	}

	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				BasicFrame frame = new BasicFrame("test") {
					
					@Override
					public void placeComponents() {
						// TODO Auto-generated method stub
						TextFieldWithLabel test = new TextFieldWithLabel("test");
//						test.setEnabled(false);
						this.add(test);
					}
				};
				frame.setVisible(true);
			}
		});
	}
}
