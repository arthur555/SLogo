package engine.parser;

import engine.errors.CommandSyntaxException;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Simple parser based on regular expressions that matches program strings to
 * kinds of language features.
 *
 * @author Robert C. Duvall
 */
public class ProgramParser {
    private static final String NO_MATCH = "Some keywords are not defined in the syntax file";

    // "types" and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic)
    private List<Map.Entry<String, Pattern>> mySymbols;


    /**
     * Create an empty parser.
     */
    public ProgramParser () {
        mySymbols = new ArrayList<>();
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addPatterns (String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public String getSymbol (String text) throws CommandSyntaxException {
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        throw new CommandSyntaxException(NO_MATCH);
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }
}
