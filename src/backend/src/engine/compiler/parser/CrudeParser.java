package engine.compiler.parser;

import engine.compiler.lexer.Token;
import engine.errors.CommandSyntaxException;
import engine.compiler.slogoast.*;
import javafx.util.Pair;

import java.util.*;

/**
 * A version 1 implementation of the Parser interface. It takes a list of Tokens and output ASTs or store variables.
 *
 * @author Haotian Wang
 */
public class CrudeParser implements Parser {
    private static final Token listStart = new Token("[", "ListStart");
    private static final Token listEnd = new Token("]", "ListEnd");
    private static final Token groupStart = new Token("(", "GroupStart");
    private static final Token groupEnd = new Token(")", "GroupEnd");

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
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        if (index >= myTokens.size()) {
            return nullPair;
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
        Pair<Expression, Integer> doTimesPair = parseDoTimes(index);
        if (doTimesPair.getKey() != null) {
            return doTimesPair;
        }
        Pair<Expression, Integer> forPair = parseFor(index);
        if (forPair.getKey() != null) {
            return forPair;
        }
        Pair<Expression, Integer> directPair = parseDirect(index);
        if (directPair.getKey() != null) {
            return directPair;
        }
        Pair<Expression, Integer> variablePair = parseVariable(index);
        if (variablePair.getKey() != null) {
            return variablePair;
        }
        Pair<Expression, Integer> expressionListPair = parseExpressionList(index);
        if (expressionListPair.getKey() != null) {
            return expressionListPair;
        }
        return nullPair;
    }

    /**
     * @param index
     * @return A pair of Expression and index for the For loop grammar.
     */
    private Pair<Expression, Integer> parseFor(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> forPair = parseToken(index, "For");
        if (forPair.getKey() == null) {
            return nullPair;
        }
        Pair<Token, Integer> listStartPair = parseToken(forPair.getValue(), "ListStart");
        if (listStartPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> variablePair = parseVariable(listStartPair.getValue());
        if (variablePair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> startPair = parseExpression(variablePair.getValue());
        if (startPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> endPair = parseExpression(startPair.getValue());
        if (endPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> stepPair = parseExpression(endPair.getValue());
        if (endPair.getKey() == null) {
            return nullPair;
        }
        Pair<Token, Integer> listEndPair = parseToken(stepPair.getValue(), "ListEnd");
        if (listEndPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> expressionListPair = parseExpressionList(listEndPair.getValue());
        if (expressionListPair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new For(forPair.getKey(), listStart, (Variable) variablePair.getKey(), startPair.getKey(), endPair.getKey(), stepPair.getKey(), listEnd, (ExpressionList) expressionListPair.getKey()), expressionListPair.getValue());
    }

    /**
     * @param index
     * @param type
     * @return This method parses single Token and return a pair of Token together with the new index.
     */
    private Pair<Token, Integer> parseToken(int index, String type) {
        if (index >= myTokens.size() || !myTokens.get(index).getType().equals(type)) {
            return new Pair<>(null, index);
        } else {
            return new Pair<>(myTokens.get(index), index + 1);
        }
    }

    /**
     * @param index
     * @return A pair of Expression and index for the DoTimes grammar.
     */
    private Pair<Expression, Integer> parseDoTimes(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> doTimesPair = parseToken(index, "DoTimes");
        if (doTimesPair.getKey() == null) {
            return nullPair;
        }
        Pair<Token, Integer> listStartPair = parseToken(doTimesPair.getValue(), "ListStart");
        if (listStartPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> variablePair = parseVariable(listStartPair.getValue());
        if (variablePair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> limitPair = parseExpression(variablePair.getValue());
        if (limitPair.getKey() == null) {
            return nullPair;
        }
        Pair<Token, Integer> listEndPair = parseToken(limitPair.getValue(), "ListEnd");
        if (listEndPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> expressionListPair = parseExpressionList(listEndPair.getValue());
        if (expressionListPair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new DoTimes(doTimesPair.getKey(), listStart, (Variable) variablePair.getKey(), limitPair.getKey(), listEnd, (ExpressionList) expressionListPair.getKey()), expressionListPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the ExpressionList grammar.
     */
    private Pair<Expression, Integer> parseExpressionList(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> listStartPair = parseToken(index, "ListStart");
        if (listStartPair.getKey() == null) {
            return nullPair;
        }
        int pointer = listStartPair.getValue();
        List<Expression> expressionList = new LinkedList<>();
        while (true) {
            Pair<Expression, Integer> listPair = parseExpression(pointer);
            if (listPair.getKey() == null) {
                break;
            }
            expressionList.add(listPair.getKey());
            pointer = listPair.getValue();
        }
        if (expressionList.isEmpty()) {
            return nullPair;
        }
        Pair<Token, Integer> listEndPair = parseToken(pointer, "ListEnd");
        if (listEndPair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new ExpressionList(listStart, expressionList, listEnd), listEndPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Condition grammar.
     */
    private Pair<Expression, Integer> parseCondition(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> conditionPair = parseToken(index, "Condition");
        if (conditionPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> expressionPair = parseExpression(conditionPair.getValue());
        if (expressionPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> expressionListPair = parseExpressionList(expressionPair.getValue());
        if (expressionListPair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new Condition(conditionPair.getKey(), expressionPair.getKey(), (ExpressionList) expressionListPair.getKey()), expressionListPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the MakeVariable grammar.
     */
    private Pair<Expression, Integer> parseMakeVariable(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> makeVariablePair = parseToken(index, "MakeVariable");
        if (makeVariablePair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> variablePair = parseVariable(makeVariablePair.getValue());
        if (variablePair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> expressionPair = parseExpression(variablePair.getValue());
        if (expressionPair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new MakeVariable(makeVariablePair.getKey(), (Variable) variablePair.getKey(), expressionPair.getKey()), expressionPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Binary grammar.
     */
    private Pair<Expression, Integer> parseBinary(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> binaryPair = parseToken(index, "Binary");
        if (binaryPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> firstPair = parseExpression(binaryPair.getValue());
        if (firstPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> secondPair = parseExpression(firstPair.getValue());
        if (secondPair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new Binary(binaryPair.getKey(), firstPair.getKey(), secondPair.getKey()), secondPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Variable grammar.
     */
    private Pair<Expression, Integer> parseVariable(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> variablePair = parseToken(index, "Variable");
        if (variablePair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new Variable(variablePair.getKey()), variablePair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Unary grammar.
     */
    private Pair<Expression, Integer> parseUnary(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        Pair<Token, Integer> unaryPair = parseToken(index, "Unary");
        if (unaryPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> secondPair = parseExpression(unaryPair.getValue());
        if (secondPair.getKey() == null) {
            return nullPair;
        }
        return new Pair<>(new Unary(unaryPair.getKey(), secondPair.getKey()), secondPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Group grammar.
     */
    private Pair<Expression, Integer> parseGroup(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        if (index >= myTokens.size()) {
            return nullPair;
        }
        if (!myTokens.get(index).getType().equals("GroupStart")) {
            return nullPair;
        }
        Pair<Token, Integer> groupStartPair = parseToken(index, "GroupStart");
        if (groupStartPair.getKey() == null) {
            return nullPair;
        }
        Pair<Expression, Integer> middlePair = parseExpression(groupStartPair.getValue());
        if (middlePair.getKey() == null) {
            return nullPair;
        }
        Pair<Token, Integer> groupEndPair = parseToken(index, "GroupEnd");
        return new Pair<>(new Group(groupStart, middlePair.getKey(), groupEnd), groupEndPair.getValue());
    }

    /**
     * @param index
     * @return A pair of Expression and index for the Direct grammar.
     */
    private Pair<Expression, Integer> parseDirect(int index) {
        Pair<Expression, Integer> nullPair = new Pair<>(null, index);
        if (index >= myTokens.size()) {
            return nullPair;
        }
        Token token = myTokens.get(index);
        if (!token.getType().equals("Direct") && !token.getType().equals("Constant")) {
            return nullPair;
        }
        return new Pair<>(new Direct(token), index + 1);
    }
}
