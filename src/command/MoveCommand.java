package command;

import core.Location;
import core.Player;
import Inventory.ItemType;

public class MoveCommand implements Command {
    private Location[][] map;
    private Player player;
    private static final String WINNING_AREA = "";

    public MoveCommand(Location[][] map, Player player) {
        this.map = map;
        this.player = player;
    }

    @Override
    public String getDescription() {
        return "Move the player to a new location";
    }

    @Override
    public String getUsage() {
        return "move <direction>";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        // Check if the argument is a valid direction
        return arg.equalsIgnoreCase("north") || arg.equalsIgnoreCase("east") ||
                arg.equalsIgnoreCase("south") || arg.equalsIgnoreCase("west");
    }

    @Override
    public void execute(String arg) {
        if (hasValidArgument(arg)) {
            // Get the current position of the player
            int row = player.getRow();
            int col = player.getCol();
            Location currentLocation = map[row][col];

            // Calculate the new position based on the direction
            if (arg.equalsIgnoreCase("north")) {
                row -= 1;
            } else if (arg.equalsIgnoreCase("east")) {
                col += 1;
            } else if (arg.equalsIgnoreCase("south")) {
                row += 1;
            } else if (arg.equalsIgnoreCase("west")) {
                col -= 1;
            }

            // Check if the new position is within the map boundaries
            if (isValidPosition(row, col)) {
                Location newLocation = map[row][col];
                if (newLocation.isLocked()) System.out.println("This location is "+"\033[1;31m"+"locked"+"\033[0m");
                else {
                    player.setPosition(row, col);
                    System.out.println("You are now at "+"\033[1;33m"+newLocation.getName()+"\033[0m");
                    System.out.println(newLocation.getDescription());

                    // Won ?
                    if (newLocation.getName().equalsIgnoreCase(WINNING_AREA)) {
                        System.out.println("\n\033[1;32m"+"Bravo ! You won :)"+"\033[0m"+"\nYou can keep exploring the world, type 'quit' to end the game.");
                    }

                    // Check if there is an item to get in the zone
                    if (newLocation.containsItem(ItemType.PUZZLE)) {
                        player.getInventory().addItem(newLocation.getItemByName("puzzle"));
                        newLocation.removeItem(newLocation.getItemByName("puzzle"));
                        System.out.println("You found a new "+"\033[1;35m"+"item"+"\033[0m"+"! Check your "+"\033[1;36m"+"inventory"+"\033[0m"+" to see what it is");
                    }
                }
            } else System.out.println("Cannot move "+arg+" from "+currentLocation.getName());
        } else System.out.println("The direction you want to go to doesn't exist !\nType 'help go' to check the usage of this command.");
    }

    // Check if the position is valid within the map boundaries
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[row].length;
    }
}