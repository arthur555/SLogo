package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.compiler.storage.VariableType;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class emulates the For loop logic in the AST.
 *
 * @author Haotian Wang
 */
public class For implements Expression {
    private Token myToken;
    private Token start;
    private Variable var;
    private Expression min;
    private Expression max;
    private Expression step;
    private Token end;
    private ExpressionList expressionList;

    public For(Token token, Token firstStart, Variable variable, Expression lower, Expression higher, Expression increment, Token firstEnd, ExpressionList list) {
        myToken = token;
        start = firstStart;
        var = variable;
        min = lower;
        max = higher;
        step = increment;
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
        return String.format("{%s %s %s %s %s %s %s %s}", myToken.getString(), start.getString(), var.toString(), min.toString(), max.toString(), step.toString(), end.getString(), expressionList.toString());
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
        if (myToken.getString().equals("For")) {
            String variableName = var.getVariableName();
            boolean reset = turtleManager.memory().containsVariable(variableName);
            double old = 0;
            if (reset){
                old = (double)turtleManager.memory().getValue(variableName);
            }

            StateMachine memory = turtleManager.memory();
            for (double counter = min.evaluate(turtleManager); counter < max.evaluate(turtleManager);  counter += step.evaluate(turtleManager)){
                memory.setVariable(var.getVariableName(), counter, VariableType.DOUBLE);
                ret = expressionList.interpret(turtleManager);
            }
            if (reset){
                turtleManager.memory().setVariable(variableName, old, VariableType.DOUBLE);
            } else{
                turtleManager.memory().removeVariable(variableName);
            }
        }
        return ret;
    }
}
