package engine.compiler.slogoast;

import engine.compiler.Token;

/**
 * Implements the IfElse grammar in the AST.
 *
 * @author Haotian Wang
 */
public class IfElse extends Expression {
    private Token myToken;
    private Expression expr;
    private ExpressionList expressionListA;
    private ExpressionList expressionListB;

    public IfElse(Token a, Expression val, ExpressionList listA, ExpressionList listB) {
        myToken = a;
        expr = val;
        expressionListA = listA;
        expressionListB = listB;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s %s}", myToken.getString(), expr.toString(), expressionListA.toString(), expressionListB.toString());
    }
}
