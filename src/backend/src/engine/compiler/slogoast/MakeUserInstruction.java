package engine.compiler.slogoast;

import engine.compiler.Token;

import java.util.Arrays;
import java.util.List;

/**
 * This implements the MakeUserInstruction grammar in AST.
 *
 * @author Haotian Wang
 */
public class MakeUserInstruction extends Expression {
    private Token action;
    private Variable myVar;
    private Token start;
    private List<Variable> variableList;
    private Token end;
    private ExpressionList expressionList;


    public MakeUserInstruction(Token a, Variable variable, Token listStart, List<Variable> varList,  Token listEnd, ExpressionList exprList) {
        action = a;
        myVar = variable;
        start = listStart;
        end = listEnd;
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
        return String.format("{%s %s %s %s}", action.getString(), myVar.toString(), Arrays.toString(variableList.toArray(new Variable[variableList.size()])), expressionList.toString());
    }
}
