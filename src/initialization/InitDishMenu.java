package initialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.restaurant.*;

public class InitDishMenu {
	
	public static Menu init(String fileName){
		Menu menu = new Menu();
		
		String dishCategory; //Starter, main-dish or dessert
		String dishName;
		String dishType;
		double price;
		
		File file = new File(fileName);
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()&&s.nextLine().equals("----------")){
				dishCategory = s.nextLine();
				dishName = s.nextLine();
				dishType = s.nextLine();
				price = Double.parseDouble(s.nextLine());
				if (dishCategory.equals("Starter")){
					menu.addDish(new Starter(dishName,dishType,price));
				}
				if (dishCategory.equals("Main-dish")){
					menu.addDish(new MainDish(dishName,dishType,price));
				}
				if (dishCategory.equals("Dessert")){
					menu.addDish(new Dessert(dishName,dishType,price));
				}
			}
			s.close();
		} catch (IOException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return menu;
	}
	
	public static void main(String arg[]){
		Menu menu = init("src/txt files/menu.txt");
		for (int i = 0; i<30; i++){
			System.out.println("[Dish/"+(i+1)+"]");
			if (i%6<2){
				System.out.println("category=Starter");
			}
			if (i%6<4 && i%6>=2){
				System.out.println("category=Main-dish");
			}
			if (i%6>=4){
				System.out.println("category=Dessert");
			}
			System.out.println("name=");
			System.out.println("type=");
			Random random = new Random();
			System.out.println("price="+random.nextInt(10)+"."+random.nextInt(10));
			System.out.println("restaurant=restaurant_"+((i/6)+1));
			System.out.println();
		}
	}
}
