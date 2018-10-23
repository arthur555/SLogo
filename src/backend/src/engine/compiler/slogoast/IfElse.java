package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * Implements the IfElse grammar in the AST.
 *
 * @author Haotian Wang
 */
public class IfElse implements Expression {
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

    /**
     * This method uses the Visitor pattern to let the Interpreter acts on the concrete types of the AST node.
     *
     * @param interpreter
     */
    @Override
    public void execute(Interpreter interpreter) throws InterpretationException {
        interpreter.interpret(this);
    }
}
