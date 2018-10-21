package engine.slogoast;

import engine.Lexer.Token;

import java.util.List;

/**
 * Implements the IfElse grammar in the AST.
 *
 * @author Haotian Wang
 */
public class IfElse extends Expression {
    private Token condition;
    private Expression expr;
    private Token startA;
    private List<Expression> exprListA;
    private Token endA;
    private Token startB;
    private List<Expression> exprListB;
    private Token endB;

    public IfElse(Token a, Expression val, Token listStart, List<Expression> listA,  Token listEnd, Token list2Start, List<Expression> listB, Token list2End) {
        condition = a;
        expr = val;
        startA = listStart;
        endA = listEnd;
        startB = list2Start;
        endB = list2End;
        exprListA = listA;
        exprListB = listB;
    }
}
