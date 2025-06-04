package command;

import core.Location;
import core.World;
import core.Player;

public class TeleportCommand implements Command {
    private Player player;
    private World world;

    public TeleportCommand(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public String getDescription() {
        return "Teleport to a previously visited location if you have the Teleport Crystal.";
    }

    @Override
    public String getUsage() {
        return "teleport <location-name>";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        // On considère qu'un argument valide est un non-empty string qui correspond à un lieu existant
        if (arg == null || arg.trim().isEmpty()) return false;
        Location loc = world.getLocationByName(arg.trim());
        return loc != null;
    }

    @Override
    public void execute(String args) {
        if (!player.getInventory().hasItem("Teleport Crystal")) {
            System.out.println("You don't have a Teleport Crystal.");
            return;
        }

        if (args == null || args.trim().isEmpty()) {
            System.out.println("Usage: " + getUsage());
            return;
        }

        Location target = world.getLocationByName(args.trim());
        if (target == null) {
            System.out.println("Unknown location: " + args);
            return;
        }

        if (!player.hasVisited(target)) {
            System.out.println("You can only teleport to locations you have already visited.");
            return;
        }

        player.setPosition(target.getRow(), target.getCol());
        System.out.println("Teleported to " + target.getName() + ".");
        System.out.println(target.getDescription());
    }
}
