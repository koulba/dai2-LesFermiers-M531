package Inventory;


import core.Location;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private Location concernedLocation;

    public Item(String name, String description, ItemType type, Location concernedLocation) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.concernedLocation = concernedLocation;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public ItemType getType() {return type;}
    public Location getConcernedLocation() {return concernedLocation;}

}
