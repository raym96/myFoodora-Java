package gui.model;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * The Class ChildFrame.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class ChildFrame extends BasicFrame{

	/** The control panel. */
	public  JPanel controlPanel;
	
	/** The Ok button. */
	private BasicButton OkButton;
	
	/** The Cancel button. */
	private BasicButton CancelButton;
	
	/**
	 * Instantiates a new child frame.
	 *
	 * @param title the title
	 */
	public ChildFrame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		placeComponents();
	}
	
	/* (non-Javadoc)
	 * @see gui.model.BasicFrame#prepareGUI(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see gui.model.BasicFrame#placeComponents()
	 */
	@Override
	public void placeComponents() {
		// TODO Auto-generated method stub
		controlPanel = new JPanel();
		this.add(controlPanel);
	}
}
