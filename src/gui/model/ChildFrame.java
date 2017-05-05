package gui.model;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.sun.net.httpserver.Authenticator.Success;

public class ChildFrame extends BasicFrame{

	public  JPanel controlPanel;
	private BasicButton OkButton;
	private BasicButton CancelButton;
	
	private Container superFrame;
	
	public ChildFrame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		
		placeComponents();
	}
	
	public ChildFrame(String title, Container superFrame) {
		this(title);
		this.superFrame = superFrame;
		
		this.superFrame.setEnabled(false);
		this.setAlwaysOnTop(true);
	}
	
	public void close(){
		this.dispose();
		this.superFrame.setEnabled(true);
		this.superFrame.setVisible(true);
	}
	
	
	@Override
	public void prepareGUI(String title) {
		// TODO Auto-generated method stub
		beautyEyeSetting();
		
		this.setTitle(title);
		this.setSize(BasicFrame.width*2/3,BasicFrame.height*2/3);
		this.setLocation(BasicFrame.x+150, BasicFrame.y+150);
		this.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            ChildFrame.this.close();
 
	         }        
	      });

		this.setResizable(false);
	    this.setVisible(true);
	}

	@Override
	public void placeComponents() {
		// TODO Auto-generated method stub
		controlPanel = new JPanel();
		this.add(controlPanel);
	}
	
}
