package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleModel;

/**
 * Implements the IfElse grammar in the AST.
 *
 * @author Haotian Wang
 */
public class IfElse implements Expression {
    private Token myToken;
    private Expression expr;
    private ExpressionList expressionListA;
    private ExpressionList expressionListB;

    public IfElse(Token a, Expression val, ExpressionList listA, ExpressionList listB) {
        myToken = a;
        expr = val;
        expressionListA = listA;
        expressionListB = listB;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s %s}", myToken.getString(), expr.toString(), expressionListA.toString(), expressionListB.toString());
    }

    /**
     * This method lets the AST act on a Turtle model.
     *
     * @param turtle : The TurtleModel that is affected by applying the abstract syntax tree.
     * @param state  : The StateMachine that records the variables.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double interpret(TurtleModel turtle, StateMachine state) throws InterpretationException {
        return 0;
    }

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     * @param state : The StateMachine that records the variables.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(StateMachine state) throws InterpretationException {
        return 0;
    }

}
