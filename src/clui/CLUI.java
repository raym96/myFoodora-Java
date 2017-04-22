package clui;

import java.util.Scanner;

import user.model.MyFoodora;

/**
 * The Class CLUI.
 */
public class CLUI {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommandProcessor c = new CommandProcessor();
		MyFoodora.reset();
		InitialScenario.load("my_foodora.ini");
		
		System.out.println("Welcome to MyFoodora. Please login before executing any command."
				+ " For more information, enter \"help\".");
		System.out.println("To exit system, enter #");
		
		
		Scanner s = new Scanner(System.in);
		String rawInput = s.nextLine();
		while (!(rawInput.equals("#"))){
			System.out.println();
			c.processCommand(rawInput);
			rawInput = s.nextLine();
		}
		System.out.println("Session ended. Goodbye. Press F11 to launch again.");
	}
}

