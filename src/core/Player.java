package core;

import Inventory.Inventory;

public class Player {
    private int row;
    private int col;
    private Inventory inventory;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
        this.inventory = new Inventory();
    }

    public int getRow() {return row;}
    public int getCol() {return col;}
    public Inventory getInventory() {return inventory;}
    public String getLocationName(World world) {return world.getLocation(this.row, this.col).getName();}

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

}