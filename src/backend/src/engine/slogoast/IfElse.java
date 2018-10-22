package engine.slogoast;

import engine.Lexer.Token;

import java.util.Arrays;
import java.util.List;

/**
 * Implements the IfElse grammar in the AST.
 *
 * @author Haotian Wang
 */
public class IfElse extends Expression {
    private Token myToken;
    private Expression expr;
    private Token startA;
    private List<Expression> exprListA;
    private Token endA;
    private Token startB;
    private List<Expression> exprListB;
    private Token endB;

    public IfElse(Token a, Expression val, Token listStart, List<Expression> listA,  Token listEnd, Token list2Start, List<Expression> listB, Token list2End) {
        myToken = a;
        expr = val;
        startA = listStart;
        endA = listEnd;
        startB = list2Start;
        endB = list2End;
        exprListA = listA;
        exprListB = listB;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s ; %s ; %s ; %s ; %s ; %s ; %s ; %s}", myToken.toString(), expr.toString(), startA.toString(), Arrays.toString(exprListA.toArray(new Expression[exprListA.size()])), endA.toString(), startB.toString(), Arrays.toString(exprListB.toArray(new Expression[exprListB.size()])), endB.toString());
    }
}
