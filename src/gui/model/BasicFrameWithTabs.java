package gui.model;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The Class BasicFrameWithTabs.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public abstract class BasicFrameWithTabs extends BasicFrame{

	/** The tabbed pane. */
	protected JTabbedPane tabbedPane;
		
	/**
	 * Instantiates a new basic frame with tabs.
	 *
	 * @param title the title
	 */
	public BasicFrameWithTabs(String title) {
		super(title);
	}

	/* (non-Javadoc)
	 * @see gui.model.BasicFrame#prepareGUI(java.lang.String)
	 */
	@Override
	public void prepareGUI(String title) {
		super.prepareGUI(title);
		
		tabbedPane = new JTabbedPane();
		this.setContentPane(tabbedPane);
	}

	/**
	 * Adds the tab.
	 *
	 * @param tabname the tabname
	 */
	public void addTab(String tabname){
		
		JPanel subPanel = new JPanel();
		subPanel.add(new Label("Content"));
		tabbedPane.addTab(tabname, subPanel);
	}

	/* (non-Javadoc)
	 * @see gui.model.BasicFrame#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);
	}

}
