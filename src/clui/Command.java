package clui;

/**
 * The Class Command.
 * 
 * @author He Xiaoan
 * @author Ji Raymond
 */
public class Command {
	
	/** The command. */
	String command;
	
	/** The arguments. */
	String[] arguments;
	
	/**
	 * Instantiates a new command.
	 *
	 * @param rawInput the raw input
	 */
	public Command(String rawInput){
		String [] input = rawInput.trim().split(" ");
		command = input[0];
		arguments = new String[0];
		if (input.length != 1){
			arguments = new String[input.length-1];
			for (int i = 1; i<input.length;i++){
				arguments[i-1]=input[i];
			}
		}
	}

	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Sets the command.
	 *
	 * @param command the new command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * Gets the arguments.
	 *
	 * @return the arguments
	 */
	public String[] getArguments() {
		return arguments;
	}

	/**
	 * Sets the arguments.
	 *
	 * @param arguments the new arguments
	 */
	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}
	
	
}
