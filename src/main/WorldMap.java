package main;

import core.Location;

public class WorldMap {
    private Location[][] locations;
    private int playerRow, playerCol;

    public WorldMap(int rows, int cols) {
        locations = new Location[rows][cols];
    }

    public void addLocation(Location loc, int row, int col) {
        locations[row][col] = loc;
    }

    public Location getLocationAt(int row, int col) {
        if (row < 0 || row >= locations.length || col < 0 || col >= locations[0].length)
            return null;
        return locations[row][col];
    }

    public void setPlayerPosition(int row, int col) {
        this.playerRow = row;
        this.playerCol = col;
    }

    public void movePlayer(int row, int col) {
        this.playerRow = row;
        this.playerCol = col;
    }

    public int getPlayerRow() { return playerRow; }
    public int getPlayerCol() { return playerCol; }

    public Location[][] getLocations() {
        return locations;
    }
}