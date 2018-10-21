package engine.parser;

import engine.Lexer.Token;
import engine.slogoast.Expression;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private Queue<Token> myTokens;

    public CrudeParser() {
        myTokens = new LinkedList<>();
    }

    /**
     * Reads in the queue of Tokens from the Lexer into the internal logic of the parser.
     *
     * @param tokens : A queue of Tokens read from the Lexer.
     */
    @Override
    public void readTokens(Queue<Token> tokens) {
        myTokens = tokens;
    }

    /**
     * Clear the stored tokens in the internal parser.
     */
    @Override
    public void clearTokens() {
        myTokens.clear();
    }

    /**
     * @return An SLogoAST for the interpreter to process.
     */
    @Override
    public Expression returnAST() {
        return null;
    }
}
