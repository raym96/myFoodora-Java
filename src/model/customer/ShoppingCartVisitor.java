package model.customer;

import model.restaurant.*;

public interface ShoppingCartVisitor {
	double visit(SpecialMealOrder mealOrder);
	double visit(StandardMealOrder order);
	double visit(AlaCarteOrder dish);
}
