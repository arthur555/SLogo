package engine.translator;

import engine.errors.CommandSyntaxException;

import java.util.MissingResourceException;

/**
 * This interface handles the adding and setting of language files used to translate raw strings into commands. For example, the classes that implement this interface translate "fd" or "forward" to "Forward", or translating "fd" to "Command".
 *
 * @author Haotian Wang
 */
public interface Translator {
    /**
     * This method add more patterns to an internal storage of existing patterns.
     *
     * @param file: This is a String identifying the location of the file to be read, starting from backend/src/,
     */
    void addPatterns(String file) throws MissingResourceException;

    /**
     * This method clears existing patterns before adding new ones.
     *
     * @param file: This is a String identifying the location of the file to be read, starting from backend/src/,
     */
    void setPatterns(String file) throws MissingResourceException;

    /**
     * This method returns the underlying real String corresponding to the raw input String.
     *
     * @param text: The raw input String.
     * @return A String that represents the meaning of the input String. For example "forward" or "fd" is actually "Forward".
     * @throws CommandSyntaxException
     */
    String getSymbol(String text) throws CommandSyntaxException;
}
