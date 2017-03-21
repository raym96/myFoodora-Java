package model.restaurant;

import java.util.ArrayList;

public class MealMenu {

	private ArrayList<HalfMeal> halfmeals;
	private ArrayList<FullMeal> fullmeals;
	
	public MealMenu() {
		super();
		halfmeals = new ArrayList<HalfMeal>();
		fullmeals = new ArrayList<FullMeal>();
		initMenu();
	}
	
	//for tests
	public void initMenu(){
		this.addMeal(new HalfMeal("Salade-poulet", new Starter("salade","standard",1.5),new MainDish("poulet","standard",1.5)));
		this.addMeal(new HalfMeal("Poulet-glace", new MainDish("poulet","standard",1.5),new Dessert("glace","standard",1.5)));
		this.addMeal(new FullMeal("Salade-Poulet-glace", new Starter("salade","standard",1.5),new MainDish("poulet","standard",1.5),new Dessert("glace","standard",1.5)));
	}
	
	public void addMeal(Meal meal){
		if (meal instanceof HalfMeal){
			halfmeals.add((HalfMeal)meal);}
		if (meal instanceof FullMeal){
			fullmeals.add((FullMeal)meal);}
	}
	
	public void removeMeal(String mealName){
		for(int i=0; i<halfmeals.size(); i++){
			if( halfmeals.get(i).getName() == mealName ){
				halfmeals.remove(i);
			}
		}
		for(int i=0; i<fullmeals.size(); i++){
			if( fullmeals.get(i).getName() == mealName ){
				fullmeals.remove(i);
			}
		}
	}
	
	public ArrayList<HalfMeal> getHalfMealMenu(){
		return halfmeals;
	}
	
	public ArrayList<FullMeal> getFullMealMenu(){
		return fullmeals;
	}

	
}
