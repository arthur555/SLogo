package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class handles the assignment grammar in the AST.
 *
 * @author Haotian Wang
 */
public class Assignment extends Expression {
    private Token myToken;
    private Variable myVar;
    private Expression myExpr;

    public Assignment(Token token, Variable var, Expression a) {
        myToken = token;
        myVar = var;
        myExpr = a;
    }
}
