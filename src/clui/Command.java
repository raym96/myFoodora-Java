package clui;

public class Command {
	String command;
	String[] arguments;
	
	public Command(String rawInput){
		String [] input = rawInput.trim().split(" ");
		command = input[0];
		if (input.length != 1){
			arguments = new String[input.length-1];
			for (int i = 1; i<input.length;i++){
				arguments[i-1]=input[i];
			}
		}
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}
	
	
}
