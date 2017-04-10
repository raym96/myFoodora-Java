/*
 * 
 */
package restaurant;

import system.ShoppingCartVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Interface Item.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
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
