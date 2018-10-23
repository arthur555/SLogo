package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This class emulates the For loop logic in the AST.
 *
 * @author Haotian Wang
 */
public class For implements Expression {
    private Token myToken;
    private Token start;
    private Variable var;
    private Expression min;
    private Expression max;
    private Expression step;
    private Token end;
    private ExpressionList expressionList;

    public For(Token token, Token firstStart, Variable variable, Expression lower, Expression higher, Expression increment, Token firstEnd, ExpressionList list) {
        myToken = token;
        start = firstStart;
        var = variable;
        min = lower;
        max = higher;
        step = increment;
        end = firstEnd;
        expressionList = list;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s %s %s %s %s %s}", myToken.getString(), start.getString(), var.toString(), min.toString(), max.toString(), step.toString(), end.getString(), expressionList.toString());
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
