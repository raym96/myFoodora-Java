package gui.model;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class ChildFrame extends BasicFrame{

	public  JPanel controlPanel;
	private BasicButton OkButton;
	private BasicButton CancelButton;
	
	public ChildFrame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		placeComponents();
	}
	
	@Override
	public void prepareGUI(String title) {
		// TODO Auto-generated method stub
		beautyEyeSetting();
		
		this.setTitle(title);
		this.setSize(BasicFrame.width*2/3,BasicFrame.height*2/3);
		this.setLocation(BasicFrame.x+150, BasicFrame.y+150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
