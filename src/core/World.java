package core;

import java.util.ArrayList;
import java.util.List;

public class World {
    private static final World instance = new World(4, 3);

    private Location[][] grid;
    private List<Location> locations;

    private World(int rows, int columns) {
        grid = new Location[rows][columns];
        locations = new ArrayList<>();
    }

    public Location getLocation(int row, int col) {
        if (isValidPosition(row, col)) return grid[row][col];
        return null;
    }

    public Location getLocationByName(String locationName) {
        for (Location location : locations) {
            if (location.getName().equals(locationName)) {
                return location;
            }
        }
        return null;
    }

    public void addLocation(Location location, int row, int col) {
        if (isValidPosition(row, col)) {
            grid[row][col] = location;
            locations.add(location);
            location.setRow(row);
            location.setCol(col);
        }
    }

    private boolean isValidPosition(int row, int col) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    public Location[][] getMap() {return grid;}
    public static World getInstance() {return instance;}
}