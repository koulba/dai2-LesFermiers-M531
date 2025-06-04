package main;

import java.util.Scanner;

import command.CommandRegistry;
import command.Command;
import core.Game;
import core.SaveManager;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = null;
        String choice = "";

        while (true) {
            System.out.println("Type 'new' to start a new game or 'load' to load the last save:");
            choice = input.nextLine().trim().toLowerCase();

            if (choice.equals("new")) { // gestion d'erreur new game
                System.out.println("Starting new game...");
                game = Game.getInstance();
                break;
            } else if (choice.equals("load")) {
                System.out.println("Loading game...");
                game = Game.getInstance();
                SaveManager.loadAndReplayCommands(game);
                break;
            } else {
                System.out.println("Invalid input. Please type 'new' or 'load'.");
            }
        }

        // Boucle principale du jeu
        while (true) {
            String userInput = input.nextLine().trim();
            if (userInput.equalsIgnoreCase("quit")) {
                System.out.println("Thank you for playing the game!");
                break;
            } else {
                game.executeCommand(userInput);
                System.out.println();
            }
        }
    }
}
