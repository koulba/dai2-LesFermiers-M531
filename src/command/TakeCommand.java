package command;


import core.Location;
import core.Player;
import core.World;
import Inventory.Inventory;
import Inventory.Item;

public class TakeCommand implements Command {
    private Inventory inventory;
    private Player player;
    private World world;

    public TakeCommand(Inventory inventory, Player player, World world) {
        this.inventory = inventory;
        this.player = player;
        this.world = world;
    }

    @Override
    public String getDescription() {
        return "take an item at the current location";
    }

    @Override
    public String getUsage() {
        return "take <item>";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        return arg != null && !arg.isEmpty();
    }

    @Override
    public void execute(String arg) {
        Location currentLocation = world.getLocation(player.getRow(), player.getCol());
        Item item = currentLocation.getItemByName(arg);
        if (item != null) {
            inventory.addItem(item);
            currentLocation.removeItem(item);
            System.out.println("You took the item: "+"\033[1;38;5;208m"+item.getName()+"\033[0m"+"\033[1;38;5;208m"+" - "+item.getDescription()+"\033[0m");
        } else {
            System.out.println("There is no item named '"+arg+"' at the current location");
        }
    }
}
