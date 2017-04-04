package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.restaurant.*;

public class InitMealMenu {
	public static ArrayList<MealMenu> init(String fileName){
		Menu menu = InitDishMenu.init("src/txt files/menu.txt");
		ArrayList<MealMenu> mealmenus = new ArrayList<MealMenu>();

		String mealCategory;
		String mealName;
		String dishName1;
		String dishName2;
		String dishName3;
		
		double gdf = 0;
		double sdf = 0;
		
		File file = new File(fileName);
		
		try{
			Scanner s = new Scanner(file);
			
			s.nextLine();
			gdf = Double.parseDouble(s.nextLine());
			s.nextLine();
			sdf = Double.parseDouble(s.nextLine());
			MealMenu halfmealmenu = new MealMenu(gdf);
			MealMenu fullmealmenu = new MealMenu(gdf);
			MealMenu specialmealmenu = new MealMenu(sdf);
			
			
			
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				mealCategory = s.nextLine();
				mealName = s.nextLine();
				if (mealCategory.equals("Half-meal")){
					dishName1 = s.nextLine();
					dishName2 = s.nextLine();
					halfmealmenu.addMeal(new HalfMeal(mealName,menu,dishName1,dishName2));
				}
				if (mealCategory.equals("Full-meal")){
					dishName1 = s.nextLine();
					dishName2 = s.nextLine();
					dishName3 = s.nextLine();
					fullmealmenu.addMeal(new FullMeal(mealName,menu,dishName1,dishName2,dishName3));
				}
				if (mealCategory.equals("Special-offer")){
					dishName1 = s.nextLine();
					dishName2 = s.nextLine();
					specialmealmenu.addMeal(new HalfMeal(mealName,menu,dishName1,dishName2));
				}
			}
			s.close();
			mealmenus.add(halfmealmenu);
			mealmenus.add(fullmealmenu);
			mealmenus.add(specialmealmenu);

		} catch (IOException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return mealmenus;
}
	public static void main(String arg[]){
		for (int i = 0; i<45; i++){
			System.out.println("[Meal/"+(i+1)+"]");
			if (i%9<4){
				System.out.println("category=Full-meal");
				System.out.println("dish1=");
				System.out.println("dish2=");
				System.out.println("dish3=");
			}
			if (i%9<8 && i%9>=4){
				System.out.println("category=Half-meal");
				System.out.println("dish1=");
				System.out.println("dish2=");
			}
			if (i%9==8){
				System.out.println("category=Special-offer");
				System.out.println("dish1=");
				System.out.println("dish2=");
			}
			Random random = new Random();
			System.out.println("restaurant=restaurant_"+((i/9)+1));
			System.out.println();
		}
	}
}
