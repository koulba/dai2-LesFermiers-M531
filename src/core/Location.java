package core;

import java.util.ArrayList;

import Inventory.Item;
import Inventory.ItemType;

public class Location {
    private String name;
    private String description;
    private int row;
    private int col;
    private boolean locked;
    private ArrayList<Item> items;

    public Location(String name, String description, boolean isLocked) {
        this.name = name;
        this.description = description;
        this.locked = isLocked;
        this.items = new ArrayList<Item>();
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public int getRow() {return row;}
    public int getCol() {return col;}

    public boolean isLocked() {return locked;}

    public void setLocked(boolean locked) {this.locked = locked;}
    public void setRow(int row) {this.row = row;}
    public void setCol(int col) {this.col = col;}

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getItemByName(String itemName) {
        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {return items;}

    public boolean containsItem(ItemType type) {
        for (int i = 0; i < items.size(); ++i) {
            if (items.get(i).getType() == ItemType.PUZZLE) return true;
        }
        return false;
    }
}