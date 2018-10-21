package engine.slogoast;

import engine.Lexer.Token;

import java.util.List;

/**
 * This class handles the Repeat and If logic. They are in the form of Condition Expression [ (Expression)* ].
 *
 * @author Haotian Wang
 */
public class Condition extends Expression {
    private Token condition;
    private Expression expr;
    private Token start;
    private Token end;
    private List<Expression> exprList;

    public Condition(Token a, Expression val, Token listStart, Token listEnd, List<Expression> list) {
        condition = a;
        expr = val;
        start = listStart;
        end = listEnd;
        exprList = list;
    }
}
