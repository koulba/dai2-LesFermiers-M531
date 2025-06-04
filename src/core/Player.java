
package core;

import Inventory.Inventory;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private int row;
    private int col;
    private Inventory inventory;
    private Set<Location> visitedLocations; // lieux visités

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
        this.inventory = new Inventory();
        this.visitedLocations = new HashSet<>();
        visitedLocations.add(World.getInstance().getLocation(row, col));
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public Inventory getInventory() { return inventory; }
    public String getLocationName(World world) { return world.getLocation(this.row, this.col).getName(); }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        // Ajouter le lieu actuel dans les lieux visités
        Location currentLocation = World.getInstance().getLocation(row, col);
        if (currentLocation != null) {
            visitedLocations.add(currentLocation);
        }
    }

    // Pour vérifier si un lieu a déjà été visité
    public boolean hasVisited(Location location) {
        return visitedLocations.contains(location);
    }
}

