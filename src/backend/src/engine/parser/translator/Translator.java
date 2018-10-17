package engine.parser.translator;

import engine.errors.CommandSyntaxException;

/**
 * This interface handles the adding and setting of language files used to translate raw strings into commands. For example, the classes that implement this interface translate "fd" or "forward" to "Forward", or translating "fd" to "Command".
 *
 * @author Haotian Wang
 */
public interface Translator {
    void addPatterns(String file);

    void setPatterns(String file);

    String getSymbol(String text) throws CommandSyntaxException;
}
