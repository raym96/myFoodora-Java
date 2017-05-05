package gui.model;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyLabel extends JLabel{

	private Font font;
	private Color color;
	
	public MyLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public MyLabel(String text, Font font, Color color) {
		super(text);
		// TODO Auto-generated constructor stub
		this.setFont(font);
		this.setForeground(color);
	}

	public JPanel generateMyLabelPanel(int prefix_gap){
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(Box.createHorizontalStrut(prefix_gap));
		p.add(this);
		
		return p;
	}
}
