package engine.parser;

import engine.Lexer.Token;
import engine.commands.Command;
import engine.errors.CommandSyntaxException;
import engine.slogoast.*;

import java.util.*;

/**
 * A version 1 implementation of the Parser interface. It takes user raw input Strings and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private List<Token> myTokens;
    private Expression myAST;
    private int pointer;

    public CrudeParser() {
        myTokens = new ArrayList<>();
    }

    /**
     * Reads in the queue of Tokens from the Lexer into the internal logic of the parser.
     *
     * @param tokens : A queue of Tokens read from the Lexer.
     */
    @Override
    public void readTokens(List<Token> tokens) throws CommandSyntaxException {
        pointer = 0;
        myTokens = tokens;
        myAST = parseExpression(myTokens);
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

    /**
     * The following methods are a family of parsing methods using recursive descent.
     *
     * @param tokens: A List of Tokens input into Parser.
     * @return An Expression AST node, which is used for Expression grammar.
     * @throws CommandSyntaxException
     */
    public Expression parseExpression(List<Token> tokens) throws CommandSyntaxException {
        if (pointer >= tokens.size()) {
            throw new CommandSyntaxException("The input command is illegal in the current grammar.");
        }
        int temp = pointer;
        Expression direct = parseDirect(tokens);
        if (direct != null) {
            return direct;
        }
        Expression group = parseGroup(tokens);
        if (group != null) {
            return group;
        }
        Expression unary = parseUnary(tokens);
        if (unary != null) {
            return unary;
        }
        Expression binary = parseBinary(tokens);
        if (binary != null) {
            return binary;
        }

        pointer = temp;
        throw new CommandSyntaxException("The input command is illegal in the current grammar.");
    }

    private Expression parseBinary(List<Token> tokens) {
        Token operator = tokens.get(pointer);
        if (!operator.getType().equals("Binary")) {
            return null;
        }
        int temp = pointer;
        pointer++;
        Expression first = null;
        Expression variable = parseVariable(tokens);
        if (variable != null) {
            first = variable;
        }
        Expression direct = parseDirect(tokens);
        if (direct != null) {
            first = direct;
        }
        if (first == null) {
            pointer = temp;
            return null;
        }
        Expression second = null;
        variable = parseVariable(tokens);
        if (variable != null) {
            second = variable;
        }
        direct = parseDirect(tokens);
        if (direct != null) {
            second = direct;
        }
        if (second == null) {
            pointer = temp;
            return null;
        }
        return new Binary(operator, first, second);
    }

    private Expression parseVariable(List<Token> tokens) {
        Token tok = tokens.get(pointer);
        if (!tok.getType().equals("Variable")) {
            return null;
        }
        pointer++;
        return new Variable(tok);
    }

    private Expression parseUnary(List<Token> tokens) throws CommandSyntaxException {
        Token operator = tokens.get(pointer);
        if (!operator.getType().equals("Unary")) {
            return null;
        }
        pointer++;
        Expression secondPart = parseExpression(tokens);
        return new Unary(operator, secondPart);
    }

    private Expression parseGroup(List<Token> tokens) throws CommandSyntaxException {
        if (!tokens.get(pointer).getType().equals("GroupStart")) {
            return null;
        }
        int tempPointer = pointer;
        pointer++;
        Expression middle = parseExpression(tokens);
        if (middle == null) {
            pointer = tempPointer;
            return null;
        }
        pointer++;
        if (pointer >= tokens.size() || !tokens.get(pointer).getType().equals("GroupEnd")) {
            pointer = tempPointer;
            return null;
        }
        pointer++;
        return new Group(new Token("(", "GroupStart"), middle, new Token(")", "GroupEnd"));
    }

    private Expression parseDirect(List<Token> tokens) {
        Token token = tokens.get(pointer);
        if (!token.getType().equals("Direct") || !token.getType().equals("Constant")) {
            return null;
        }
        pointer++;
        return new Direct(token);
    }
}
