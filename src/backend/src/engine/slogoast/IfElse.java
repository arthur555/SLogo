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
    private Token start;
    private Token end;
    private List<Expression> exprListA;
    private List<Expression> exprListB;

    public IfElse(Token a, Expression val, Token listStart, Token listEnd, List<Expression> listA, List<Expression> listB) {
        condition = a;
        expr = val;
        start = listStart;
        end = listEnd;
        exprListA = listA;
        exprListB = listB;
    }
}
