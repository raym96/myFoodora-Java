package restaurant;

import system.ShoppingCartVisitor;

/**
 * The Interface Item.
 * 
 * @author He Xiaoan
 * @authro Ji Raymond
 */
public interface  Item {
	
	/**
	 * Accept.
	 *
	 * @param visitor the visitor
	 * @return the double
	 */
	public double accept(ShoppingCartVisitor visitor);
}
