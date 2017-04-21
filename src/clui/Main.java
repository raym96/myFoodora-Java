package clui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import user.model.Manager;
import user.model.MyFoodora;

public class Main {
	public static void main(String[] args) {
		Manager ceo = new Manager("ji","raymond","ceo","123456789");
		ceo.setActivated(true);
		MyFoodora.getInstance().addUser(ceo);
		
		CommandProcessor c = new CommandProcessor();
		
		File file = new File("test.txt");
		Scanner s;
		try {
			s = new Scanner(file);
			String rawInput = "";
			while (s.hasNextLine()){
				rawInput = s.nextLine();
				c.processCommand(rawInput);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
