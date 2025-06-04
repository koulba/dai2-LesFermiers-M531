
package core;

import command.*;
import Inventory.Item;
import Inventory.ItemType;
import Inventory.Puzzle;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<String> commandHistory = new ArrayList<>();
    private static final Game instance = new Game();
    private static CommandRegistry commandRegistry;
    private static Location[][] map;
    private static World world;
    private static Player player;
    private static String currentLoc;
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String ORANGE_BOLD = "\033[1;38;5;208m";
    public static final String RESET_COLOR = "\033[0m";


    private Game() {
        createWorld();
        createPlayer();
        createItems();
        createCommands();
        initMessage();
    }

    private void createWorld() {
        System.out.println(GREEN_BOLD+"Initializing Game World"+RESET_COLOR);
        Game.world = World.getInstance();
        world.addLocation(new Location("Nexus", "Team's Headquarter.", false), 0, 0);
        world.addLocation(new Location("Abîme Hurlant", "Inspired by the Howling Abyss, this windswept bridge is shrouded in eternal blizzards and ancient Freljordian whispers. The spirits of fallen warriors echo through the storm.", false), 0, 1);
        world.addLocation(new Location("Amumu's Tomb", "An isolated tomb hidden in the Shuriman sands, said to be the final resting place of the Sad Mummy. Some say his sorrow still lingers here, cursing those who enter.", false), 0, 2);
        world.addLocation(new Location("Carapateur", "A darkened riverbank where the Rift Scuttler once thrived. Twisted by dark magic, the land now pulses with necromantic energy. Locals dare not fish these waters.", false), 1, 0);
        world.addLocation(new Location("Baron Nashor", " A colossal manor perched on cursed ground. Named after the mighty Baron Nashor, it's said to contain a void portal within its basement. Strange lights flicker at night.", true), 1, 1);
        world.addLocation(new Location("Gromp", "Once a quaint forest cabin, now abandoned and overrun with bramble and decay. The ghostly croak of a giant frog-like beast echoes through the glades.", false), 1, 2);
        world.addLocation(new Location("Ancestral's Dragon", " A forgotten shrine turned amusement park. Statues of Elemental Dragons tower over the land, but some say the dragons' spirits still roam freely when the fog rolls in.", false), 2, 0);
        world.addLocation(new Location("Freljord", "A frozen wasteland, eerily quiet and blanketed in death. Reanimated corpses march across the snow, remnants of Lissandra’s ancient undead army.", false), 2, 1);
        world.addLocation(new Location("Zaun", "A crumbling chemical plant from Zaun’s depths, relocated by mad science. Toxic smog and unstable energies haunt the halls, don’t breathe too deep.", false), 2, 2);
        world.addLocation(new Location("Jungle", " A dense, shadowy jungle that shifts with every visit. A witch’s ghost is said to roam its twisted paths, cursing those who dare harvest its monstrous flora.", false), 3, 0);
        world.addLocation(new Location("Tower of Gods", "A towering structure lost to time, reaching into the heavens. Some say it was built to imprison a forgotten Celestial. Every floor reveals a different reality.", false), 3, 1);
        world.addLocation(new Location("Heralt", "A distant island where the Rift Herald once slumbered. A gaping eye-shaped cavern dominates the landscape, watching intruders with silent, ominous judgment.", true), 3, 2);
        Game.map = world.getMap();
    }


    private void createPlayer() {
        System.out.println(GREEN_BOLD+"Initializing Player"+RESET_COLOR);
        Game.player = new Player(0, 0);
        Game.currentLoc = player.getLocationName(world);
    }

    private void createItems() {
        System.out.println(GREEN_BOLD+"Initializing Items"+RESET_COLOR);
        world.getLocationByName("Zaun").addItem(new Item("Key", "Unlocks the 'Baron Nashor' location", ItemType.KEY, world.getLocationByName("Baron Nashor")));

        System.out.println(GREEN_BOLD+"Initializing Items"+RESET_COLOR);
        world.getLocationByName("Amumu's Tomb").addItem(new Item("Key", "Unlocks the 'Heralt' location", ItemType.KEY, world.getLocationByName("Heralt")));

        System.out.println(GREEN_BOLD+"Initializing Items"+RESET_COLOR);
        world.getLocationByName("Abîme Hurlant").addItem(new Item("Sword", "Infinity Edge", ItemType.SWORD, world.getLocationByName("Abîme Hurlant")));

        world.getLocationByName("Ancestral's Dragon").addItem(new Puzzle(
                "What haunts Ancestral's Dragon?",
                "Ghosts",
                "A strange "+PURPLE_BOLD+"item"+RESET_COLOR+" seems to have been lost near a Manor... but which one ?",
                world.getLocationByName("Ancestral's Dragon")
        ));

        world.getLocationByName("Nexus").addItem(
                new Item("Teleport Crystal", "A mysterious crystal that allows you to teleport between visited locations.", ItemType.MAGIC, world.getLocationByName("Mystery Inc. Headquarters"))
        );

        world.getLocationByName("Baron Nashor").addItem(new Puzzle(
                "What haunts the manor named after Baron Nashor?",
                "Void",
                "Another hint is awaiting you at "+YELLOW_BOLD+"Heralt"+RESET_COLOR+". You might want to check it.",
                world.getLocationByName("Baron Nashor")
        ));
    }

    public void executeCommand(String inputLine) {
        commandHistory.add(inputLine); // sauvegarde la commande

        String[] parts = inputLine.trim().split(" ");
        String commandWord = parts[0];
        String arg = inputLine.substring(commandWord.length()).trim();

        if (commandWord.equalsIgnoreCase("save")) {
            SaveManager.saveCommands(commandHistory);
            System.out.println("Game saved.");
            return;
        }

        Command command = commandRegistry.getCommand(commandWord);
        if (command != null) {
            command.execute(arg);
        } else {
            System.out.println("Invalid command: " + commandWord + "\nType 'help' for available commands");
        }

        updateTeleportCommand(); // vérifie si le cristal est dans l'inventaire
    }

    private void updateTeleportCommand() {
        boolean hasCrystal = player.getInventory().getItems().stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase("Teleport Crystal"));

        if (hasCrystal) {
            if (commandRegistry.getCommand("teleport") == null) {
                commandRegistry.registerCommand("teleport", new TeleportCommand(player, world));
            }
        } else {
            if (commandRegistry.getCommand("teleport") != null) {
                commandRegistry.unregisterCommand("teleport");
            }
        }
    }

    private void createCommands() {
        System.out.println(GREEN_BOLD+"Initializing Commands"+RESET_COLOR);
        Game.commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("help", new HelpCommand(commandRegistry));
        commandRegistry.registerCommand("move", new MoveCommand(map, player));
        commandRegistry.registerCommand("map", new MapCommand(map, player));
        commandRegistry.registerCommand("take", new TakeCommand(player.getInventory(), player, world));
        commandRegistry.registerCommand("use", new UseCommand(player.getInventory()));
        commandRegistry.registerCommand("inventory", new InventoryCommand(player.getInventory()));
        commandRegistry.registerCommand("look", new LookCommand(map, player));
    }

    private void initMessage() {
        System.out.println("Welcome, Summoner.\n" +
                "You stand at the threshold of a world where shadows whisper and legends walk. \nFrom the cursed halls of Baron Nashor to the ghostly fog of Ancestral's Dragon, every step holds mystery — and danger.\n" +
                "Choose your path wisely… the Rift remembers.\n");
        System.out.println(BLUE_BOLD+"Type 'help' for available commands"+RESET_COLOR);
        System.out.println("\nYou are now at "+YELLOW_BOLD+currentLoc+RESET_COLOR);
        System.out.println(world.getLocationByName(currentLoc).getDescription());
        System.out.println("");
    }

    public static CommandRegistry getCommandRegistry() {return commandRegistry;}
    public static Game getInstance() {return instance;}
}
