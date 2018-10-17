package engine.parser;

import engine.SLogoAST;
import engine.parser.translator.LanguageTranslator;
import engine.parser.translator.TypeTranslator;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private static final String PREFIX = "engine/parser/languages";
    private static final String[] LANGUAGES = {"Syntax", "Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"};
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String SYNTAX = "Syntax";

    private TypeTranslator myType;
    private LanguageTranslator myLanguage;

    public CrudeParser() {
        myType = new TypeTranslator();
        myLanguage = new LanguageTranslator();
    }

    public CrudeParser(String language) {
        rawProcessor =
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
