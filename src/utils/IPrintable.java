package utils;

/**
 * Interface for elements that can be printed to the console.
 * This interface defines methods for getting a string representation of an element
 * and determining if it should be displayed in gray.
 * @see Array2Dprinter
 */
public interface IPrintable {
    /**
     * @return The string representation of the element
     */
    String getPrintableString();
    /**
     * @return true if the element should be displayed in gray, false otherwise
     */
    boolean isGrayedOut();
}
