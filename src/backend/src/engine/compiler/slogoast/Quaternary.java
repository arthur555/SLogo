package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * Implements the Ternary operator in AST node.
 *
 * @author Haotian Wang
 */
public class Quaternary implements Expression {
    private Token myToken;
    private Expression firstArg;
    private Expression secondArg;
    private Expression thirdArg;
    private Expression fourthArg;

    public Quaternary(Token token, Expression expr1, Expression expr2, Expression expr3, Expression expr4) {
        myToken = token;
        firstArg = expr1;
        secondArg = expr2;
        thirdArg = expr3;
        fourthArg = expr4;
    }

    /**
     * This method lets the AST act on a Turtle model.
     *
     * @param turtleManager : The TurtleManager that is affected by applying the abstract syntax tree.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double interpret(TurtleManager turtleManager) throws InterpretationException {
        return 0;
    }

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     * @param turtleManager @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(TurtleManager turtleManager) throws InterpretationException {
        return 0;
    }
}
