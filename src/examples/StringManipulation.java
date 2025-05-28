package examples;

public class StringManipulation {

    public static void Example() {

        System.out.println("String Manipulation Example");
        System.out.println("====================================");
        String original = "  Hello, World!  ";

        // Trim whitespace : removes leading and trailing spaces
        // Useful for cleaning up user input or formatting strings.
        // For example, if a user inputs " Hello ", you might want to trim it to
        // "Hello".
        String trimmed = original.trim();
        System.out.println("Trimmed: '" + trimmed + "'");

        // Convert to upper and lower case
        // Useful for case-insensitive comparisons or formatting.
        System.out.println("Uppercase: " + trimmed.toUpperCase());
        System.out.println("Lowercase: " + trimmed.toLowerCase());

        // Split string in parts given a delimiter
        String example = "apple banana cherry";
        String[] parts = example.split(" ");
        for (String part : parts) {
            System.out.println("Split Part: " + part);
        }

        // String formatting insert values into a string
        // Useful for creating dynamic strings, such as messages or logs.
        String name = "Alice";
        int age = 30;
        String formatted = String.format("Name: %s | Age: %d", name, age);
        System.out.println("Formatted String: " + formatted);

        // Reminder on String comparison
        // !!! Important: Use equals() for string comparison, never with ==
        String str1 = "Hello";
        String str2 = "Helloooo";
        if (str1.equals(str2)) {
            System.out.println("Strings are equal");
        } else {
            System.out.println("Strings are not equal");
        }

        System.out.println("====================================");
    }

}
