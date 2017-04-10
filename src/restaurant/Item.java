package restaurant;

import system.ShoppingCartVisitor;

public interface Item {
	public  double accept(ShoppingCartVisitor visitor);
}
