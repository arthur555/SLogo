package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This class handles the Repeat and If logic. They are in the form of Condition Expression [ (Expression)* ].
 *
 * @author Haotian Wang
 */
public class Condition implements Expression {
    private Token condition;
    private Expression expr;
    private ExpressionList expressionList;

    public Condition(Token a, Expression val, ExpressionList list) {
        condition = a;
        expr = val;
        expressionList = list;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s}", condition.getString(), expr.toString(), expressionList.toString());
    }

    /**
     * This method uses the Visitor pattern to let the Interpreter acts on the concrete types of the AST node.
     *
     * @param interpreter
     */
    @Override
    public void execute(Interpreter interpreter) throws InterpretationException {
        interpreter.evaluate(this);
    }
}
