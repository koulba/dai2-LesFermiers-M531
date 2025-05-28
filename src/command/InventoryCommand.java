package command;


import Inventory.Inventory;
import Inventory.Item;

import java.util.List;

public class InventoryCommand implements Command {
    private Inventory inventory;

    public InventoryCommand(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getDescription() {
        return "Display your inventory";
    }

    @Override
    public String getUsage() {
        return "inventory";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        return arg.isEmpty(); // The "inventory" command doesn't require any argument
    }

    @Override
    public void execute(String arg) {
        List<Item> inventory = this.inventory.getItems();

        if (hasValidArgument(arg)) {
            if (inventory.isEmpty()) System.out.println("Empty");
            else {
                System.out.println("----------------------------------------------");
                for (int i = 0; i < inventory.size(); ++i) {
                    Item item = inventory.get(i);
                    System.out.print(item.getName());
                    switch(item.getType()) {
                        case KEY:
                            System.out.print(" - "+item.getDescription());
                            break;
                        case PUZZLE:
                            System.out.print(" ("+item.getConcernedLocation().getName()+")");
                            break;
                        case SWORD:
                            System.out.print(" - "+item.getDescription());
                            break;
                    }
                    System.out.println("");
                }
                System.out.println("----------------------------------------------");
            }
        } else System.out.println("You don't need to add arguments to this command.");
    }
}
