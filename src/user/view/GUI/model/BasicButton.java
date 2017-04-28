package user.view.GUI.model;

import java.awt.Font;

import javax.swing.JButton;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;


public class BasicButton extends JButton{

	private final int WIDTH = 100;
	private final int HEIGHT = 40;
	
//	private final Font font = new Font("", Font.BOLD, 30);
	
	public static final int NORMAL = 0;
	public static final int GREEN = 1;
	public static final int LIGHTBLUE = 2;
	public static final int BLUE = 3;
	public static final int RED = 4;
	
	
	public BasicButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
		
//		this.setFont(font);
		this.setSize(WIDTH, HEIGHT);
		
		beautyEye();
	}
	
	public BasicButton(String text, int x, int y) {
		super(text);
		// TODO Auto-generated constructor stub
		
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		
		beautyEye();
	}

	protected void beautyEye(){
		
		try{
			
			this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setBEColor(int color){
		try{
			switch (color) {
				case BasicButton.GREEN:
					this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
					break;
				case BasicButton.LIGHTBLUE:
					this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
					break;
				case BasicButton.BLUE:
					this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
					break;
				case BasicButton.RED:
					this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
					break;
				default:
					this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
