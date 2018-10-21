package engine.parser;

import engine.Lexer.Token;
import engine.errors.CommandSyntaxException;
import engine.slogoast.Binary;
import engine.slogoast.Direct;
import engine.slogoast.Expression;
import engine.slogoast.Unary;

import java.util.*;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private List<Token> myTokens;
    private Expression myAST;

    public CrudeParser() {
        myTokens = new ArrayList<>();
    }

    /**
     * Reads in the queue of Tokens from the Lexer into the internal logic of the parser.
     *
     * @param tokens : A queue of Tokens read from the Lexer.
     */
    @Override
    public void readTokens(List<Token> tokens) {
        myTokens = tokens;
        myAST = parseExpression(tokens, 0);
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
        return myAST;
    }

    public Expression parseExpression(List<Token> tokens, int index) {
        List<Token> copy = new 
    }
}
