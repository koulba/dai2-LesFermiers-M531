package utils;

public class StringStyling {

    private static final String template = "\033[%s;%sm";
    private static final String backgroundTemplate = "\033[%sm";
    private static final String RESET = "\033[0m";

    /**
     * Styles a string with the given style and color.
     * @param value the string to be styled
     * @param style the text style (e.g., bold, italic)
     * @param c the text color
     * @return the styled string
     */
    public static String StyleString(String value, Style style, Color c){
        return StyleString(value, style, c, false);
    }

    /**
     * Styles a string with the given style and color.
     * @param value the string to be styled
     * @param style the text style (e.g., bold, italic)
     * @param c the text color
     * @param highInstensity whether to use high intensity colors
     * @return the styled string
     */
    public static String StyleString(String value, Style style, Color c, boolean highInstensity){
        String color = textPrefix(style, c, true);
        return color + value + RESET;
    }

    /**
     * Styles a string with the given text style and color and adds a background color.
     * @param value the string to be styled
     * @param style the text style (e.g., bold, italic)
     * @param textColor the text color
     * @param backgroundColor the background color
     * @return the styled string
     */
    public static String StyleString(String value, Style style, Color textColor, Color backgroundColor){
        String color = textPrefix(style, textColor, false);
        String bck = backgroundPrefix(backgroundColor, false);
        return color + bck + value + RESET;
    }

    /**
     * Styles a string with the given text style and color and adds a background color.
     * @param value the string to be styled
     * @param style the text style (e.g., bold, italic)
     * @param textColor the text color
     * @param backgroundColor the background color
     * @return the styled string
     */
    public static String StyleStringBright(String value, Style style, Color textColor, Color backgroundColor){
        String color = textPrefix(style, textColor, true);
        String bck = backgroundPrefix(backgroundColor, true);
        return color + bck + value + RESET;
    }

    /**
     * Sets the console's text style and color
     * @param style the text style (e.g., bold, italic)
     * @param textColor the text color
     * @param highInstensity whether to use high intensity colors
     * @return the ANSI escape code for the text style and color
     */
    public static String toStyleColor(Style style, Color color) {
        return textPrefix(style, color ,false);
    }

    /**
     * Sets the console's background style and color
     * @param color the text color
     * @param highInstensity whether to use high intensity colors
     * @return the ANSI escape code for the text style and color
     */
    public static String toBackgroundColor(Color c) {
        return backgroundPrefix(c, false);
    }

    /**
     * Resets the console's text style and color
     * @return the ANSI escape code to reset the text style and color and background
     */
    public static String resetChar(){
        return RESET;
    }

    private static String backgroundPrefix(Color color, boolean highInstensity) {
        return String.format(backgroundTemplate, color.toBackgroundString(highInstensity));
    }

    private static String textPrefix(Style style, Color textColor, boolean highInstensity) {
        return String.format(template,style,textColor.toColorString(highInstensity));
    }

}
