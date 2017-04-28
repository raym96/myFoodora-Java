package user.view.GUI.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.sun.glass.ui.MenuBar;

import user.view.GUI.Login;

public abstract class BasicFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JMenuBar menuBar;
	
	public BasicFrame(String title){

		prepareGUI(title);
	}
	
	public void beautyEyeSetting(){
		
		try{
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void prepareGUI(String title){
		
		beautyEyeSetting();
		
		this.setTitle(title);
		this.setSize(1000,1000);
		this.setLocation(600,00);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    menuBar = new JMenuBar();
	    this.setJMenuBar(menuBar);
	    
	    editMenuBar();
	    bindMenuItemActionListeners();
	     
	    this.setVisible(true);
	}
	
	public void editMenuBar(){
		JMenu menu_ope = new JMenu("Operations");
		JMenu menu_other = new JMenu("Other");
		menuBar.add(menu_ope);
		menuBar.add(menu_other);
	    
		menu_ope.add(new JMenuItem("Change login"));
		menu_ope.add(new JMenuItem("Login out"));
		menu_ope.add(new JMenuItem("Exit"));
	    
		menu_other.add(new JMenuItem("Help"));
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

			}else if(eventSource==menuBar.getMenu(menuBar.getMenuCount()-1).getItem(1)){
			}
		}
	}

	public abstract void placeComponents();
}
