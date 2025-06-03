package command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private Map<String, Command> commands;

    public CommandRegistry() {
        commands = new HashMap<>();
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName.toLowerCase(), command);
    }

    public Command getCommand(String commandName) {
        if (commandName == null) return null;
        return commands.get(commandName.toLowerCase());
    }

    public void unregisterCommand(String commandName) {
        if (commandName != null) {
            commands.remove(commandName.toLowerCase());
        }
    }

    public String[] getCommandNames() {
        String[] allCommands = new String[commands.size()];
        return commands.keySet().toArray(allCommands);
    }
}
