package engine.Lexer;

import engine.parser.CrudeParser;
import engine.translator.LanguageTranslator;
import engine.translator.TypeTranslator;

import java.util.Queue;

public class CrudeLexer implements Lexer{
    private static final String PREFIX = "engine/translator/languages/";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String SYNTAX = "Syntax";

    private TypeTranslator myType;
    private LanguageTranslator myLanguage;

    /**
     * Constructs a CrudeParser with the user-defined language as the starting recognized language.
     *
     * @param language: A String such as "English", "Chinese" or "French".
     */
    public CrudeLexer(String language) {
        myType = new TypeTranslator(PREFIX + SYNTAX);
        myLanguage = new LanguageTranslator(PREFIX + language);
    }

    /**
     * The default constructor will choose English.properties as its starting recognized languages and Syntax.properties as its starting recognized syntax.
     */
    public CrudeLexer() {
        this(DEFAULT_LANGUAGE);
    }

    /**
     * Reset the language dictionary to use the default language only, which is English.
     */
    public void resetLanguage() {
        myLanguage.setPatterns(PREFIX + DEFAULT_LANGUAGE);
    }

    /**
     * This processes the user input raw String.
     *
     * @param input : A user input raw String.
     */
    @Override
    public void readString(String input) {

    }

    /**
     * Return a list of Token from the input String, after translation by two translators.
     *
     * @return A list of Token from the input String.
     */
    @Override
    public Queue<Token> getTokens() {
        return null;
    }
}
