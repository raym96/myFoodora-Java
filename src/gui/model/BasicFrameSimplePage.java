package gui.model;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sun.java2d.pipe.SpanClipRenderer;

public abstract class BasicFrameSimplePage extends BasicFrame implements ActionListener{
	
	protected JLabel headerLabel;
	protected JLabel statusLabel;
	protected JPanel controlPanel;
	
//	public static final int Border_layout = 1;
//	public static final int Gird_layout = 2;
//	public static final int GridBag_layout = 3;
	

	public BasicFrameSimplePage(String title) {
		super(title);
		this.setBoxLayout();
	}
	
	public void setBoxLayout(){
		JPanel mainPanel = new JPanel();
		this.add(mainPanel);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		headerLabel = new JLabel("header");
		headerLabel.setLocation(50, 0);
		mainPanel.add(headerLabel);
		
		mainPanel.add(Box.createVerticalStrut(30));
		
		controlPanel = new JPanel();
		mainPanel.add(controlPanel);
		
		mainPanel.add(Box.createVerticalStrut(30));
		
		statusLabel = new JLabel("status");
		mainPanel.add(statusLabel);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				BasicFrameSimplePage frame = new BasicFrameSimplePage("test") {
					
					@Override
					public void placeComponents() {
						// TODO Auto-generated method stub
						
					}
				};
				frame.setVisible(true);
			}
		});
	}
	
	// disposed
//	public BasicFrameSimplePage(String title, int layout) {
//		super(title);
//		
//		switch (layout) {
//			case Border_layout:
//				setBorderLayout();
//				break;
//			case Gird_layout:
//				setGridLayout();
//				break;
//			case GridBag_layout:
//				setGridBagLayout();
//				break;
//			default:
//				setBorderLayout();
//				break;
//			}
//	}

	
//	private void setBorderLayout(){
//		this.setLayout(new BorderLayout());
//		
//		headerLabel = new JLabel(""); 
//		this.add(headerLabel, BorderLayout.PAGE_START); 
//	    
//	    controlPanel = new JPanel(); 
//	    this.add(controlPanel, BorderLayout.CENTER);
// 
//	    statusLabel = new JLabel("");
//		this.add(statusLabel, BorderLayout.PAGE_END);
//	}
	
//	private void setGridLayout(){
//		this.setLayout(new GridLayout(3,1));
//		
//		headerLabel = new JLabel("",JLabel.CENTER); 
//		this.add(headerLabel); 
//	    
//	    controlPanel = new JPanel(); 
//	    this.add(controlPanel);
// 
//	    statusLabel = new JLabel("",JLabel.CENTER);
//		this.add(statusLabel);
//	}
	
//	private void setGridBagLayout(){
//		this.setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		
//		headerLabel = new JLabel(""); 
//		c.fill = GridBagConstraints.VERTICAL;
//		c.gridx = 0;
//		c.gridy = 0;
//		this.add(headerLabel, c); 
//	    
//	    controlPanel = new JPanel(); 
//	    c.fill = GridBagConstraints.VERTICAL;
//	    c.gridx = 0;
//	    c.gridy = 1;
//	    c.gridwidth = 4;
//	    c.weighty = 1;
//	    this.add(controlPanel, c);
// 
//	    statusLabel = new JLabel("");
//	    c.fill = GridBagConstraints.VERTICAL;
//		c.gridx = 0;
//		c.gridy = 2;
//		this.add(statusLabel, c);
//	}
}
