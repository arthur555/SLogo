package engine.slogoast;

import engine.Lexer.Token;

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
}
