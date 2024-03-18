package command;

public class Invoker {
    private Command command;

    // Constructor
    public Invoker() {
        // Initialize command to null or any default value
        this.command = null;
    }

    // Constructor with command parameter
    public Invoker(Command command) {
        this.command = command;
    }

    // Getter method for command
    public Command getCommand() {
        return command;
    }

    // Setter method for command
    public void setCommand(Command command) {
        this.command = command;
    }

    // Method to execute the command
    public void executeCommand() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set. Cannot execute.");
        }
    }
}
