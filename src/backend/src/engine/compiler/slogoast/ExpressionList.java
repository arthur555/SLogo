package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleManager;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the list of Expression grammar.
 *
 * @author Haotian Wang
 */
public class ExpressionList extends Expression {
    private Token listStart;
    private List<Expression> expressionList;
    private Token listEnd;

    public ExpressionList(Token start, List<Expression> list, Token end, StateMachine glob) {
        super(glob);
        listStart = start;
        expressionList = list;
        listEnd = end;
    }
    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return Arrays.toString(expressionList.toArray(new Expression[expressionList.size()]));
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
        double ret = 0;
        for (Expression expr : expressionList) {
            ret = expr.interpret(turtleManager);
        }
        return ret;
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
        return interpret(turtleManager);
    }


    /**
     * @return The actual List of Expression associated with this ExpressionList object.
     */
    List<Expression> getListOfExpressions() {
        return expressionList;
    }

}
