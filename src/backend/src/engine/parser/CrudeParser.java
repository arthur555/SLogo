package engine.parser;

import engine.StateMachine;
import engine.errors.CommandSyntaxException;
import engine.parser.translator.LanguageTranslator;
import engine.parser.translator.TypeTranslator;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private static final String PREFIX = "engine/parser/languages/";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String SYNTAX = "Syntax";

    private TypeTranslator myType;
    private LanguageTranslator myLanguage;
    private StateMachine myVariables;

    /**
     * The default constructor will choose English.properties as its starting recognized languages and Syntax.properties as its starting recognized syntax.
     */
    public CrudeParser() {
        this(DEFAULT_LANGUAGE);
    }

    /**
     * Constructs a CrudeParser with the user-defined language as the starting recognized language.
     *
     * @param language: A String such as "English", "Chinese" or "French".
     */
    public CrudeParser(String language) {
        myType = new TypeTranslator(PREFIX + SYNTAX);
        myLanguage = new LanguageTranslator(PREFIX + language);
        myVariables = new StateMachine();
    }

    /**
     * Reset the language dictionary to use the default language only, which is English.
     */
    public void resetLanguage() {
        myLanguage.setPatterns(PREFIX + DEFAULT_LANGUAGE);
    }

    /**
     * This reads in a String representing a complete command.
     *
     * @param rawCommand: A String that the user types in exactly as it appears in the editor view.
     */
    @Override
    public void readRawCommand(String rawCommand) {
        if (rawCommand.isEmpty()) { return; }
        String[] arr = rawCommand.split("\\s+");
        String[] typeArray = new String[arr.length];
        String[] langArray = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            try {
                typeArray[i] = myType.getSymbol(arr[i]);
            } catch (CommandSyntaxException e) {
                // TODO
                e.printStackTrace();
            }
            try {
                langArray[i] = myLanguage.getSymbol(arr[i]);
            } catch (CommandSyntaxException e) {
                // TODO
                e.printStackTrace();
            }
        }
    }

    /**
     * @return An SLogoAST for the interpreter to process.
     */
    @Override
    public SLogoAST returnAST() {
        return null;
    }
}
