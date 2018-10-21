package engine.parser;

import engine.Lexer.Token;
import engine.errors.CommandSyntaxException;
import engine.translator.LanguageTranslator;
import engine.translator.TypeTranslator;

import java.util.Queue;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {

    /**
     * Reads in the queue of Tokens from the Lexer into the internal logic of the parser.
     *
     * @param tokens : A queue of Tokens read from the Lexer.
     */
    @Override
    public void readTokens(Queue<Token> tokens) {

    }

    /**
     * @return An SLogoAST for the interpreter to process.
     */
    @Override
    public SLogoAST returnAST() {
        return null;
    }
}
