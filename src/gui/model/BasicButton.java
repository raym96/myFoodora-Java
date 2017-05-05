package gui.model;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;


/**
 * The Class BasicButton.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class BasicButton extends JButton{

	/** The width. */
	private final int WIDTH = 100;
	
	/** The height. */
	private final int HEIGHT = 40;
	
//	private final Font font = new Font("", Font.BOLD, 30);
	
	/** The Constant NORMAL. */
public static final int NORMAL = 0;
	
	/** The Constant GREEN. */
	public static final int GREEN = 1;
	
	/** The Constant LIGHTBLUE. */
	public static final int LIGHTBLUE = 2;
	
	/** The Constant BLUE. */
	public static final int BLUE = 3;
	
	/** The Constant RED. */
	public static final int RED = 4;
	
	
	/**
	 * Instantiates a new basic button.
	 *
	 * @param text the text
	 */
	public BasicButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
		
//		this.setFont(font);
		this.setSize(WIDTH, HEIGHT);
		beautyEye();
	}
	
	/**
	 * Instantiates a new basic button.
	 *
	 * @param text the text
	 * @param c the c
	 */
	public BasicButton(String text, Container c) {
		this(text);
		c.add(this);
	}
	
	/**
	 * Instantiates a new basic button.
	 *
	 * @param text the text
	 * @param x the x
	 * @param y the y
	 */
	public BasicButton(String text, int x, int y) {
		this(text);
		this.setLocation(x, y);

	}
	
	/**
	 * Instantiates a new basic button.
	 *
	 * @param text the text
	 * @param c the c
	 * @param x the x
	 * @param y the y
	 */
	public BasicButton(String text, Container c, int x, int y) {
		this(text, x, y);
		c.add(this);
	}

	/**
	 * Beauty eye.
	 */
	protected void beautyEye(){
		
		try{
			
			this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Sets the BE color.
	 *
	 * @param color the new BE color
	 */
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
