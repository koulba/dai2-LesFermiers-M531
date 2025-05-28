package main;

import java.util.Scanner;

import command.CommandRegistry;
import command.Command;
import core.Game;

public class Main {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // Create game
        Game.getInstance();

        // Game loop
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equalsIgnoreCase("quit")) {
                System.out.print("Thank you for playing the game !");
                break;
            } else executeCommand(userInput);
            System.out.println("");
        }
    }

    public static void executeCommand(String input) {
        CommandRegistry commandRegistry = Game.getCommandRegistry();
        String[] tokens = input.split(" ");
        String arg = input.substring(tokens[0].length(), input.length()).trim();

        if (tokens.length > 0) {
            String commandName = tokens[0];
            Command command = commandRegistry.getCommand(commandName);

            if (command != null) command.execute(arg);
            else System.out.println("Invalid command: "+commandName+" "+arg+"\nType 'help' for available commands");
        }
    }
}
