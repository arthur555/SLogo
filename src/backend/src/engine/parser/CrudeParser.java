package engine.parser;

import engine.SLogoAST;
import engine.parser.translator.TypeTranslator;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private static final String[] languages = {"Syntax", "Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Syntax"};

    private TypeTranslator rawProcessor;

    public CrudeParser() {
        rawProcessor = new TypeTranslator();
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
