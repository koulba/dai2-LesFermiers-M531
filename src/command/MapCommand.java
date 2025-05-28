package command;

import core.Location;
import core.Player;

public class MapCommand implements Command {
    private Location[][] map;
    private Player player;

    public MapCommand(Location[][] map, Player player) {
        this.map = map;
        this.player = player;
    }

    @Override
    public String getDescription() {
        return "Display the Map";
    }

    @Override
    public String getUsage() {
        return "map";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        return arg.isEmpty(); // The "map" command doesn't require any argument
    }

    @Override
    public void execute(String arg) {
        printAsciiMap();
    }

    private void printAsciiMap() {
        int maxLength = findMaxLength();

        for (Location[] row : map) {
            printHorizontalLine(maxLength, row.length);
            printLocationNames(row, maxLength);
        }

        printHorizontalLine(maxLength, map[0].length);
    }

    private void printHorizontalLine(int maxLength, int numColumns) {
        for (int col = 0; col < numColumns; col++) {
            System.out.print("+");
            System.out.print("-".repeat(maxLength + 2));
        }
        System.out.println("+");
    }

    private void printLocationNames(Location[] row, int maxLength) {
        for (Location location : row) {
            System.out.print("|");

            if (location != null) {
                String name = location.getName();
                int padding = (maxLength - name.length()) / 2;
                String paddingSpaces = " ".repeat(padding + 1);

                if (player.getRow() == location.getRow() &&
                        player.getCol() == location.getCol()) {
                    System.out.print(paddingSpaces);
                    System.out.print("\033[1;33m"+name+"\033[0m");
                    // for (int i = 0; i < name.length(); i++) {
                    //     System.out.print("_");
                    //     System.out.print(name.charAt(i));
                    // }
                    System.out.print(paddingSpaces);
                } else {
                    System.out.print(paddingSpaces);
                    System.out.print(name);
                    System.out.print(paddingSpaces);
                }
            } else {
                System.out.print(" ".repeat(maxLength + 2));
            }
        }
        System.out.println("|");
    }

    private int findMaxLength() {
        int maxLength = 0;

        for (Location[] row : map) {
            for (Location location : row) {
                if (location != null) {
                    maxLength = Math.max(maxLength, location.getName().length());
                }
            }
        }

        return maxLength;
    }
}