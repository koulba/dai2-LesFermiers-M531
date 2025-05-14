package examples;

import utils.Color;
import utils.StringStyling;
import utils.Style;

public class ConsoleStylingExample {

    public static void Example() {
        System.out.println("Console Styling Example");
        System.out.println("====================================");
        // Basic style usage
        String styled = StringStyling.StyleString("Hello, World! Out of the blue", Style.BOLD, Color.BLUE);
        System.out.println(styled);

        // Using bright styling with background
        String styledBright = StringStyling.StyleStringBright("Warning!", Style.UNDERLINE, Color.RED, Color.YELLOW);
        System.out.println(styledBright);

        // Custom console style prefix (useful for dynamic prints)
        String prefix = StringStyling.toStyleColor(Style.ITALIC, Color.CYAN);
        System.out.println(prefix + "This should be italic cyan text." + StringStyling.resetChar());

        // Background only
        String backgroundOnly = StringStyling.toBackgroundColor(Color.GREEN);
        System.out.println(backgroundOnly + "Green background text" + StringStyling.resetChar());

        System.out.println("====================================");
    }

}
