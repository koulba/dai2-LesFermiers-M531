package main;

import java.util.Scanner;

import command.CommandRegistry;
import command.Command;
import core.Game;
import core.SaveManager;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Type 'new' to start a new game or 'load' to load the last save:");

        String choice = input.nextLine().trim().toLowerCase();
        Game game = Game.getInstance();

        if (choice.equals("load")) {
            System.out.println("Loading game...");
            SaveManager.loadAndReplayCommands(game);  // charge et rejoue la sauvegarde
        } else {
            System.out.println("Starting new game...");
        }

        // Boucle principale du jeu
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equalsIgnoreCase("quit")) {
                System.out.print("Thank you for playing the game !");
                break;
            } else {
                game.executeCommand(userInput);
                System.out.println("");
            }
        }
    }
}
