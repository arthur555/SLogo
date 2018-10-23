package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

import java.util.Arrays;
import java.util.List;

/**
 * This implements the MakeUserInstruction grammar in AST.
 *
 * @author Haotian Wang
 */
public class MakeUserInstruction implements Expression {
    private Token action;
    private Variable myVar;
    private VariableList variableList;
    private ExpressionList expressionList;


    public MakeUserInstruction(Token a, Variable variable, VariableList varList, ExpressionList exprList) {
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
     * This method uses the Visitor pattern to let the Interpreter acts on the concrete types of the AST node.
     *
     * @param interpreter
     */
    @Override
    public void execute(Interpreter interpreter) throws InterpretationException {
        interpreter.interpret(this);
    }
}
