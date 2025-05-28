package command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private Map<String, Command> commands;

    public CommandRegistry() {
        commands = new HashMap<>();
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public String[] getCommandNames() {
        String[] allCommands = new String[commands.size()];
        return commands.keySet().toArray(allCommands);
    }
}
