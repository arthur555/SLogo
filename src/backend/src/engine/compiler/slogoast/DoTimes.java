package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleModel;

/**
 * This class handles the concept of DoTimes.
 *
 * @author Haotian Wang
 */
public class DoTimes implements Expression {
    private Token myToken;
    private Token start;
    private Variable var;
    private Expression limit;
    private Token end;
    private ExpressionList expressionList;

    public DoTimes(Token token, Token firstStart, Variable variable, Expression varLit, Token firstEnd, ExpressionList list) {
        myToken = token;
        start = firstStart;
        var = variable;
        limit = varLit;
        end = firstEnd;
        expressionList = list;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s %s %s %s}", myToken.getString(), start.getString(), var.toString(), limit.toString(), end.getString(), expressionList.toString());
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
