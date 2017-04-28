package user.view.GUI.model;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.java2d.pipe.SpanClipRenderer;

public abstract class BasicFrameSimplePage extends BasicFrame implements ActionListener{
	
	protected JLabel headerLabel;
	protected JLabel statusLabel;
	protected JPanel controlPanel;

	public BasicFrameSimplePage(String title) {
		super(title);
	}

	@Override
	public void prepareGUI(String title){
		
		super.prepareGUI(title);
		
		setBorderLayout();
	
	    placeComponents(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
	}
	
	private void setBorderLayout(){
		this.setLayout(new BorderLayout());
		
		headerLabel = new JLabel(""); 
		this.add(headerLabel, BorderLayout.PAGE_START); 
	    
	    controlPanel = new JPanel(); 
	    this.add(controlPanel, BorderLayout.CENTER);
 
	    statusLabel = new JLabel("");
		this.add(statusLabel, BorderLayout.PAGE_END);
	}
	
	private void setGridBagLayout(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		headerLabel = new JLabel(""); 
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(headerLabel, c); 
	    
	    controlPanel = new JPanel(); 
	    c.fill = GridBagConstraints.VERTICAL;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 4;
	    c.weighty = 1;
	    this.add(controlPanel, c);
 
	    statusLabel = new JLabel("");
	    c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 2;
		this.add(statusLabel, c);
	}

	

}
