package command;

import core.Location;
import main.WorldMap;

public class CommandMove implements Command {
    private final WorldMap map;

    public CommandMove(WorldMap map) {
        this.map = map;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: move <north|south|east|west>");
            return;
        }

        Direction dir;
        try {
            dir = Direction.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid direction.");
            return;
        }

        int newRow = map.getPlayerRow() + dir.dRow;
        int newCol = map.getPlayerCol() + dir.dCol;

        Location dest = map.getLocationAt(newRow, newCol);
        if (dest == null) {
            System.out.println("Impossible to move there.");
        } else if (dest.isLocked()) {
            System.out.println("Zone locked.");
        } else {
            map.movePlayer(newRow, newCol);
            System.out.println(dest.inspect());
        }
    }
}