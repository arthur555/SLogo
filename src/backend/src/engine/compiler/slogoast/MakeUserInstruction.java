package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This implements the MakeUserInstruction grammar in AST.
 *
 * @author Haotian Wang
 */
public class MakeUserInstruction extends Expression {
    private Token action;
    private Variable myVar;
    private VariableList variableList;
    private ExpressionList expressionList;


    public MakeUserInstruction(Token a, Variable variable, VariableList varList, ExpressionList exprList, StateMachine glob) {
        super(glob);
        action = a;
        myVar = variable;
        variableList = varList;
        expressionList = exprList;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s %s}", action.getString(), myVar.toString(), variableList.toString(), expressionList.toString());
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
        if (action.getString().equals("MakeUserInstruction")) {
            turtleManager.memory().setExpression(myVar.getVariableName(), this);
        }
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
        return interpret(turtleManager);
    }

    /**
     * Get the variableList object associated with this MakeUserInstruction class.
     *
     * @return A VariableList object.
     */
    VariableList getParameters() {
        return variableList;
    }

    /**
     * @return An ExpressionList object associated with this MakeUserInstruction object.
     */
    ExpressionList getExpressionList() {
        return expressionList;
    }
}
