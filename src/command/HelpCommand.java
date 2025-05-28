package command;

import java.util.Arrays;
import java.util.List;

public class HelpCommand implements Command {
    private CommandRegistry commandRegistry;

    public HelpCommand(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Override
    public String getDescription() {
        return "Display the list of available commands or the details of a specific one";
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        List<String> allCommands = Arrays.asList(commandRegistry.getCommandNames());
        return allCommands.contains(arg);
    }

    @Override
    public void execute(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Available commands:");
            for (String commandName : commandRegistry.getCommandNames()) {
                Command command = commandRegistry.getCommand(commandName);
                System.out.println(command.getUsage() + " - " + command.getDescription());
            }
        } else {
            if (hasValidArgument(arg)) {
                Command command = commandRegistry.getCommand(arg);
                System.out.println(command.getUsage() + " - " + command.getDescription());
            } else System.out.println("'"+arg+"' is not a valid argument !");
        }
    }
}
