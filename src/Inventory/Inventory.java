package Inventory;


import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {return items;}

    public Item getItem(String name, String location) {
        for (int i = 0; i < items.size(); ++i) {
            if (items.get(i).getName().equalsIgnoreCase(name) && items.get(i).getConcernedLocation().getName().equalsIgnoreCase(location)) {
                return items.get(i);
            }
        }
        return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean containsItem(Item item) {
        return items.contains(item);
    }

}
