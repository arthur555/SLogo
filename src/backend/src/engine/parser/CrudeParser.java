package engine.parser;

import engine.Lexer.Token;
import engine.errors.CommandSyntaxException;
import engine.slogoast.*;
import javafx.util.Pair;

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
    public void readTokens(List<Token> tokens) throws CommandSyntaxException {
        myTokens = tokens;
        myAST = parseGoal();
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
     * This method returns the complete syntax tree if the input command is grammatically correct.
     *
     * @return A root Expression node passed to the Interpreter.
     * @throws CommandSyntaxException
     */
    private Expression parseGoal() throws CommandSyntaxException {
        Pair<Expression, Integer> resultPair = parseExpression(0);
        if (resultPair.getKey() == null || resultPair.getValue() != myTokens.size()) {
            throw new CommandSyntaxException("The input command cannot be parsed.");
        } else {
            return resultPair.getKey();
        }
    }

    /**
     * The following methods are a family of parsing methods using recursive descent.
     *
     * @param index: The starting index of this parse query.
     * @return An Expression AST node, which is used for Expression grammar, and paired with it, an index after the parse.
     */
    private Pair<Expression, Integer> parseExpression(int index) {
        if (index >= myTokens.size()) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> groupPair = parseGroup(index);
        if (groupPair.getKey() != null) {
            return groupPair;
        }
        Pair<Expression, Integer> unaryPair = parseUnary(index);
        if (unaryPair.getKey() != null) {
            return unaryPair;
        }
        Pair<Expression, Integer> binaryPair = parseBinary(index);
        if (binaryPair.getKey() != null) {
            return binaryPair;
        }
        Pair<Expression, Integer> makeVariablePair = parseMakeVariable(index);
        if (makeVariablePair.getKey() != null) {
            return makeVariablePair;
        }
        Pair<Expression, Integer> conditionPair = parseCondition(index);
        if (conditionPair.getKey() != null) {
            return conditionPair;
        }
        Pair<Expression, Integer> directPair = parseDirect(index);
        if (directPair.getKey() != null) {
            return directPair;
        }
        Pair<Expression, Integer> variablePair = parseVariable(index);
        if (variablePair.getKey() != null) {
            return variablePair;
        }
        return new Pair<>(null, index);
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Condition grammar.
     */
    private Pair<Expression, Integer> parseCondition(int index) {
        Token token = myTokens.get(index);
        if (!token.getType().equals("Condition")) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> expressionPair = parseExpression(index + 1);
        if (expressionPair.getKey() == null) {
            return new Pair<>(null, index);
        }
        if (expressionPair.getValue() >= myTokens.size() || !myTokens.get(expressionPair.getValue()).getType().equals("ListStart")) {
            return new Pair<>(null, index);
        }
        List<Expression> expressionList = new LinkedList<>();
        int pointer = expressionPair.getValue() + 1;
        while (true) {
            Pair<Expression, Integer> listPair = parseExpression(pointer);
            if (listPair.getKey() == null) {
                break;
            }
            expressionList.add(listPair.getKey());
            pointer = listPair.getValue();
        }
        if (expressionList.isEmpty()) {
            return new Pair<>(null, index);
        }
        if (pointer >= myTokens.size() || !myTokens.get(pointer).getType().equals("ListEnd")) {
            return new Pair<>(null, index);
        }
        return new Pair<>(new Condition(token, expressionPair.getKey(), new Token("[", "ListStart"), expressionList, new Token("]", "ListEnd")), pointer + 1);
    }

    /**
     * @param index
     * @return A pair of Expression and index for the MakeVariable grammar.
     */
    private Pair<Expression, Integer> parseMakeVariable(int index) {
        Token token = myTokens.get(index);
        if (!token.getType().equals("MakeVariable")) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> variablePair = parseVariable(index + 1);
        if (variablePair.getKey() == null) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> expressionPair = parseExpression(variablePair.getValue());
        if (expressionPair.getKey() == null) {
            return new Pair<>(null, index);
        }
        return new Pair<>(new MakeVariable(token, (Variable) variablePair.getKey(), expressionPair.getKey()), expressionPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Binary grammar.
     */
    private Pair<Expression, Integer> parseBinary(int index) {
        Token operator = myTokens.get(index);
        if (!operator.getType().equals("Binary")) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> firstPair = parseExpression(index + 1);
        if (firstPair.getKey() == null) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> secondPair = parseExpression(firstPair.getValue());
        if (secondPair.getKey() == null) {
            return new Pair<>(null, index);
        }
        return new Pair<>(new Binary(operator, firstPair.getKey(), secondPair.getKey()), secondPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Variable grammar.
     */
    private Pair<Expression, Integer> parseVariable(int index) {
        Token token = myTokens.get(index);
        if (!token.getType().equals("Variable")) {
            return new Pair<>(null, index);
        }
        return new Pair<>(new Variable(token), index + 1);
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Unary grammar.
     */
    private Pair<Expression, Integer> parseUnary(int index) {
        Token operator = myTokens.get(index);
        if (!operator.getType().equals("Unary")) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> secondPair = parseExpression(index + 1);
        if (secondPair.getKey() == null) {
            return new Pair<>(null, index);
        }
        return new Pair<>(new Unary(operator, secondPair.getKey()), secondPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Group grammar.
     */
    private Pair<Expression, Integer> parseGroup(int index) {
        if (!myTokens.get(index).getType().equals("GroupStart")) {
            return new Pair<>(null, index);
        }
        Pair<Expression, Integer> middlePair = parseExpression(index + 1);
        if (middlePair.getKey() == null) {
            return new Pair<>(null, index);
        }
        if (middlePair.getValue() >= myTokens.size() || !myTokens.get(middlePair.getValue()).getType().equals("GroupEnd")) {
            return new Pair<>(null, index);
        }
        return new Pair<>(new Group(new Token("(", "GroupStart"), middlePair.getKey(), new Token(")", "GroupEnd")), middlePair.getValue() + 1);
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Direct grammar.
     */
    private Pair<Expression, Integer> parseDirect(int index) {
        Token token = myTokens.get(index);
        if (!token.getType().equals("Direct") && !token.getType().equals("Constant")) {
            return new Pair<>(null, index);
        }
        return new Pair<>(new Direct(token), index + 1);
    }
}
