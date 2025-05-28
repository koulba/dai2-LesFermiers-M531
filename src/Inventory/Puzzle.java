package Inventory;


import core.Location;

public class Puzzle extends Item {
    private final String ANSWER;
    private final String REWARD;

    public Puzzle(String riddle, String answer, String reward,Location concernedLocation) {
        super("Puzzle", "To "+"\033[1;32m"+"unlock"+"\033[0m"+" this hint, answer that riddle:\n"+riddle, ItemType.PUZZLE, concernedLocation);
        this.ANSWER = answer;
        this.REWARD = reward;
    }

    public String getAnswer() {return ANSWER;}
    public String getReward() {return REWARD;}

}
