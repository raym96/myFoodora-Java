package clui;

import java.io.*;
import java.util.Scanner;

import user.model.Customer;
import user.model.Manager;
import user.model.MyFoodora;

/**
 * The Class InitialScenario.
 */
public class InitialScenario {
	
	
	/**
	 * Load.
	 *
	 * @param filename the filename
	 */
	public static void load(String filename) {
		System.out.println("\n-----------------------------------------------------------------------------------");
		System.out.println("Loading initial setup <"+filename+">");
		
		MyFoodora.reset();
		
		PrintStream originalStream = System.out;
		
		PrintStream invisibleStream    = new PrintStream(new OutputStream(){
		    public void write(int b) {
		        //does nothing, hide all system outputs
		    }
		});

		System.setOut(invisibleStream);
		
		Manager ceo = new Manager("ji","raymond","ceo","123456789");
		ceo.setActivated(true);
		MyFoodora.getInstance().addUser(ceo);
		
		CommandProcessor c = new CommandProcessor();
		
		File file = new File(filename);
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
		MyFoodora myfoodora = MyFoodora.getInstance();

		System.setOut(originalStream);
		
//		myfoodora.getView().showUsers();
//		myfoodora.getView().showRestaurantMenus();
//		myfoodora.getView().showHistory();
		System.out.println("The initial file <"+filename+"> successfully loaded into the system.");
		System.out.println("-----------------------------------------------------------------------------------\n");

	}
}
