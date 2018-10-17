package engine.parser;

import engine.SLogoAST;
import engine.parser.translator.LanguageTranslator;
import engine.parser.translator.TypeTranslator;

import java.util.HashMap;
import java.util.Map;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private static final String PREFIX = "engine/parser/languages/";
    private static final String ALL_LANGUAGES = "all languages";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String SYNTAX = "Syntax";

    private TypeTranslator myType;
    private LanguageTranslator myLanguage;
    private Map<String, Object> myVariables;

    /**
     * The default constructor will choose English.properties as its starting recognized languages and Syntax.properties as its starting recognized syntax.
     */
    public CrudeParser() {
        myType = new TypeTranslator(PREFIX + SYNTAX);
        myLanguage = new LanguageTranslator(PREFIX + "English");
        myVariables = new HashMap<>();
    }

    /**
     * Constructs a CrudeParser with the user-defined language as the starting recognized language.
     *
     * @param language: A String such as "English", "Chinese" or "French".
     */
    public CrudeParser(String language) {
        myType = new TypeTranslator(PREFIX + SYNTAX);
        myLanguage = new LanguageTranslator(PREFIX + language);
        myVariables = new HashMap<>();
    }

    @Override
    public void readRawCommand(String rawCommand) {

    }

    @Override
    public void storeVariable() {

    }

    @Override
    public SLogoAST returnAST() {
        return null;
    }
}
