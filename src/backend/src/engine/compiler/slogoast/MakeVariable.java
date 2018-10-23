package engine.compiler.slogoast;

import engine.compiler.Token;

/**
 * This class handles the assignment grammar in the AST.
 *
 * @author Haotian Wang
 */
public class MakeVariable extends Expression {
    private Token myToken;
    private Variable myVar;
    private Expression myExpr;

    public MakeVariable(Token token, Variable var, Expression a) {
        myToken = token;
        myVar = var;
        myExpr = a;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s}", myToken.getString(), myVar.toString(), myExpr.toString());
    }
}
