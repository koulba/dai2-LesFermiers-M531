package command;

import java.util.Scanner;

import Inventory.Inventory;
import Inventory.Item;
import Inventory.Puzzle;

public class UseCommand implements Command {
    private static Scanner input = new Scanner(System.in);
    private Item item;
    private Inventory inventory;

    public UseCommand(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getDescription() {
        return "Use an item in your inventory";
    }

    @Override
    public String getUsage() {
        return "use <ItemName> <LocationName>";
    }

    @Override
    public boolean hasValidArgument(String arg) {
        return arg != null && !arg.isEmpty();
    }

    public void setItem(Item item) {this.item = item;}

    @Override
    public void execute(String arg) {
        String item = arg.split(" ")[0].trim();
        String args = "";

        if (arg.split(" ").length > 1) {
            args = arg.substring(item.length()+1, arg.length()).trim();
        } else {
            System.out.println("Invalid arguments for the 'use' command !");
            if (arg.equalsIgnoreCase("key")) System.out.println("Try 'use key <the location it unlocks>'");
            else if (arg.equalsIgnoreCase("puzzle")) System.out.println("Try 'use puzzle <the location you found it>'");
            else if (arg.equalsIgnoreCase("scooby snacks")) System.out.println("Try 'use scooby snacks <the location you found it>'");
            else System.out.println("Type 'help use' to see how this command works.");
            return;
        }

        this.item = inventory.getItem(item, args);

        if (inventory.containsItem(this.item)) {
            System.out.println("Your Inventory");
            switch(this.item.getType()) {
                case KEY:
                    this.item.getConcernedLocation().setLocked(false);
                    System.out.println("You unlocked the "+"\033[1;33m"+this.item.getConcernedLocation().getName()+"\033[0m"+" location !");
                    inventory.removeItem(this.item);
                    break;

                case SWORD:
                    this.item.getName();
                    System.out.println("You used an " +"\033[1;38;5;208m"+this.item.getConcernedLocation().getName()+"\033[0m"+"and you have now more power");
                    inventory.removeItem(this.item);
                    break;

                case PUZZLE:
                    System.out.println(this.item.getDescription()+"\n");
                    Puzzle puzzle  = (Puzzle)this.item;
                    System.out.print("Your answer: ");
                    String userInput = input.nextLine();
                    if (userInput.equalsIgnoreCase(puzzle.getAnswer())) {
                        System.out.println("Congratulations, that's "+"\033[1;32m"+"correct"+"\033[0m"+ "! Here is your reward:");
                        System.out.println(puzzle.getReward());
                    } else {
                        System.out.println("\033[1;31m"+"Wrong answer !"+"\033[0m");
                    }
                    break;
            }
        } else {
            System.out.println("You do not own this item !");
        }
    }
}
