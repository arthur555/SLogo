package engine.slogoast;

import engine.Lexer.Token;

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
    private Token startA;
    private List<Variable> variableList;
    private Token endA;
    private Token startB;
    private List<Expression> exprList;
    private Token endB;

    public MakeUserInstruction(Token a, Variable variable, Token listStart, List<Variable> listA,  Token listEnd, Token list2Start, List<Expression> listB, Token list2End) {
        action = a;
        myVar = variable;
        startA = listStart;
        endA = listEnd;
        startB = list2Start;
        endB = list2End;
        variableList = listA;
        exprList = listB;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s ; %s ; %s ; %s ; %s ; %s ; %s ; %s}", action.toString(), myVar.toString(), startA.toString(), Arrays.toString(variableList.toArray(new Variable[variableList.size()])), endA.toString(), startB.toString(), Arrays.toString(exprList.toArray(new Expression[exprList.size()])), endB.toString());
    }
}
