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

public abstract class BasicFrameWithTabs extends BasicFrame{

	protected JTabbedPane tabbedPane;
		
	public BasicFrameWithTabs(String title) {
		super(title);
	}

	@Override
	public void prepareGUI(String title) {
		super.prepareGUI(title);
		
		tabbedPane = new JTabbedPane();
		this.setContentPane(tabbedPane);
	}

	public void addTab(String tabname){
		
		JPanel subPanel = new JPanel();
		subPanel.add(new Label("Content"));
		tabbedPane.addTab(tabname, subPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);
	}

}
