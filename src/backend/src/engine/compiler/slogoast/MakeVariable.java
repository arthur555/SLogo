package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.compiler.storage.VariableType;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class handles the assignment grammar in the AST.
 *
 * @author Haotian Wang
 */
public class MakeVariable extends Expression {
    private Token myToken;
    private Variable myVar;
    private Expression myExpr;

    public MakeVariable(Token token, Variable var, Expression a, StateMachine glob) {
        super(glob);
        myToken = token;
        myVar = var;
        myExpr = a;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s}", myToken.getString(), myVar.toString(), myExpr.toString());
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
        if (myToken.getString().equals("MakeVariable")) {
            double ret = myExpr.evaluate(turtleManager);
            turtleManager.memory().setGlobalVariable(myVar.getVariableName(), ret, VariableType.DOUBLE);
            return ret;
        }
        // turtleManager.memory().setDouble("x"+((int) (Math.random() * 1000)), Math.random()); // just to test integration with the view
        return 0;
    }

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     *
     * @param turtleManager@return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(TurtleManager turtleManager) throws InterpretationException {
        return 0;
    }
}
