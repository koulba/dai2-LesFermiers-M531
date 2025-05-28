package core;

import command.*;
import main.Player;
import main.WorldMap;
import utils.Color;
import utils.StringStyling;
import utils.Style;

import java.util.Scanner;

public class Game {
    private WorldMap worldMap;
    private Player player;
    private CommandRegistry registry;

    public Game() {
        System.out.println("Initializing game...");
    }

    public void run() {
        // Init world
        worldMap = new WorldMap(3, 3);
        Location start = new Location("Village", "A peaceful village", false);
        Location forest = new Location("Forest", "A scary forest", true);
        Location river = new Location("River", "A fresh river", false);

        worldMap.addLocation(start, 1, 1);
        worldMap.addLocation(forest, 0, 1);
        worldMap.addLocation(river, 1, 2);
        worldMap.setPlayerPosition(1, 1);

        player = new Player();

        // Commands
        registry = new CommandRegistry();
        registry.register("move", new CommandMove(worldMap));
        registry.register("map", new CommandMap(worldMap));

        System.out.println(StringStyling.StyleString("Welcome to the adventure game!", Style.BOLD, Color.BLUE));
        System.out.println("Use 'move <north|south|east|west>' or 'map'. Type 'quit' to exit.\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) break;
            registry.execute(input);
        }
    }
}
