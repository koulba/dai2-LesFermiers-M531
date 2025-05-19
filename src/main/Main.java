package main;

import utils.Color;
import utils.StringStyling;
import utils.Style;

public class Main {
    public static void main(String[] args) {
        System.out.println(StringStyling.StyleString("Starting...", Style.ITALIC, Color.BLACK));
        Game game = new Game();
        game.run();
        System.out.println(StringStyling.StyleString("Terminating...", Style.ITALIC, Color.BLACK));
    }
}
