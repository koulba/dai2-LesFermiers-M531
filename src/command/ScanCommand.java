package command;


import core.Location;
import core.Player;
import Inventory.Item;

import java.util.List;

public class ScanCommand implements Command {
    private Location[][] map;
    private Player player;

    public ScanCommand(Location[][] map, Player player) {
        this.map = map;
        this.player = player;
    }

    @Override
    public String getDescription() {
        return "Scan your current location for items";
    }

    @Override
    public String getUsage() {
        return "scan";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        return arg.isEmpty(); // The "scan" command doesn't require any argument
    }

    @Override
    public void execute(String arg) {
        if (hasValidArgument(arg)) {
            // Get the current position of the player
            int row = player.getRow();
            int col = player.getCol();
            Location currentLocation = map[row][col];
            List<Item> availableItems = currentLocation.getItems();

            // Displays items in the location
            if (availableItems.isEmpty()) System.out.println("Empty");
            else {
                System.out.println("Available items in this location:");
                for (int i = 0; i < availableItems.size(); ++i) {
                    Item item = availableItems.get(i);
                    System.out.println(item.getName());
                }
            }
        } else System.out.println("You don't need to add arguments to this command.");
    }

}
