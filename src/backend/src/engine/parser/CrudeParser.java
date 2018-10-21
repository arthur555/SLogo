package engine.parser;

import engine.Lexer.Token;
import engine.errors.CommandSyntaxException;
import engine.slogoast.Binary;
import engine.slogoast.Direct;
import engine.slogoast.Expression;
import engine.slogoast.Unary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private Queue<Token> myTokens;
    private Expression myAST;

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
        myAST = parseExpression(tokens);
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

    public Expression parseExpression(Queue<Token> tokens) {
        if (parseDirect(tokens) != null) { return parseDirect(tokens); }
        if (parseUnary(tokens) != null) { return parseUnary(tokens); }
        if (parseBinary(tokens) != null) { return parseBinary(tokens); }
    }

    private Expression parseBinary(Queue<Token> tokens) {
        if (!tokens.peek().getType().equals("Binary")) {
            return null;
        }
        Queue<Token> copy = new LinkedList<>(tokens);
        Token operator = copy.poll();
        return new Binary(operator, )
    }

    private Expression parseUnary(Queue<Token> tokens) {
        if (!tokens.peek().getType().equals("Unary")) {
            return null;
        }
        Queue<Token> copy = new LinkedList<>(tokens);
        Token token = copy.poll();
        return new Unary(token, parseExpression(copy));
    }

    private Expression parseDirect(Queue<Token> tokens) {
        if (tokens.size() != 1) {
            return null;
        }
        Token token = tokens.peek();
        if (!token.getType().equals("Direct")) {
            return null;
        }
        return new Direct(token);
    }
}
