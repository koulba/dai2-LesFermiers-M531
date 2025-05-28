package utils;

/**
 * Utility class to print a 2D array in a grid formatted way.
 */
public class Array2Dprinter {

    private static int SPACING = 1;

    private static Style highlightTextStyle = Style.BOLD;
    private static Color highlightTextColor = Color.WHITE;
    private static Color highlightBackgroundColor = Color.BLUE;

    public static void setHighlightStyle(Style style, Color color, Color backgroundColor) {
        highlightTextColor = color;
        highlightTextStyle = style;
        highlightBackgroundColor = backgroundColor;
    }

    private static Color grayedOutTextColor = Color.BLACK;
    private static Style grayedOutTextStyle = Style.NORMAL;
    private static Color grayedOutBackgroundColor = Color.BLACK;

    public static void setGrayedOutStyle(Style style, Color color, Color backgroundColor) {
        grayedOutTextColor = color;
        grayedOutTextStyle = style;
        grayedOutBackgroundColor = backgroundColor;
    }

    /**
     * Build a grid string representation of a 2D array.
     *
     * @param array2D The 2D array to be printed. first dimension is the row (vertical), second dimension is the column (horizontal).
     * @param highlightRow The row coordinate of the cell to be highlighted
     * @param highlightColumn The column coordinate of the cell to be highlighted
     * @return The grid string representation of the 2D array
     */
    public static String print2DArray(IPrintable[][] array2D, int highlightRow, int highlightColumn) {
        StringBuilder output = new StringBuilder();
        int maxLength = findMaxLength(array2D); // Find the maximum length of elements for alignment purposes.
        int numColumns = array2D[0].length; // Get the number of columns in the array.

        for (int row = 0; row < array2D.length; row++) {
            appendHorizontalLine(output, maxLength, numColumns); // Print the top horizontal line for each element.
            appendElements(output, array2D[row], maxLength, row == highlightRow, highlightColumn); // Print the elements in the row.
        }
        appendHorizontalLine(output, maxLength, numColumns); // Print the last horizontal line of the array.
        return output.toString();
    }

    private static <T> void appendHorizontalLine(StringBuilder output, int maxLength, int numColumns) {
        for (int col = 0; col < numColumns; col++) {
            output.append("+");
            output.append("-".repeat(maxLength + 2 * SPACING)); // Print the horizontal line with length according to the maxLength.
        }
        output.append("+");
        output.append(System.lineSeparator());
    }

    private static void appendElements(StringBuilder output, IPrintable[] row, int maxLength, boolean isHighlightRow, int highlightColumn) {

        output.append("|");

        for (int col = 0; col < row.length; col++) {
            IPrintable element = row[col];
            String text = (element != null) ? element.getPrintableString() : "";

            double padding = (maxLength - text.length()) * 0.5;
            int paddingBefore = (int)Math.floor(padding);
            int paddingAfter = (int)Math.ceil(padding);

            String paddingSpacesBefore = " ".repeat(paddingBefore);
            String paddingSpacesAfter = " ".repeat(paddingAfter);

            text = paddingSpacesBefore + text + paddingSpacesAfter;
            if (isHighlightRow && col == highlightColumn) {
                text = StringStyling.StyleString(text, highlightTextStyle, highlightTextColor, highlightBackgroundColor);
            } else if (element != null && element.isGrayedOut()) {
                text = StringStyling.StyleString(text, grayedOutTextStyle, grayedOutTextColor, grayedOutBackgroundColor);
            }

            output.append(" ".repeat(SPACING))
            .append(text)
            .append(" ".repeat(SPACING))
            .append("|");
        }
        output.append(System.lineSeparator());
    }

    private static int findMaxLength(IPrintable[][] array) {
        int maxLength = 0;
        for (IPrintable[] row : array) {
            for (IPrintable element : row) {
                if (element != null && element.toString().length() > maxLength) {
                    maxLength = element.toString().length();
                }
            }
        }
        return maxLength;
    }
}

