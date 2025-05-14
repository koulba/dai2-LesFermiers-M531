package main;


import examples.ConsoleStylingExample;
import examples.StringManipulation;
import examples.UserInputExample;
import utils.Color;
import utils.StringStyling;
import utils.Style;

public class Main {

    public static void main(String[] args) {
        // you can safely remove those examples lines
        StringManipulation.Example();
        ConsoleStylingExample.Example();
        UserInputExample.Example();

        System.out.println(StringStyling.StyleString("Starting...", Style.ITALIC, Color.BLACK));
        Game game = new Game();
        game.run();
        System.out.println(StringStyling.StyleString("Terminating...", Style.ITALIC, Color.BLACK));
    }
}
