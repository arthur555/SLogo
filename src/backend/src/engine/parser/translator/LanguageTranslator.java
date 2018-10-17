package engine.parser.translator;

import engine.errors.CommandSyntaxException;

import java.util.*;
import java.util.regex.Pattern;

/**
 * This class handles the translation of user input in different languages to input that Parser can understand.
 *
 * @author Haotian Wang
 */
public class LanguageTranslator implements Translator {
    private static final String NO_MATCH = " is not defined in the language files.";

    private Map<String, Pattern> mySymbols;

    /**
     * Create an empty parser.
     */
    public LanguageTranslator() {
        mySymbols = new HashMap<>();
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    @Override
    public void addPatterns (String syntax) throws MissingResourceException{
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.put(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }

    @Override
    public void setPatterns(String syntax) throws MissingResourceException{
        mySymbols.clear();
        addPatterns(syntax);
    }

    /**
     * Returns language's type associated with the given text if one exists
     *
     * @param text: The input raw String
     * @return The associated String symbol that the raw string represents.
     * @throws CommandSyntaxException: When the input raw String is not defined in the resources/languages properties files, a CommandSyntaxException is thrown, containing a message about which String is not defined.
     */
    @Override
    public String getSymbol (String text) throws CommandSyntaxException {
        for (var e : mySymbols.entrySet()) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        throw new CommandSyntaxException("\"" + text + "\"" + NO_MATCH);
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }
}
