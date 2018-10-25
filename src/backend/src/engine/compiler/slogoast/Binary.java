package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class is an AST node representing binary operation that takes two expressions as commands.
 *
 * @author Haotian Wang
 */
public class Binary implements Expression {
    private Token myToken;
    private Expression myFirstExpr;
    private Expression mySecondExpr;

    public Binary(Token token, Expression a, Expression b) {
        myToken = token;
        myFirstExpr = a;
        mySecondExpr = b;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s}", myToken.getString(), myFirstExpr.toString(), mySecondExpr.toString());
    }


    /**
     * This method lets the AST act on a Turtle model.
     *
     * @param turtleManager : The TurtleManager that is affected by applying the abstract syntax tree.
     * @throws InterpretationException
     */
    @Override
    public double interpret(TurtleManager turtleManager) throws InterpretationException {
        if (myToken.getString().equals("Sum")) {
            return myFirstExpr.evaluate(turtleManager) + mySecondExpr.evaluate(turtleManager);
        } else if (myToken.getString().equals("Difference")) {
            // TODO
        } else if (myToken.getString().equals("Quotient")) {
            // TODO
        } else if (myToken.getString().equals("Product")) {
            // TODO
        } else if (myToken.getString().equals("Remainder")) {
            // TODO
        } else if (myToken.getString().equals("Power")) {
            // TODO
        } else if (myToken.getString().equals("LessThan")) {
            // TODO
        } else if (myToken.getString().equals("GreaterThan")) {
            // TODO
        } else if (myToken.getString().equals("Equal")) {
            // TODO
        } else if (myToken.getString().equals("NotEqual")) {
            // TODO
        } else if (myToken.getString().equals("And")) {
            // TODO
        } else if (myToken.getString().equals("Or")) {
            // TODO
        } else if (myToken.getString().equals("Towards")) {
            // TODO
        }
        return 0;
    }

    /**
     * This method evaluates the return value of the expression.
     *
     *
     * @param turtleManager@return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(TurtleManager turtleManager) throws InterpretationException {
        return interpret(turtleManager);
    }
}
