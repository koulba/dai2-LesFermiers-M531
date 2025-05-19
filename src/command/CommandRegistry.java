package command;

import java.util.HashMap;

public class CommandRegistry {
    private final HashMap<String, Command> commands = new HashMap<>();

    public void register(String name, Command command) {
        commands.put(name, command);
    }

    public void execute(String input) {
        String[] parts = input.trim().split(" ");
        String name = parts[0];
        String[] args = java.util.Arrays.copyOfRange(parts, 1, parts.length);
        Command command = commands.get(name);
        if (command != null) {
            command.execute(args);
        } else {
            System.out.println("Unknown command.");
        }
    }
}
