package user.view;

import user.model.Restaurant;

/**
 * The Class RestaurantView.
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class RestaurantView implements UserView{
	/** The restaurant. */
	private  Restaurant restaurant;
	
	/**
	 * Instantiates a new restaurant view.
	 *
	 * @param restaurant the restaurant
	 */
	public RestaurantView(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
	
	/* (non-Javadoc)
	 * @see user.view.UserView#showInfo()
	 */
	public void showInfo(){
		String output = "";
		output+="<Restaurant> "+restaurant.getUsername()+"; name = "+restaurant.getName()+"; address="+restaurant.getAddress();
		if (restaurant.getEmail() !=null){
			output+="; email = "+restaurant.getEmail();
		}
		if (restaurant.getPhone() !=null){
			output+="; phone = "+restaurant.getPhone();
		}
		System.out.println(output);
	}
	
	/* (non-Javadoc)
	 * @see user.view.UserView#showHistory()
	 */
	public void showHistory(){
		restaurant.getHistory().display();
	}
	

	/**
	 * Show dishes.
	 */
	public void showDishes() {
		// TODO Auto-generated method stub
		restaurant.getMenu().display();
	}


	/**
	 * Show meals.
	 */
	public void showMeals() {
		// TODO Auto-generated method stub
		System.out.println("\n"+"[Meal menu]");
		restaurant.getMealMenu().display();
	}

	/**
	 * Show discount factors.
	 */
	public void showDiscountFactors(){
		System.out.println("Generic discount factor = "+restaurant.getGeneric_discount_factor());
		System.out.println("Special discount factor = "+restaurant.getSpecial_discount_factor());
	}
	
	/**
	 * Show special offers.
	 */
	public void showSpecialOffers(){
		// TODO Auto-generated method stub
		System.out.println("\n[Special Offers]");
		restaurant.getSpecialmealmenu().display();
	}
	
	/**
	 * Show total income.
	 */
	public void showTotalIncome(){
		System.out.println(restaurant.getName()+"'s total income = " + restaurant.getIncome()+ " euros.");
	}
	

	/**
	 * Show menu.
	 */
	public void showMenu(){
		System.out.println("\n-----["+(restaurant.getName().toUpperCase()+" username <"+restaurant.getUsername()+">]-----"));
		showDishes();
		showMeals();
		showSpecialOffers();
	}

}
